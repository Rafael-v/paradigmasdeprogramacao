import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    public Vertex addVertex(double x, double y, char type, Color color) {
        Vertex v = new Vertex(x, y, type, color);
        vertices.add(v);
        return v;
    }

    public Edge addEdge(Vertex start, Vertex end, Color color) {
        Edge e = new Edge(start, end, color);
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
}
