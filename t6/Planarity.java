import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

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
    private long startTime;

    public Planarity() {
        menu = new Interface();
        graphGenerator = new GraphGenerator();
        graphPane = new Pane();
        vertexPressed = null;
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        toLevel(1);
        setMouseActions();

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
        startTime = System.currentTimeMillis();
        menu.setLevel(level);
        menu.refreshPane(graphPane, graph);
    }

    private void checkLevelComplete() {
        if (graph.getIntersections() != 0)
            return;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Objetivo atingido");
        alert.setHeaderText(null);
        alert.setContentText("Parabens! Nivel " + menu.getLevel() + " concluido em " + ((System.currentTimeMillis() - startTime)/1000) + "s\n" + "Continue para proxima fase...");
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
