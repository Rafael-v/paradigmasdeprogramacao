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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {
    private final Model model = new Model();

    public void atualizaTextos(ArrayList<Label> labels) {
        labels.get(0).setText("Ultima leitura de dados:\t" + model.getDataUltimaLeitura());
        labels.get(1).setText("Data mais recente:  \t" + model.getDataMaisRecente());
        labels.get(2).setText("Data menos recente: \t" + model.getDataMenosRecente());
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

    public void atualizaWebView(WebView browser, double latitude, double longitude) {
        browser.getEngine().load("https://maps.googleapis.com/maps/api/staticmap?size=400x625&zoom=16&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C" + latitude + "," + longitude);
    }

    public Button getTodasPosicoesButton(TableView<Dado> table, PieChart pie, BarChart bar, ArrayList<Label> labels) {
        Button btn = new Button("Obter todas linhas");
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
        Button btn = new Button("Filtrar por linha");
        btn.setFont(new Font("Arial", 14));
    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Filtrar por linha");
                dialog.setHeaderText(null);
                dialog.setContentText("Digite a linha:");

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
        Button btn = new Button("Filtrar por onibus");
        btn.setFont(new Font("Arial", 14));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Filtrar por onibus");
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
