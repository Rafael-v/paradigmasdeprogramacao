import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class Model extends Application {
    private HttpJSONService http = new HttpJSONService();
    private ArrayList<Dado> ultimaLeitura = new ArrayList<Dado>();
    private String dataUltimaLeitura;

    public static void main(String[] args) {
        launch(args);
    }
      
    @Override
    public void start(Stage stage) {
        obterTodasPosicoes();
        System.out.println("Data da ultima leitura: " + getDataUltimaLeitura());

        System.out.println("Data do registro mais recente: " + getDataMaisRecente());
        System.out.println("Data do registro menos recente: " + getDataMenosRecente());

        System.out.println("Numero de veiculos: " + getNumVeiculos());
        System.out.println("Numero de veiculos parados: " + getVeiculosParados());

        System.out.println("Lista de linhas: " + getLinhas());

        System.exit(0);
    }

    public void obterTodasPosicoes() {
        ultimaLeitura.add(new Dado("06-15-2018 00:00:50","A55108",0.0,-22.905741,-43.260529,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 00:16:18","D53604",0.0,-22.88382,-43.49485,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 00:24:00","C47676",0.0,-22.952339,-43.34885,0.0));
        ultimaLeitura.add(new Dado("06-15-2010 00:57:50","D53930",0.0,-22.884291,-43.495258,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:01:50","D53706",0.0,-22.88431,-43.495651,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:05:05","D53916",0.0,-22.883739,-43.496029,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:06:23","D53521",0.0,-22.883619,-43.494701,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:20:45","D53540",0.0,-22.884159,-43.495579,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:25:36","D53633",0.0,-22.883801,-43.494549,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 01:28:18","C47765",880.0,-22.95159,-43.349312,16.0));
        ultimaLeitura.add(new Dado("06-15-2019 02:07:32","A48047",415.0,-22.910732,-43.270615,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 02:08:38","A48028",415.0,-22.91037,-43.27095,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 02:20:32","A48134",426.0,-22.910639,-43.269722,0.37));
        ultimaLeitura.add(new Dado("06-15-2018 02:24:21","A48049",202.0,-22.910704,-43.269871,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 02:26:14","A48090",415.0,-22.910683,-43.270927,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 02:42:14","B27142",951.0,-22.81702,-43.30175,0.0));
        ultimaLeitura.add(new Dado("06-15-2018 02:53:41","A63522",0.0,-22.86809,-43.258049,4.0));
        ultimaLeitura.add(new Dado("06-15-2018 03:35:06","A63504",0.0,-22.867661,-43.258789,0.0));

        dataUltimaLeitura = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public int getNumVeiculos() {
        return ultimaLeitura.size();
    }

    public int getVeiculosParados() {
        int parados = 0;
        for (Dado d : ultimaLeitura) {
            if (d.getVelocidade() == 0.0)
                parados++;
        }
        return parados;
    }

    public String getDataUltimaLeitura() {
        return dataUltimaLeitura;
    }

    public String getDataMenosRecente() {
        if (ultimaLeitura.isEmpty())
            return "Sem registros";
        String menosRecente = ultimaLeitura.get(0).getData();
        for (int i = 1; i < ultimaLeitura.size(); i++) {
            if (dataMenor(ultimaLeitura.get(i).getData(), menosRecente))
                menosRecente = ultimaLeitura.get(i).getData();
        }
        return menosRecente;
    }

    public String getDataMaisRecente() {
        if (ultimaLeitura.isEmpty())
            return "Sem registros";
        String maisRecente = ultimaLeitura.get(0).getData();
        for (int i = 1; i < ultimaLeitura.size(); i++) {
            if (dataMenor(maisRecente, ultimaLeitura.get(i).getData()))
                maisRecente = ultimaLeitura.get(i).getData();
        }
        return maisRecente;
    }

    public ArrayList<Double> getLinhas() {
        ArrayList<Double> linhas = new ArrayList<Double>();
        for (Dado d : ultimaLeitura) {
            if (!linhas.contains(d.getLinha()))
                linhas.add(d.getLinha());
        }
        return linhas;
    }

    public int getVeiculosMovimentoNaLinha(double linha) {
        int movimento = 0;
        for (Dado d : ultimaLeitura) {
            if (d.getLinha() == linha && d.getVelocidade() != 0.0)
                movimento++;
        }
        return movimento;
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
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        int responseCode = con.getResponseCode();
        
        System.out.println("\n'GET' request sent to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        
        // Print result
        System.out.println(response.toString());

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
        Map contents = (Map) result;
        return contents;
    }
}
