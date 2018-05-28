import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class GraphEditor extends Application {

    private Graph graph;
    private Vertex start, end;

    public GraphEditor() {
        graph = new Graph();
        start = end = null;
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Pane graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #f0f0f0;");
        borderPane.setCenter(graphPane);
        
        Interface menu = new Interface();
        menu.createButtons(graphPane, graph);
        borderPane.setTop(menu.getToolbar());
        borderPane.setBottom(menu.getMenu());

        mouseEvent(graphPane, menu);

        Scene scene = new Scene(borderPane, 700, 800);
        stage.setScene(scene);
        stage.setTitle("Editor de Grafos");
        stage.show();
    }

    public void mouseEvent(Pane graphPane, Interface menu) {
        graphPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (menu.getSelected() == 0) {
                    Vertex vertex = graph.addVertex(e.getX(), e.getY(), menu.getVertexType(), menu.getColor());
                    graphPane.getChildren().add(vertex.getShape());
                } else {
                    if (start == null) {
                        start = graph.findVertex(e.getX(), e.getY());
                    } else {
                        end = graph.findVertex(e.getX(), e.getY());
                        if ( end != null) {
                            Edge edge = graph.addEdge(start, end, menu.getEdgeType(), menu.getColor());
                            if (edge != null) {
                                graphPane.getChildren().add(edge.getLine());
                                start = end = null;
                            }
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
