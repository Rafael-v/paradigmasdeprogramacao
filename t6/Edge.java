import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Line shape;

    public Edge(Vertex start_, Vertex end_) {
        start = start_;
        end = end_;
        shape = new Line(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public Line getLine() {
        return shape;
    }

    public void setColor(Color c) {
        shape.setStroke(c);
    }

    public int checkIntersection(ArrayList<Edge> edgeList) {
        for (Edge e : edgeList) {
            if (e.getStart() == start || e.getEnd() == start || e.getStart() == end || e.getEnd() == end)
                continue;
            Shape intersect = Shape.intersect(shape, e.getLine());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                this.setColor(Color.RED);
                return 1;
            }
        }
        this.setColor(Color.BLACK);
        return 0;
    }
}
