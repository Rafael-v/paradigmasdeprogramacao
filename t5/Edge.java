import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Color color;
    private Boolean dotted;

    public Edge(Vertex start_, Vertex end_, char type_, Color color_) {
        start = start_;
        end = end_;
        dotted = (type_ == 'T');
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

    public String getColor() {
        String colorHex = String.format("#%02X%02X%02X", 
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
        return colorHex;
    }
}
