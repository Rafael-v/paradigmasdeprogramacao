import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author Rafael Vales
 */
public class Monitoramento extends Application {
    private final Controller controller = new Controller();
    private ArrayList<Label> dates, andress;
    private TableView<Dado> table;
    private BarChart<String,Number> bar;
    private PieChart pie;
    private WebView browser;

    @Override
    public void start(Stage stage) {
        // Inicializacoes
        initLabels();
        initTableView();
        initPieChart();
        initBarChart();
        initWebView();

        // Pane esquerdo
        VBox vbLeft = getVBox();
        vbLeft.getChildren().addAll(pie, new Separator(), bar);

        // Pane central
        final Label titulo = new Label("  Monitoramento de frota de onibus\nurbano na cidade do Rio de Janeiro");
        titulo.setFont(new Font("Arial", 22));
        VBox vbTitle = getVBox();
        vbTitle.getChildren().add(titulo);

        HBox hbButtons = getHBox();
        hbButtons.getChildren().addAll(
            controller.getTodasPosicoesButton(table, pie, bar, dates),
            controller.getJsonFileButton(table, pie, bar, dates, stage),
            controller.getPosicoesDaLinhaButton(table, pie, bar, dates),
            controller.getPosicoesDoOnibusButton(table, pie, bar, dates));

        VBox vbDates = getVBox();
        vbDates.getChildren().addAll(dates);

        VBox vbCenter = getVBox();
        vbCenter.getChildren().addAll(vbTitle, table, hbButtons, new Separator(), vbDates);

        // Pane direito
        VBox vbEndereco = getVBox();
        vbEndereco.getChildren().addAll(andress);

        VBox vbRight = new VBox();
        vbRight.getChildren().addAll(browser, vbEndereco);

        // BorderPane/pane principal
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vbLeft);
        borderPane.setCenter(vbCenter);
        borderPane.setRight(vbRight);

        Scene scene = new Scene(borderPane, 1250, 650);
        stage.setTitle("Monitoramento");
        stage.setScene(scene);
        stage.setMinWidth(1250);
        stage.show();
    }

    private void initLabels() {
        dates = new ArrayList<>();
        dates.add(new Label()); // Label ultima leitura de dados do servidor
        dates.add(new Label()); // Label data-hora mais recente lida do servidor
        dates.add(new Label()); // Label data-hora menos recente lida do servidor
        for (Label l : dates)
            l.setFont(new Font("Arial", 14));
        controller.atualizaTextos(dates);

        andress = new ArrayList<>();
        andress.add(new Label()); // Label rua
        andress.add(new Label()); // Label bairro
        andress.add(new Label()); // Label cidade
        andress.add(new Label()); // Label cep
        andress.add(new Label()); // Label pais
        for (Label l : andress)
            l.setFont(new Font("Arial", 14));
        controller.atualizaEndereco(andress, "Rio de Janeiro - RJ, Brasil");
    }

    private void initPieChart() {
        pie = new PieChart();
        pie.setMinWidth(350);
        pie.setPrefWidth(350);
        controller.atualizaPieChart(pie);
    }

    private void initBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Linha de onibus");       
        yAxis.setLabel("Qtd. de veiculos");
 
        bar = new BarChart<>(xAxis,yAxis);
        bar.setTitle("Numero de veiculos em movimento por linha");
        bar.setMinWidth(350);
        bar.setPrefWidth(350);
        bar.setLegendVisible(false);
        controller.atualizaBarChart(bar);
    }
    
    private void initWebView() {
        int width = 350, height = 500, zoom = 10;
        browser = new WebView();
        browser.setMinWidth(350);
        browser.setPrefSize(width, height);
        browser.getEngine().load("https://maps.googleapis.com/maps/api/staticmap?center=Rio+de+Janeiro,RJ,Brazil&size="+width+"x"+height+"&zoom="+zoom+"&maptype=roadmap");
    }

    @SuppressWarnings("unchecked")
    private void initTableView() {
        table = new TableView<>();
        table.setMinWidth(550);

        // Checa a linha clicada e atualiza as coordenadas do mapa
        // eu odeio unchecked warning
        Callback<TableColumn<Dado,String>,TableCell<Dado,String>> cellFactory = new Callback<TableColumn<Dado,String>,TableCell<Dado,String>>() {
            @Override
            public TableCell<Dado,String> call(TableColumn p) {
                TableCell<Dado,String> cell = new TableCell<Dado,String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText((item == null || empty) ? "" : getItem());
                    }
                };

                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent t) {
                        int id = ((TableCell)t.getSource()).getIndex();
                        controller.atualizaWebView(browser, andress,
                            table.getItems().get(id).getLatitude(),
                            table.getItems().get(id).getLongitude());
                    }
                });
                return cell;
            }
        };

        TableColumn<Dado,String> dataCol = new TableColumn<>("Data");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        dataCol.prefWidthProperty().bind(table.widthProperty().multiply(0.27));
        table.getColumns().add(dataCol);

        TableColumn<Dado,String> ordemCol = new TableColumn<>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());
        ordemCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        table.getColumns().add(ordemCol);

        TableColumn<Dado,String> linhaCol = new TableColumn<>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());
        linhaCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        table.getColumns().add(linhaCol);

        TableColumn<Dado,String> latitudeCol = new TableColumn<>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        latitudeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.16));
        table.getColumns().add(latitudeCol);

        TableColumn<Dado,String> longitudeCol = new TableColumn<>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        longitudeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        table.getColumns().add(longitudeCol);

        TableColumn<Dado,String> velocidadeCol = new TableColumn<>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty());
        velocidadeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.18));
        table.getColumns().add(velocidadeCol);

        for (TableColumn tc : table.getColumns())
            tc.setCellFactory(cellFactory);
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
