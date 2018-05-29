import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Color color;
    private Boolean dotted;

    public Edge(Vertex start_, Vertex end_, int type_, Color color_) {
        start = start_;
        end = end_;
        dotted = (type_ == 1);
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

    public Boolean dotted() {
        return dotted;
    }

    public String getColorHex() {
        return String.format("#%02X%02X%02X", 
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
    }

    public int numIntersections(ArrayList<Edge> edgeList) {
        int cont = 0;
        for (Edge e : edgeList) {
            if (start == e.getStart() || start == e.getEnd() || end == e.getStart() || end == e.getEnd())
                continue; // ignora colisoes nos vertices
            Shape intersect = Shape.intersect(this.getFullLine(), e.getFullLine());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                cont++;
            }
        }
        return cont;
    }

    private Line getFullLine() {
        return new Line(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
