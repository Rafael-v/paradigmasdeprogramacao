import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Rafael Vales
 */
public class View extends Application {
    private final Controller controller = new Controller();
    private ArrayList<Label> labelsDates;
    private TableView<Dado> table;
    private BarChart<String,Number> bar;
    private PieChart pie;
    private WebView browser;

    @Override
    public void start(Stage stage) {
        initLabels();
        initTableView();
        initPieChart();
        initBarChart();
        initWebView();

        // Pane esquerdo
        VBox vbLeft = getVBox();
        vbLeft.getChildren().addAll(pie, new Separator(), bar);

        // Pane central
        VBox vbCenter = getVBox();

        final Label titulo = new Label("  Monitoramento de frota de onibus\nurbano na cidade do Rio de Janeiro");
        titulo.setFont(new Font("Arial", 22));
        VBox vbTitle = getVBox();
        vbTitle.getChildren().addAll(titulo);

        HBox hbButtons = getHBox();
        hbButtons.getChildren().addAll(
            controller.getTodasPosicoesButton(table, pie, bar, labelsDates),
            controller.getPosicoesDaLinhaButton(table, pie, bar, labelsDates),
            controller.getPosicoesDoOnibusButton(table, pie, bar, labelsDates));

        VBox vbDates = getVBox();
        for (Label l : labelsDates)
            vbDates.getChildren().add(l);

        vbCenter.getChildren().addAll(vbTitle, table, hbButtons, new Separator(), vbDates);

        // Pane direito
        VBox vbRight = new VBox();
        vbRight.getChildren().addAll(browser);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vbLeft);
        borderPane.setCenter(vbCenter);
        borderPane.setRight(vbRight);

        Scene scene = new Scene(borderPane, 1350, 650);
        stage.setScene(scene);
        stage.show();
    }

    private void initLabels() {
        labelsDates = new ArrayList<>();
        labelsDates.add(new Label()); // Label ultima leitura de dados do servidor
        labelsDates.add(new Label()); // Label data-hora mais recente lida do servidor
        labelsDates.add(new Label()); // Label data-hora menos recente lida do servidor
        for (Label l : labelsDates)
            l.setFont(new Font("Arial", 14));
        controller.atualizaTextos(labelsDates);
    }

    private void initPieChart() {
        pie = new PieChart();
        pie.setPrefWidth(450);
        pie.setPrefHeight(325);
        controller.atualizaPieChart(pie);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Linha de onibus");       
        yAxis.setLabel("Qtd. de veiculos");
 
        bar = new BarChart<>(xAxis,yAxis);
        bar.setTitle("Numero de veiculos em movimento por linha");
        bar.setPrefWidth(450);
        bar.setPrefHeight(325);
        bar.setLegendVisible(false);
        //bar.setBarGap(0.5);
        //bar.setCategoryGap(0.5);
        controller.atualizaBarChart(bar);
    }
    
    private WebView initWebView() {
        browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        browser.setPrefWidth(400);
        browser.setPrefHeight(625);
        webEngine.load("https://maps.googleapis.com/maps/api/staticmap?size=400x625&maptype=roadmap&markers=color:blue%7Clabel:S%7C-22.839769,-43.282219");
        return browser;
    }

    private void initTableView() {
        table = new TableView<>();

        TableColumn<Dado,String> dataCol = new TableColumn<>("Data");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        dataCol.setPrefWidth(115);
        table.getColumns().add(dataCol);

        TableColumn<Dado,String> ordemCol = new TableColumn<>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        ordemCol.setPrefWidth(54);
        table.getColumns().add(ordemCol);

        TableColumn<Dado,String> linhaCol = new TableColumn<>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        linhaCol.setPrefWidth(50);
        table.getColumns().add(linhaCol);

        TableColumn<Dado,String> latitudeCol = new TableColumn<>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        latitudeCol.setPrefWidth(67);
        table.getColumns().add(latitudeCol);

        TableColumn<Dado,String> longitudeCol = new TableColumn<>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        longitudeCol.setPrefWidth(73);
        table.getColumns().add(longitudeCol);

        TableColumn<Dado,String> velocidadeCol = new TableColumn<>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
        velocidadeCol.setPrefWidth(80);
        table.getColumns().add(velocidadeCol);
    }

    private VBox getVBox() {
        VBox vb = new VBox();
        vb.setSpacing(5);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setAlignment(Pos.CENTER);
        return vb;
    }

    private HBox getHBox() {
        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
