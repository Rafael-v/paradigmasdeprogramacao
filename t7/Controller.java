import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    private final Model model = new Model();

    public void atualizaTextos(ArrayList<Label> labels) {
        labels.get(0).setText("Ultima leitura de dados:\t" + model.getDataUltimaLeitura());
        labels.get(1).setText("Data mais recente:  \t" + model.getDataMaisRecente());
        labels.get(2).setText("Data menos recente: \t" + model.getDataMenosRecente());
    }

    public void atualizaEndereco(ArrayList<Label> labels, String endStr) {
        int idx;
        String[] endArray = endStr.split(", ");
        // copia cada trecho da string para uma das labels do arraylist
        for (idx = 0; idx < endArray.length; idx++) {
            if (endArray[idx].length() > 40)
                endArray[idx] = endArray[idx].substring(0,36) + "...";
            labels.get(idx).setText(endArray[idx]);
        }
        // retira os restos mortais do endereco anterir, se houver
        for (; idx < labels.size(); idx++)
            labels.get(idx).setText("");
    }

    public void atualizaPieChart(PieChart pie) {
        int total = model.getTotalVeiculos();
        int parados = model.getVeiculosParados();

        pie.setTitle(total + " veiculos na ultima leitura");
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(parados + " parado(s)", parados),
                new PieChart.Data((total-parados) + " em movimento", (total-parados)));

        pie.setData(pieChartData);
    }

    @SuppressWarnings("unchecked")
    public void atualizaBarChart(BarChart bar) {
        XYChart.Series<String,Number> barras = new XYChart.Series<>();

        ArrayList<String> linhas = model.getLinhas();
        for (String l : linhas) {
            int veiculos = model.getVeiculosMovimentoNaLinha(l);
            if (veiculos > 0)
                barras.getData().add(new XYChart.Data<>(l, veiculos));
        }

        bar.getData().clear();
        bar.getData().add(barras);
    }

    public void atualizaWebView(WebView browser, ArrayList<Label> end, double lat, double lng) {
        int width = (int)browser.getWidth();
        int height = (int)browser.getHeight();
        browser.getEngine().load("https://maps.googleapis.com/maps/api/staticmap?size="+width+"x"+height+"&zoom=16&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C"+lat+","+lng);
        atualizaEndereco(end, model.getEndereco(lat, lng));
    }

    public Button getJsonFileButton(TableView<Dado> table, PieChart pie, BarChart bar, ArrayList<Label> labels, Stage stage) {
        FileChooser fc = new FileChooser();
        Button btn = new Button("Buscar em arquivo");
        btn.setFont(new Font("Arial", 14));
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ObservableList<Dado> observable = FXCollections.observableArrayList();
                File file = fc.showOpenDialog(stage);
                if (file != null) {
                    for (Dado d : model.obterDadosArquivo(file))
                        observable.add(d);
                    table.setItems(observable);
                    atualizaTextos(labels);
                    atualizaPieChart(pie);
                    atualizaBarChart(bar);
                }
            }
        });

        return btn;
    }

    public Button getTodasPosicoesButton(TableView<Dado> table, PieChart pie, BarChart bar, ArrayList<Label> labels) {
        Button btn = new Button("Buscar todas linhas");
        btn.setFont(new Font("Arial", 14));
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ObservableList<Dado> observable = FXCollections.observableArrayList();
                for (Dado d : obterTodasPosicoes())
                    observable.add(d);
                table.setItems(observable);
                atualizaTextos(labels);
                atualizaPieChart(pie);
                atualizaBarChart(bar);
            }
        });

        return btn;
    }

    public Button getPosicoesDaLinhaButton(TableView<Dado> table, PieChart pie, BarChart bar, ArrayList<Label> labels) {
        Button btn = new Button("Buscar linha");
        btn.setFont(new Font("Arial", 14));
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Buscar linha");
                dialog.setHeaderText("Informe a linha desejada.\nPara multiplas linhas, separe-as por virgula.\nEx.: 434 ou 415,426,434");
                dialog.setContentText("Digite a(s) linha(s):");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    ObservableList<Dado> observable = FXCollections.observableArrayList();
                    for (Dado d : obterPosicoesDaLinha(result.get()))
                        observable.add(d);
                    table.setItems(observable);
                    atualizaTextos(labels);
                    atualizaPieChart(pie);
                    atualizaBarChart(bar);
                }
            }
        });

        return btn;
    }

    public Button getPosicoesDoOnibusButton(TableView<Dado> table, PieChart pie, BarChart bar, ArrayList<Label> labels) {
        Button btn = new Button("Buscar onibus");
        btn.setFont(new Font("Arial", 14));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Buscar onibus");
                dialog.setHeaderText(null);
                dialog.setContentText("Digite a ordem do onibus:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    ObservableList<Dado> observable = FXCollections.observableArrayList();
                    for (Dado d : obterPosicoesDoOnibus(result.get()))
                        observable.add(d);
                    table.setItems(observable);
                    atualizaTextos(labels);
                    atualizaPieChart(pie);
                    atualizaBarChart(bar);
                }
            }
        });

        return btn;
    }

    private ArrayList<Dado> obterTodasPosicoes() {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
    }

    private ArrayList<Dado> obterPosicoesDaLinha(String linha) {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDaLinha/" + linha);
    }

    private ArrayList<Dado> obterPosicoesDoOnibus(String ordem) {
        return model.obterDados("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterPosicoesDoOnibus/" + ordem);
    }
}
