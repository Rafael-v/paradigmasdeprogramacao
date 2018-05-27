import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

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

    public Edge addEdge(Vertex start, Vertex end) {
        Edge e = new Edge(start, end);
        edges.add(e);
        return e;
    }

    public int verticesSize() {
        return vertices.size();
    }

    public int edgesSize() {
        return edges.size();
    }
}
