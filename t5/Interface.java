import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Interface {
    private String btnCSS, menuCSS;
    private ArrayList<Button> btn;
    private ColorPicker cp;
    private int selected;
    private char vertexType;
    private char edgeType;

    public Interface() {
        btnCSS = "-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px; -fx-border-radius: 20;";
        menuCSS = "-fx-background-color: linear-gradient(#3c3c3c, #000000); -fx-padding: 10.0;";
        btn = new ArrayList<Button>();
        cp = new ColorPicker(Color.BLACK);
        selected = 0;
        vertexType = 'C';
        edgeType = 'N';
    }

    public HBox getToolbar() {
        Label title = new Label("Editor de Grafos    ");
        title.setStyle("-fx-font: 20px \"Georgia\"; -fx-text-fill: #f0f0f0");

        Button vertex = new Button("Vertice");
        Button edge = new Button("Aresta");
        optionsButtons(vertex, edge);

        ChoiceBox vertexBtn = newChoiceBox(new String[]{"Circulo", "Quadrado", "Triangulo"}, 0);
        ChoiceBox edgeBtn = newChoiceBox(new String[]{"Normal", "Tracejado"}, 1);

        HBox toolbar = new HBox(title, vertex, vertexBtn, edge, edgeBtn, cp);
        toolbar.setSpacing(10);
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setStyle(menuCSS);
        return toolbar;
    }

    public void optionsButtons(Button vertex, Button edge) {
        String on = "-fx-background-color: linear-gradient(#e6e6e6, #999999)";
        String off = "-fx-background-color: #e6e6e6";
        vertex.setStyle(on);
        edge.setStyle(off);

        vertex.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                selected = 0;
                vertex.setStyle(on);
                edge.setStyle(off);
            }
        });

        edge.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                selected = 1;
                vertex.setStyle(off);
                edge.setStyle(on);
            }
        });
    }

    public ChoiceBox newChoiceBox(String[] options, int type) {
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList (options));
        cb.getSelectionModel().select(0);
        cb.setTooltip(new Tooltip("Selecione um formato para " + (type==0 ? "o vertice." : "a aresta.")));
        cb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (type == 0)
                    vertexType = cb.getValue().toString().charAt(0);
                else
                    edgeType = cb.getValue().toString().charAt(0);
                selected = type;
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
            b.setStyle(btnCSS);
    }

    public HBox getMenu() {
        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle(menuCSS);
        for (Button b : btn)
            menu.getChildren().add(b);
        return menu;
    }

    public Button buttonNewGraph(Pane graphPane, Graph graph) {
        Button b = new Button("Novo Grafo");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Novo Grafo");
                alert.setHeaderText("Criando novo grafo...");
                alert.setContentText("O grafo atual sera perdido. Deseja continuar?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    graphPane.getChildren().clear();
                    graph.reset();
                }
            }
        });
        return b;
    }

    public Button buttonSaveGraph(Graph graph) {
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

    public Button buttonCharacteristics(Graph graph) {
        Button b = new Button("Caracteristicas");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Caracteristicas");
                alert.setHeaderText("Caracteristicas do Grafo");
                alert.setContentText("Numero de vertices: " + graph.verticesSize() + "\nNumero de arestas: " + graph.edgesSize() + "\nNumero de arestas sobrepostas: " + graph.overlapSize());
                alert.showAndWait();
            }
        });
        return b;
    }

    public Button buttonLeave() {
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

    public char getVertexType() {
        return vertexType;
    }

    public char getEdgeType() {
        return edgeType;
    }

    public Color getColor() {
        return cp.getValue();
    }
}
