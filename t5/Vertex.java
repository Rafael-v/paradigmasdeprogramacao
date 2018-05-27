import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Vertex {
    public double x, y;
    public Color color;
    public char type;

    public Vertex(double x_, double y_, char type_, Color color_) {
        x = x_;
        y = y_;
        type = type_;
        color = color_;
    }

    public Shape createShape() {
        Shape s = null;
        switch (type) {
            case 'C': // circulo
                s = new Circle(x, y, 25);
                break;
            case 'Q': // quadrado
                s = new Rectangle(x-25, y-25, 50, 50);
                break;
            case 'T': // triangulo
                s = new Polygon(new double[]{x-25, y+25, x+25, y+25, x, y-25});
                break;
        }
        s.setFill(color);
        return s;
    }

}
