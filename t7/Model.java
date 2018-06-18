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
    private HttpJSONService http = new HttpJSONService();
    private ArrayList<Dado> dados = new ArrayList<>();
    private String dataUltimaLeitura = "Sem registros";

    public void obterTodasPosicoes() {
        inserirDados(getJson("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes"));
    }

    public void obterPosicoesDaLinha(int linha) {
        inserirDados(getJson("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDaLinha/" + linha));
    }

    public void obterPosicoesDoOnibus(String ordem) {
        inserirDados(getJson("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDoOnibus/" + ordem));
    }

    public void obterPorArquivo(String nomeArq) {
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
        for (int i = 1; i < dados.size(); i++) {
            if (dataMenor(dados.get(i).getData(), menosRecente))
                menosRecente = dados.get(i).getData();
        }
        return menosRecente;
    }

    public String getDataMaisRecente() {
        if (dados.isEmpty())
            return "Sem registros";
        String maisRecente = dados.get(0).getData();
        for (int i = 1; i < dados.size(); i++) {
            if (dataMenor(maisRecente, dados.get(i).getData()))
                maisRecente = dados.get(i).getData();
        }
        return maisRecente;
    }

    public ArrayList getLinhas() {
        ArrayList linhas = new ArrayList();
        for (Dado d : dados) {
            if (!linhas.contains(d.getLinha()))
                linhas.add(d.getLinha());
        }
        return linhas;
    }

    public int getVeiculosMovimentoNaLinha(int linha) {
        int movimento = 0;
        for (Dado d : dados) {
            if (d.getLinha() == linha && d.getVelocidade() != 0.0)
                movimento++;
        }
        return movimento;
    }

    private void inserirDados(Map obj) {
        List listaDados = (List)obj.get("DATA");
        ArrayList<List> dado = new ArrayList<>();
        for (int i = 0; i < listaDados.size(); i++) {
            dados.add(new Dado((List)listaDados.get(i)));
        }
        dataUltimaLeitura = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    private Map getJson(String url) {
        Map json = null;
        try {
            json = http.sendGet(url);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Nao foi possivel conectar ao servidor. Verifique sua conexao e tente novamente.");
        }
        return json;
    }

    private Boolean dataMenor(String d1, String d2) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        try {
            if (format.parse(d1).before(format.parse(d2)))
                return true;
        } catch (ParseException e) {
            System.out.println(e);
        }
        return false;
    }
}

class HttpJSONService {
    private final String USER_AGENT = "Mozilla/5.0";
    private JSONParsing engine = new JSONParsing();
    
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
    private ScriptEngine engine;
  
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
