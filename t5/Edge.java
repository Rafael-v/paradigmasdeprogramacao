import javafx.scene.shape.Line;

public class Edge {
    public Vertex start;
    public Vertex end;

    public Edge(Vertex v1, Vertex v2) {
        start = v1;
        end = v2;
    }

    public Line getLine() {
        return new Line(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
