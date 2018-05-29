import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Vertex {
    private double x, y;
    private Color color;
    private int type;

    // set the default shape size
    private static final double size = 25;

    public Vertex(double x_, double y_, int type_, Color color_) {
        x = x_;
        y = y_;
        type = type_;
        color = color_;
    }

    public Shape getShape() {
        Shape shape = null;

        switch (type) {
            case 1: // square
                shape = new Rectangle(x-(size), y-(size), size*2, size*2);
                break;
            case 2: // triangle
                shape = new Polygon(new double[]{x-(size), y+(size), x+(size), y+(size), x, y-(size)});
                break;
            default: // circle
                shape = new Circle(x, y, (size));
        }
        shape.setFill(color);
        return shape;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public double getSize() {
        return size;
    }

    public String getColorHex() {
        return String.format("#%02X%02X%02X", 
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
    }

    public Boolean vertexCollision(ArrayList<Vertex> vertexList) {
        for (Vertex v : vertexList) {
            Shape intersect = Shape.intersect(this.getShape(), v.getShape());
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                return true;
            }
        }
        return false;
    }
}
