import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge {
    public Vertex start;
    public Vertex end;
    public Color color;

    public Edge(Vertex start_, Vertex end_, Color color_) {
        start = start_;
        end = end_;
        color = color_;
    }

    public Line getLine() {
        Line line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        line.setStroke(color);
        return line;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }
}
