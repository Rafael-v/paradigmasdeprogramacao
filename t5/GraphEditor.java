import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Rafael Vales
 */
public class GraphEditor extends Application {

    private Graph graph;
    private Line drawingEdge;

    public GraphEditor() {
        graph = new Graph();
        drawingEdge = null;
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Pane graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #f0f0f0;");
        
        Interface menuBar = new Interface();
        menuBar.createButtons(graphPane, graph);
        
        borderPane.setCenter(graphPane);
        borderPane.setTop(menuBar.getToolbar());
        borderPane.setBottom(menuBar.getMenu());

        setMouseActions(graphPane, menuBar);

        Scene scene = new Scene(borderPane, 700, 800);
        stage.setScene(scene);
        stage.setTitle("Editor de Grafos");
        stage.show();
    }

    private void setMouseActions(Pane graphPane, Interface menuBar) {
        graphPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (menuBar.getSelected() == 0) {
                    Vertex vertex = graph.addVertex(e.getX(), e.getY(), menuBar.getVertexType(), menuBar.getCurrentColor());
                    if (vertex == null) return; // se nao ha vertice na posicao clicada

                    graphPane.getChildren().add(vertex.getShape());
                } else {
                    Vertex start = graph.findVertex(e.getX(), e.getY());
                    if (start == null) return; // se nao ha vertice na posicao clicada

                    drawingEdge = new Line(start.getX(), start.getY(), e.getX(), e.getY());
                    if (menuBar.getEdgeType() == 1) drawingEdge.getStrokeDashArray().addAll(25.0, 20.0, 5.0, 20.0);
                    drawingEdge.setStroke(menuBar.getCurrentColor());

                    graphPane.getChildren().add(drawingEdge);
                    drawingEdge.toBack();
                }
            }
        });

        graphPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (menuBar.getSelected() == 0) return;
                drawingEdge.setEndX(e.getX());
                drawingEdge.setEndY(e.getY());
            }
        });

        graphPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (menuBar.getSelected() == 0) return;

                graphPane.getChildren().remove(drawingEdge);
                Vertex end = graph.findVertex(e.getX(), e.getY());
                if (end == null) return; // se nao ha vertice no local soltado
                Vertex start = graph.findVertex(drawingEdge.getStartX(), drawingEdge.getStartY());
                Edge edge = graph.addEdge(start, end, menuBar.getEdgeType(), menuBar.getCurrentColor());
                if (edge == null) return; // se ja existe uma aresta nessa posicao
                Line edgeLine = edge.getLine();
                graphPane.getChildren().add(edgeLine);
                edgeLine.toBack();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
