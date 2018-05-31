import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Vertex {
    private double x, y;
    private ArrayList<Edge> connectedEdges;
    private Circle shape;

    public Vertex(double x_, double y_) {
        x = x_;
        y = y_;
        connectedEdges = new ArrayList<Edge>();
        shape = new Circle(x_, y_, 16);
    }

    public double getX() {
        return x;
    }

    public void setX(double x_) {
        if (x_ < 16 )
            x_ = 16;
        else if (x_ > 894)
            x_ = 894;

        x = x_;
        shape.setCenterX(x_);

        for (Edge e : connectedEdges) {
            if (e.getStart() == this)
                e.getLine().setStartX(x_);
            else
                e.getLine().setEndX(x_);
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y_) {
        if (y_ < 16 )
            y_ = 16;
        else if (y_ > 635)
            y_ = 635;

        y = y_;
        shape.setCenterY(y_);

        for (Edge e : connectedEdges) {
            if (e.getStart() == this)
                e.getLine().setStartY(y_);
            else
                e.getLine().setEndY(y_);
        }
    }

    public Circle getCircle() {
        return shape;
    }

    public void insertEdge(Edge e) {
        connectedEdges.add(e);
    }
}
