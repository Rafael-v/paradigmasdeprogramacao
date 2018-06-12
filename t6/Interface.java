import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Interface {
    private String menuStyle, titleStyle, infoStyle;
    private ArrayList<Button> btn;
    private Label info;
    private int level;

    public Interface() {
        menuStyle = "-fx-background-color: linear-gradient(#3c3c3c, #000000); -fx-padding: 10.0;";
        titleStyle = "-fx-background-color: linear-gradient(#999999, #e6e6e6); -fx-padding: 40.0;";
        infoStyle = "-fx-font: 20px Verdana; -fx-text-fill: #666666;";
        btn = new ArrayList<Button>();
        info = new Label();
        level = 1;
    }

    public HBox getBottom() {
        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle(menuStyle);
        for (Button b : btn)
            menu.getChildren().add(b);
        return menu;
    }

    public void refreshPane(Pane pane, Graph graph) {
        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: #e6e6e6;");
        pane.getChildren().add(getTop("Planarity"));
        info.setStyle(infoStyle);
        info.setTranslateX(10);
        info.setTranslateY(590);
        pane.getChildren().add(info);
        refreshInfo(graph);
        for (Edge e : graph.edges)
            pane.getChildren().add(e.getLine());
        for (Vertex v : graph.vertices)
            pane.getChildren().add(v.getCircle());
    }

    public HBox getTop(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font: 26px \"Georgia\"; -fx-text-fill: black");

        HBox title = new HBox(label);
        title.setAlignment(Pos.CENTER);
        title.setStyle(titleStyle);
        title.setMinWidth(910);
        return title;
    }

    public void createButtons(Planarity planarity) {
        btn.add(buttonShuffle(planarity));
        btn.add(buttonLevels(planarity));
        btn.add(buttonLeave());
    }

    private Button buttonShuffle(Planarity planarity) {
        Button b = new Button(" Misturar ");
        b.setStyle("-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px;");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Graph g = planarity.getGraph();
                do {
                    g.shuffle();
                } while (g.getIntersections() == 0);
                refreshInfo(g);
            }
        });
        return b;
    }

    private Button buttonLevels(Planarity planarity) {
        Button b = new Button("    Niveis    ");
        b.setStyle("-fx-font: 20px Georgia; -fx-padding: 7 20px 7px 20px;");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ArrayList<String> choices = new ArrayList<String>();
                for (int i = 1; i <= 30; i++)
                    choices.add("Nivel " + i);
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Nivel " + level, choices);
                dialog.setTitle("Alterar dificuldade");
                dialog.setHeaderText(null);
                dialog.setContentText("Escolha uma fase:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent())
                    planarity.toLevel(Integer.parseInt(result.get().substring(6)));
            }
        });
        return b;
    }

    private Button buttonLeave() {
        Button b = new Button("   Sair   ");
        b.setStyle("-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px;");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Sair");
                alert.setHeaderText("Sair de Planarity");
                alert.setContentText("Deseja sair?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(0);
                }
            }
        });
        return b;
    }

    public void refreshInfo(Graph graph) {
        info.setText("Nivel " + level + '\n' + graph.getIntersections() + " arestas com interseccoes");
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level_) {
        level = level_;
    }
}
