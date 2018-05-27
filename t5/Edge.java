import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Color color;
    private Boolean dotted;

    public Edge(Vertex start_, Vertex end_, Boolean dotted_, Color color_) {
        start = start_;
        end = end_;
        dotted = dotted_;
        color = color_;
    }

    public Line getLine() {
        Line line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        if (dotted) line.getStrokeDashArray().addAll(25.0, 20.0, 5.0, 20.0);
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
