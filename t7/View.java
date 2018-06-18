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
import javafx.scene.layout.BorderPane;
import javafx.scene.chart.*;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class View extends Application {
    private Controller controller = new Controller();
    private ArrayList<Label> textos = new ArrayList<>();
    private TableView<Dado> tabela = new TableView<>();
    private PieChart chart = new PieChart();

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        initLabels();
        initTable();
        initPieChar();

        /*center*/
        final Label title = new Label("Monitoramento de frota de onibus urbano da cidade de Rio de Janeiro");
        title.setFont(new Font("Arial", 20));

        Button btn1 = controller.getTodasPosicoesButton(tabela, chart, textos);
        Button btn2 = controller.getPosicoesDaLinhaButton(tabela, chart, textos);
        Button btn3 = controller.getPosicoesDoOnibusButton(tabela, chart, textos);

        final VBox vboxCenter = new VBox();
        vboxCenter.setSpacing(5);
        vboxCenter.setPadding(new Insets(10, 10, 10, 10));
        vboxCenter.getChildren().addAll(title, tabela, btn1, btn2, btn3);
        for (Label l : textos)
            vboxCenter.getChildren().add(l);

        /*right*/
        final VBox vboxRight = new VBox();
        vboxRight.setSpacing(5);
        vboxRight.setPadding(new Insets(10, 10, 10, 10));
        vboxRight.getChildren().addAll();

        /*left*/
        final VBox vboxLeft = new VBox();
        vboxLeft.setSpacing(5);
        vboxLeft.setPadding(new Insets(10, 10, 10, 10));
        vboxLeft.getChildren().addAll(chart);

        borderPane.setCenter(vboxCenter);
        borderPane.setRight(vboxRight);
        borderPane.setLeft(vboxLeft);

        Scene scene = new Scene(borderPane, 1200, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void initLabels() {
        textos.add(new Label("Ultima leitura de dados:\tSem registros"));
        textos.add(new Label("Data mais recente:  \tSem registros"));
        textos.add(new Label("Data menos recente: \tSem registros"));
        for (Label l : textos)
            l.setFont(new Font("Arial", 12));
    }

    private void initPieChar() {
        chart.setTitle("Situacao dos veiculos");
        chart.setPrefHeight(300);
        controller.atualizaPie(chart);
    }

    private void initTable() {
        TableColumn<Dado,String> dataCol = new TableColumn<Dado,String>("Data");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        tabela.getColumns().add(dataCol);

        TableColumn<Dado,String> ordemCol = new TableColumn<Dado,String>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        tabela.getColumns().add(ordemCol);

        TableColumn<Dado,String> linhaCol = new TableColumn<Dado,String>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        tabela.getColumns().add(linhaCol);

        TableColumn<Dado,String> latitudeCol = new TableColumn<Dado,String>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        tabela.getColumns().add(latitudeCol);

        TableColumn<Dado,String> longitudeCol = new TableColumn<Dado,String>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        tabela.getColumns().add(longitudeCol);

        TableColumn<Dado,String> velocidadeCol = new TableColumn<Dado,String>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
        tabela.getColumns().add(velocidadeCol);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
