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

    public Shape getLine() {
        Shape line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        if (dotted) line.getStrokeDashArray().addAll(25.0, 20.0, 5.0, 20.0);

        // define a aresta para comecar a partir da borda de cada vertice
        line = Shape.subtract(line, start.getShape());
        line = Shape.subtract(line, end.getShape());

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
        return String.format("#%02X%02X%02X", (int)(color.getRed() * 255), (int)(color.getGreen() * 255), (int)(color.getBlue() * 255));
    }

    public int numIntersections(ArrayList<Edge> edgeList) {
        int cont = 0;
        for (Edge e : edgeList) {
            if (this == e) continue;
            
            // Use a proxima linha para considerar colisao uma aresta tracejada que cruza por outra mas nao a encosta (passa pelo buraco da tracejada)
            Shape intersect = Shape.intersect(this.getFullLine(), e.getFullLine());
            // Use a proxima linha para NAO considerar colisao uma aresta tracejada que cruza por outra mas nao a encosta
            //Shape intersect = Shape.intersect(this.getLine(), e.getLine());

            if (intersect.getBoundsInLocal().getWidth() != -1) {
                cont++;
            }
        }
        return cont;
    }

    private Shape getFullLine() {
        Shape line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        line = Shape.subtract(line, start.getShape());
        line = Shape.subtract(line, end.getShape());
        return line;
    }
}
