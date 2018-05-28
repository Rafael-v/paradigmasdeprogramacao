import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private int overlaps;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        overlaps = 0;
    }

    public Vertex addVertex(double x, double y, char type, Color color) {
        Vertex v = new Vertex(x, y, type, color);
        vertices.add(v);
        return v;
    }

    public Edge addEdge(Vertex start, Vertex end, char type, Color color) {
        if ((start == end) || (findEdge(start, end) != null))
            return null;
        Edge e = new Edge(start, end, type, color);
        edges.add(e);
        return e;
    }

    public Vertex findVertex(double x, double y) {
        for (Vertex v : vertices) {
            if (v.getX() > x-25 && v.getX() < x+25 && v.getY() > y-25 && v.getY() < y+25)
                return v;
        }
        return null;
    }

    public Edge findEdge(Vertex start, Vertex end) {
        for (Edge e : edges) {
            if ((e.getStart() == start || e.getStart() == end) && (e.getEnd() == start || e.getEnd() == end))
                return e;
        }
        return null;
    }

    public int verticesSize() {
        return vertices.size();
    }

    public int edgesSize() {
        return edges.size();
    }

    public int getOverlaps() {
        return overlaps;
    }

    public void saveSVG(String fileName) {
        double x, y;
        try {
            File file = new File(fileName + ".html");
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);

            pw.println("<svg height=\"700\" width=\"800\">");

            // write edges
            for (Edge e : edges) {
                Vertex v1 = e.getStart();
                Vertex v2 = e.getEnd();
                pw.println("  <line id=\"" + e + "\" x1=\"" + v1.getX() + "\" y1=\"" + v1.getY() + "\" x2=\"" + v2.getX() + "\" y2=\"" + v2.getY() + "\" style=\"stroke:" + e.getColor() + ";stroke-width:1px;" + (e.dotted() ? "stroke-dasharray:25px,20px,5px,20px;" : "") + "\"/>");
            }

            // write vertices
            for (Vertex v : vertices) {
                x = v.getX();
                y = v.getY();
                switch (v.getType()) {
                    case 'C':
                        pw.println("  <circle id=\"" + v + "\" cx=\"" + x + "\" cy=\"" + y + "\" r=\"" + 25 + "\" stroke=\"black\" stroke-width=\"0\" fill=\"" + v.getColor() + "\" />");
                        break;
                    case 'Q':
                        pw.println("  <rect id=\"" + v + "\" x=\"" + (x-25) + "\" y=\"" + (y-25) + "\" width=\"" + 50 + "\" height=\"" + 50 + "\" style=\"fill:" + v.getColor() + "\" />");
                        break;
                    case 'T':
                        pw.println("  <polygon id=\"" + v + "\" points=\"" + (x-25) + ',' + (y+25) + ' ' + (x+25) + ',' + (y+25) + ' ' + (x) + ',' + (y-25) + "\" style=\"fill:" + v.getColor() + "\" />");
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
    }
}
