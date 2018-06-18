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
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller {
    private Model model = new Model();

    public ArrayList<Dado> obterTodasPosicoes() {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
    }

    public ArrayList<Dado> obterPosicoesDaLinha(String linha) {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDaLinha/" + linha);
    }

    public ArrayList<Dado> obterPosicoesDoOnibus(String ordem) {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDoOnibus/" + ordem);
    }

    public void atualizaTextos(ArrayList<Label> list) {
        list.get(0).setText("Ultima leitura de dados:\t" + model.getDataUltimaLeitura());
        list.get(1).setText("Data mais recente:  \t" + model.getDataMaisRecente());
        list.get(2).setText("Data menos recente: \t" + model.getDataMenosRecente());
    }

    public Button getTodasPosicoesButton(TableView<Dado> table, ArrayList<Label> textos) {
        Button btn = new Button("Obter todas posicoes");
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //table.getItems().clear();
                ObservableList<Dado> observable = FXCollections.observableArrayList();
                ArrayList<Dado> array = obterTodasPosicoes();
                for (Dado d : array)
                    observable.add(d);
                table.setItems(observable);
                atualizaTextos(textos);
            }
        });

        return btn;
    }

    public Button getPosicoesDaLinhaButton(TableView<Dado> table, ArrayList<Label> textos) {
        Button btn = new Button("Filtrar por linha");
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Filtrar por linha");
                dialog.setHeaderText(null);
                dialog.setContentText("Digite a linha:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    //table.getItems().clear();
                    ObservableList<Dado> observable = FXCollections.observableArrayList();
                    ArrayList<Dado> array = obterPosicoesDaLinha(result.get());
                    for (Dado d : array)
                        observable.add(d);
                    table.setItems(observable);
                    atualizaTextos(textos);
                }
            }
        });

        return btn;
    }

    public Button getPosicoesDoOnibusButton(TableView<Dado> table, ArrayList<Label> textos) {
        Button btn = new Button("Filtrar por onibus");
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Filtrar por onibus");
                dialog.setHeaderText(null);
                dialog.setContentText("Digite a ordem do onibus:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    //table.getItems().clear();
                    ObservableList<Dado> observable = FXCollections.observableArrayList();
                    ArrayList<Dado> array = obterPosicoesDoOnibus(result.get());
                    for (Dado d : array)
                        observable.add(d);
                    table.setItems(observable);
                    atualizaTextos(textos);
                }
            }
        });

        return btn;
    }
}
