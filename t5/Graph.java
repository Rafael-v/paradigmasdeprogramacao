import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private int intersections;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        intersections = 0;
    }

    public int getVerticesSize() {
        return vertices.size();
    }

    public int getEdgesSize() {
        return edges.size();
    }

    public int getIntersections() {
        return intersections;
    }

    public Vertex addVertex(double x, double y, int type, Color color) {
        Vertex v = new Vertex(x, y, type, color);
        if ( !v.vertexCollision(vertices) ) {
            vertices.add(v);
            return v;
        }
        return null;
    }

    public Edge addEdge(Vertex start, Vertex end, int type, Color color) {
        if ((start == end) || (findEdge(start, end) != null))
            return null;
        Edge e = new Edge(start, end, type, color);
        edges.add(e);
        intersections += e.numIntersections(edges);
        return e;
    }

    public Vertex findVertex(double x, double y) {
        for (Vertex v : vertices) {
            Shape intersect = Shape.intersect(v.getShape(), new Line(x, y, x, y));
            if (intersect.getBoundsInLocal().getWidth() != -1)
                return v;
        }
        return null;
    }

    public Edge findEdge(Vertex start, Vertex end) {
        for (Edge e : edges) {
            if ((e.getStart() == start && e.getEnd() == end) || (e.getStart() == end && e.getEnd() == start))
                return e;
        }
        return null;
    }

    public void saveSVG(String fileName) {
        try {
            File file = new File(fileName + ".html");
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);

            // find the minimum height and width to draw the graph
            double width = 0, height = 0;
            for(Vertex v : vertices) {
                if (v.getX() > width)  width = v.getX();
                if (v.getY() > height) height = v.getY();
            }
            width += 50; height += 50;

            pw.println("<!-- Made in Graph Editor -->");
            pw.println("<svg height=\"" + height + "\" width=\"" + width + "\">");

            // write edges
            for (Edge e : edges) {
                Vertex v1 = e.getStart();
                Vertex v2 = e.getEnd();
                pw.println("  <line id=\"" + e + "\" x1=\"" + v1.getX() + "\" y1=\"" + v1.getY() + "\" x2=\"" + v2.getX() + "\" y2=\"" + v2.getY() + "\" style=\"stroke:" + e.getColorHex() + ";stroke-width:1px;" + (e.dotted() ? "stroke-dasharray:25px,20px,5px,20px;" : "") + "\"/>");
            }

            // write vertices
            for (Vertex v : vertices) {
                double x = v.getX();
                double y = v.getY();
                double shapeSize = v.getSize();
                switch (v.getType()) {
                    case 0: // circle
                        pw.println("  <circle id=\"" + v + "\" cx=\"" + x + "\" cy=\"" + y + "\" r=\"" + shapeSize + "\" stroke=\"black\" stroke-width=\"0\" fill=\"" + v.getColorHex() + "\" />");
                        break;
                    case 1: // square
                        pw.println("  <rect id=\"" + v + "\" x=\"" + (x-shapeSize) + "\" y=\"" + (y-shapeSize) + "\" width=\"" + (shapeSize*2) + "\" height=\"" + (shapeSize*2) + "\" style=\"fill:" + v.getColorHex() + "\" />");
                        break;
                    case 2: // triangle
                        pw.println("  <polygon id=\"" + v + "\" points=\"" + (x-shapeSize) + ',' + (y+shapeSize) + ' ' + (x+shapeSize) + ',' + (y+shapeSize) + ' ' + (x) + ',' + (y-shapeSize) + "\" style=\"fill:" + v.getColorHex() + "\" />");
                }
            }

            pw.println("</svg>");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        vertices.clear();
        edges.clear();
        intersections = 0;
    }
}
