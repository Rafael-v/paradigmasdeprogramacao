import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Vertex {
    private double x, y;
    private Color color;
    private char type;

    public Vertex(double x_, double y_, char type_, Color color_) {
        x = x_;
        y = y_;
        type = type_;
        color = color_;
    }

    public Shape getShape() {
        Shape s;
        switch (type) {
            case 'Q': // quadrado
                s = new Rectangle(x-25, y-25, 50, 50);
                break;
            case 'T': // triangulo
                s = new Polygon(new double[]{x-25, y+25, x+25, y+25, x, y-25});
                break;
            default:  // circulo como default
                s = new Circle(x, y, 25);
        }
        s.setFill(color);
        return s;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public char getType() {
        return type;
    }

    public String getColor() {
        String colorHex = String.format("#%02X%02X%02X", 
            (int)(color.getRed() * 255),
            (int)(color.getGreen() * 255),
            (int)(color.getBlue() * 255));
        return colorHex;
    }
}
