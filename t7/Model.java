import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Model {
    private final HttpJSONService http = new HttpJSONService();
    private final ArrayList<Dado> dados = new ArrayList<>();
    private String dataUltimaLeitura = "Sem registros";

    public ArrayList<Dado> obterDados(String url) {
        dados.clear();
        inserirDados(getJson(url));
        return dados;
    }

    public int getTotalVeiculos() {
        return dados.size();
    }

    public int getVeiculosParados() {
        int parados = 0;
        for (Dado d : dados) {
            if (d.getVelocidade() == 0.0)
                parados++;
        }
        return parados;
    }

    public String getDataUltimaLeitura() {
        return dataUltimaLeitura;
    }

    public String getDataMenosRecente() {
        if (dados.isEmpty())
            return "Sem registros";
        String menosRecente = dados.get(0).getData();
        for (Dado d : dados) {
            if (dataMenor(d.getData(), menosRecente))
                menosRecente = d.getData();
        }
        return menosRecente;
    }

    public String getDataMaisRecente() {
        if (dados.isEmpty())
            return "Sem registros";
        String maisRecente = dados.get(0).getData();
        for (Dado d : dados) {
            if (dataMenor(maisRecente, d.getData()))
                maisRecente = d.getData();
        }
        return maisRecente;
    }

    public ArrayList<String> getLinhas() {
        ArrayList<String> linhas = new ArrayList<>();
        for (Dado d : dados) {
            if (!linhas.contains(d.getLinha()))
                linhas.add(d.getLinha());
        }
        linhas.remove(""); // remove linha vazia (definida para onibus sem linha)
        return linhas;
    }

    public int getVeiculosMovimentoNaLinha(String linha) {
        int emMovimento = 0;
        for (Dado d : dados) {
            if (d.getLinha().equals(linha) && d.getVelocidade() != 0.0)
                emMovimento++;
        }
        return emMovimento;
    }

    public String getEndereco(double lat, double lng) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + lat + "," + lng + "&key=AIzaSyCMzST_dQwc1gbFvxFakpAt_YBAhSu5MuI";
        Map obj = getJson(url);

        if (obj != null) {
            // se deu limite de acesso, tenta de novo
            // (geralmente prox tentativa retorna ok)
            if (obj.get("status") == "OVER_QUERY_LIMIT")
                obj = getJson(url);
            if (obj.get("status") == "OK") {
                List l = (List)obj.get("results"); // lista de objetos
                return (String)((Map)l.get(0)).get("formatted_address");
            }
            System.out.println("Error (" + obj.get("status") + ")");
        }

        System.out.print("Nao foi possivel encontrar o endereco solicitado. ");
        return "Rio de Janeiro - RJ, Brasil";
    }

    private void inserirDados(Map obj) {
        if (obj == null) return;
        for (Object l : (List)obj.get("DATA"))
            dados.add(new Dado((List)l));
        dataUltimaLeitura = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    private Map getJson(String url) {
        Map json = null;
        try {
            json = http.sendGet(url);
        } catch (Exception e) {
            System.out.println("\nNao foi possivel conectar ao servidor.");
            System.out.println("Verifique sua conexao e tente novamente ou insira os dados via arquivo de texto.");
            System.out.println("(" + e + ")");
        }
        return json;
    }

    private Boolean dataMenor(String d1, String d2) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        try {
            if (format.parse(d1).before(format.parse(d2)))
                return true;
        } catch (ParseException e) {
            System.out.println("(" + e + ")");
        }
        return false;
    }
}

class HttpJSONService {
    private final String USER_AGENT = "Mozilla/5.0";
    private final JSONParsing engine = new JSONParsing();
    
    // HTTP GET request
    public Map sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        
        System.out.println("\n'GET' request sent to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        
        // Print result
        //System.out.println(response.toString());

        // Parse JSON result
        JSONParsing engine = new JSONParsing();
        return engine.parseJSON(response.toString());
    }
}

class JSONParsing {
    private final ScriptEngine engine;
  
    public JSONParsing() {
        ScriptEngineManager sem = new ScriptEngineManager();
        this.engine = sem.getEngineByName("javascript");
    }
  
    public Map parseJSON(String json) throws IOException, ScriptException {
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = this.engine.eval(script);
        Map contents = (Map)result;
        return contents;
    }
}
