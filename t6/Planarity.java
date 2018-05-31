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

    private Graph graph;
    private GraphGenerator graphGenerator;
    private int level;
    private Vertex vertexPressed;
    private Label info;

    public Planarity() {
        graph = null;
        vertexPressed = null;
        graphGenerator = new GraphGenerator();
        level = 0;
        info = new Label();
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Interface menu = new Interface();
        Pane graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #e6e6e6;");

        setMouseActions(graphPane, menu);
        nextLevel(graphPane, menu);

        menu.createButtons(graph);
        borderPane.setCenter(graphPane);
        borderPane.setBottom(menu.getBottom());

        Scene scene = new Scene(borderPane, 900, 700);
        stage.setScene(scene);
        stage.setTitle("Planarity");
        stage.setResizable(false);
        stage.show();
    }

    private void setMouseActions(Pane graphPane, Interface menu) {
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
                int intersections = graph.checkIntersections();
                info.setText("Nivel " + level + '\n' + "Tempo " + 40.2 + '\n' + intersections + " interseccoes restantes");
            }
        });

        graphPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                vertexPressed = null;
            }
        });
    }

    private void nextLevel(Pane graphPane, Interface menu) {
        menu.setLevel(++level);
        graph = graphGenerator.newPlanarGraph(level);
        graphPane = menu.getPane(graphPane, info);

        for (Edge e : graph.edges)
            graphPane.getChildren().add(e.getLine());
        for (Vertex v : graph.vertices)
            graphPane.getChildren().add(v.getCircle());

        graph.shuffle();
        info.setText("Nivel " + level + '\n' + "Tempo " + 40.2 + '\n' + graph.checkIntersections() + " interseccoes restantes");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
