import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
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
import javafx.scene.shape.Shape;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rafael Vales
 */
public class Planarity extends Application {

    private Interface menu;
    private GraphGenerator graphGenerator;
    private Pane graphPane;
    private Graph graph;
    private Vertex vertexPressed;

    public Planarity() {
        menu = new Interface();
        graphGenerator = new GraphGenerator();
        graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #e6e6e6;");
        vertexPressed = null;
        graph = null;
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        setMouseActions();
        toLevel(1);

        menu.createButtons(this);
        borderPane.setCenter(graphPane);
        borderPane.setBottom(menu.getBottom());

        Scene scene = new Scene(borderPane, 900, 700);
        stage.setScene(scene);
        stage.setTitle("Planarity");
        stage.setResizable(false);
        stage.show();
    }

    private void setMouseActions() {
        graphPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                vertexPressed = graph.findVertex(e.getX(), e.getY());
            }
        });

        graphPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (vertexPressed == null)
                    return;
                vertexPressed.setX(e.getX());
                vertexPressed.setY(e.getY());
                menu.refreshInfo(graph);
            }
        });

        graphPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                vertexPressed = null;
                checkLevelComplete();
            }
        });
    }

    public void toLevel(int level) {
        graph = graphGenerator.newPlanarGraph(level);
        graphPane = menu.getPane(graphPane);

        for (Edge e : graph.edges)
            graphPane.getChildren().add(e.getLine());
        for (Vertex v : graph.vertices)
            graphPane.getChildren().add(v.getCircle());

        menu.setLevel(level);
        graph.shuffle();
        menu.refreshInfo(graph);
    }

    private void checkLevelComplete() {
        if (graph.getIntersections() != 0)
            return;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Objetivo atingido");
        alert.setHeaderText(null);
        alert.setContentText("Parabens! Nivel " + menu.getLevel() + " concluido\n" + "Continue para proxima fase...");
        alert.showAndWait();

        toLevel(menu.getLevel()+1);
    }


    public Graph getGraph() {
        return graph;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
