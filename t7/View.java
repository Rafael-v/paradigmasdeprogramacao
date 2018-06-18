import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.scene.control.*;

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
    private ArrayList<Label> textos;
    private TableView<Dado> tabela;
    private BarChart<String,Number> bar;
    private PieChart pie;

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        initLabels();
        initTableView();
        initPieChart();
        initBarChart();

        /*center*/
        final Label title = new Label("  Monitoramento de frota de onibus\nurbano na cidade do Rio de Janeiro");
        title.setFont(new Font("Arial", 22));
        final VBox titleBox = new VBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);

        Button btn1 = controller.getTodasPosicoesButton(tabela, pie, bar, textos);
        Button btn2 = controller.getPosicoesDaLinhaButton(tabela, pie, bar, textos);
        Button btn3 = controller.getPosicoesDoOnibusButton(tabela, pie, bar, textos);
        final HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(5);
        hboxButtons.setPadding(new Insets(10, 10, 10, 10));
        hboxButtons.getChildren().addAll(btn1, btn2, btn3);
        hboxButtons.setAlignment(Pos.CENTER);

        final VBox vboxCenter = new VBox();
        vboxCenter.setSpacing(5);
        vboxCenter.setPadding(new Insets(10, 10, 10, 10));
        vboxCenter.getChildren().addAll(titleBox, tabela, hboxButtons);
        for (Label l : textos)
            vboxCenter.getChildren().add(l);

        /*right*/
        final VBox vboxRight = new VBox();
        vboxRight.setSpacing(5);
        vboxRight.setPadding(new Insets(10, 10, 10, 10));
        vboxRight.getChildren().addAll(initWebView());

        /*left*/
        final VBox vboxLeft = new VBox();
        vboxLeft.setSpacing(5);
        vboxLeft.setPadding(new Insets(10, 10, 10, 10));
        vboxLeft.getChildren().addAll(pie, new Separator(), bar);

        borderPane.setCenter(vboxCenter);
        borderPane.setRight(vboxRight);
        borderPane.setLeft(vboxLeft);

        Scene scene = new Scene(borderPane, 1350, 650);
        stage.setScene(scene);
        stage.show();
    }

    private WebView initWebView() {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        browser.setPrefWidth(400);
        browser.setPrefHeight(400);
        webEngine.load("https://maps.googleapis.com/maps/api/staticmap?size=400x400&maptype=roadmap&markers=color:blue%7Clabel:S%7C-22.839769,-43.282219");
        return browser;
    }

    private void initLabels() {
        textos = new ArrayList<>();
        textos.add(new Label("Ultima leitura de dados:\tSem registros"));
        textos.add(new Label("Data mais recente:  \tSem registros"));
        textos.add(new Label("Data menos recente: \tSem registros"));
        for (Label l : textos)
            l.setFont(new Font("Arial", 14));
    }

    private void initPieChart() {
        pie = new PieChart();
        pie.setTitle("Situacao dos veiculos");
        pie.setPrefWidth(450);
        pie.setPrefHeight(325);
        controller.atualizaPieChart(pie);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Linha de onibus");       
        yAxis.setLabel("Qtd. de veiculos");
 
        bar = new BarChart<String,Number>(xAxis,yAxis);
        bar.setTitle("Numero de veiculos em movimento por linha");
        bar.setPrefWidth(450);
        bar.setPrefHeight(325);
        bar.setLegendVisible(false);
        bar.setBarGap(0.5);
        bar.setCategoryGap(0.5);
        controller.atualizaBarChart(bar);
    }

    private void initTableView() {
        tabela = new TableView<>();

        TableColumn<Dado,String> dataCol = new TableColumn<Dado,String>("Data");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        dataCol.setPrefWidth(115);
        tabela.getColumns().add(dataCol);

        TableColumn<Dado,String> ordemCol = new TableColumn<Dado,String>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        ordemCol.setPrefWidth(54);
        tabela.getColumns().add(ordemCol);

        TableColumn<Dado,String> linhaCol = new TableColumn<Dado,String>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        linhaCol.setPrefWidth(50);
        tabela.getColumns().add(linhaCol);

        TableColumn<Dado,String> latitudeCol = new TableColumn<Dado,String>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        latitudeCol.setPrefWidth(67);
        tabela.getColumns().add(latitudeCol);

        TableColumn<Dado,String> longitudeCol = new TableColumn<Dado,String>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        longitudeCol.setPrefWidth(73);
        tabela.getColumns().add(longitudeCol);

        TableColumn<Dado,String> velocidadeCol = new TableColumn<Dado,String>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
        velocidadeCol.setPrefWidth(80);
        tabela.getColumns().add(velocidadeCol);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
