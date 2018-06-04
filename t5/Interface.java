import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Interface {
    private String btnStyle, menuStyle;
    private ArrayList<Button> btn;
    private ColorPicker cp;
    private int vertexType, edgeType, selected;

    public Interface() {
        btnStyle = "-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px; -fx-border-radius: 20;";
        menuStyle = "-fx-background-color: linear-gradient(#3c3c3c, #000000); -fx-padding: 10.0;";
        btn = new ArrayList<Button>();
        cp = new ColorPicker(Color.BLACK);
        vertexType = edgeType = selected = 0;
    }

    public HBox getToolbar() {
        Label title = new Label("Editor de Grafos    ");
        title.setStyle("-fx-font: 20px \"Georgia\"; -fx-text-fill: #f0f0f0;");

        Button vertex = new Button("Vertice");
        Button edge = new Button("Aresta");
        toolbarButtons(vertex, edge);

        ChoiceBox vertexBtn = newChoiceBox(new String[]{"Circulo", "Quadrado", "Triangulo"}, 0);
        ChoiceBox edgeBtn = newChoiceBox(new String[]{"Normal", "Tracejado"}, 1);

        HBox toolbar = new HBox(title, vertex, vertexBtn, edge, edgeBtn, cp);
        toolbar.setSpacing(10);
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setStyle(menuStyle);
        return toolbar;
    }

    public HBox getMenu() {
        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle(menuStyle);
        for (Button b : btn)
            menu.getChildren().add(b);
        return menu;
    }

    private void toolbarButtons(Button vertex, Button edge) {
        String on = "-fx-background-color: linear-gradient(#e6e6e6, #999999); -fx-font-weight: bold;";
        String off = "-fx-background-color: #e6e6e6;";
        vertex.setStyle(on);
        edge.setStyle(off);

        vertex.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                vertex.setStyle(on);
                edge.setStyle(off);
                selected = 0;
            }
        });

        edge.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                vertex.setStyle(off);
                edge.setStyle(on);
                selected = 1;
            }
        });
    }

    private ChoiceBox newChoiceBox(String[] options, int type) {
        ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(options));
        cb.getSelectionModel().select(0);
        cb.setTooltip(new Tooltip("Selecione um formato para " + (type==0 ? "o vertice." : "a aresta.")));

        cb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (type == 0)
                    vertexType = Arrays.asList(options).indexOf(cb.getValue().toString());
                else
                    edgeType = Arrays.asList(options).indexOf(cb.getValue().toString());
            }
        });
        return cb;
    }

    public void createButtons(Pane graphPane, Graph graph) {
        btn.add(buttonNewGraph(graphPane, graph));
        btn.add(buttonSaveGraph(graph));
        btn.add(buttonCharacteristics(graph));
        btn.add(buttonLeave());
        for (Button b : btn)
            b.setStyle(btnStyle);
    }

    private Button buttonNewGraph(Pane graphPane, Graph graph) {
        Button b = new Button("Novo Grafo");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Novo Grafo");
                alert.setHeaderText("Deseja criar um novo grafo?");
                alert.setContentText("O grafo atual sera perdido.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    graphPane.getChildren().clear();
                    graph.reset();
                }
            }
        });
        return b;
    }

    private Button buttonSaveGraph(Graph graph) {
        Button b = new Button("Salvar em SVG");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                TextInputDialog dialog = new TextInputDialog("grafo");
                dialog.setTitle("Salvar Grafo");
                dialog.setHeaderText("Salvar grafo em SVG");
                dialog.setContentText("Digite o nome do arquivo:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    graph.saveSVG(result.get());

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Salvar Grafo");
                    alert.setHeaderText(null);
                    alert.setContentText("Grafo salvo em " + result.get() + ".html");
                    alert.showAndWait();
                }
            }
        });
        return b;
    }

    private Button buttonCharacteristics(Graph graph) {
        Button b = new Button("Caracteristicas");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Caracteristicas");
                alert.setHeaderText("Caracteristicas do Grafo");
                alert.setContentText("Numero de vertices: " + graph.getVerticesSize() + "\nNumero de arestas: " + graph.getEdgesSize() + "\nNumero de arestas sobrepostas: " + graph.getIntersections());
                alert.showAndWait();
            }
        });
        return b;
    }

    private Button buttonLeave() {
        Button b = new Button("Sair");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Sair");
                alert.setHeaderText("Sair do Editor de Grafos");
                alert.setContentText("Deseja sair?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(0);
                }
            }
        });
        return b;
    }

    public int getSelected() {
        return selected;
    }

    public int getVertexType() {
        return vertexType;
    }

    public int getEdgeType() {
        return edgeType;
    }

    public Color getCurrentColor() {
        return cp.getValue();
    }
}
