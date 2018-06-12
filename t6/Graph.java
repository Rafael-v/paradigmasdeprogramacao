import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import java.util.Random;

public class Graph {
    public ArrayList<Vertex> vertices;
    public ArrayList<Edge> edges;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    public Vertex addVertex(double x, double y) {
        Vertex v = new Vertex(x, y);
        vertices.add(v);
        return v;
    }

    public Vertex addVertex() {
        return addVertex(0.0, 0.0);
    }

    public Edge addEdge(Vertex start, Vertex end) {
        Edge e = new Edge(start, end);
        edges.add(e);
        start.insertEdge(e);
        end.insertEdge(e);
        return e;
    }

    public Vertex findVertex(double x, double y) {
        for (Vertex v : vertices) {
            Shape intersect = Shape.intersect(v.getCircle(), new Circle(x, y, 1));
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

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getIntersections() {
        int intersections = 0;
        for (Edge e : edges)
            intersections += e.checkIntersection(edges);

        // se todas arestas estao ok, colore de verde
        if (intersections == 0)
            for (Edge e : edges)
                e.setColor(Color.GREEN);

        return intersections;
    }

    public void shuffle() {
        Random rand = new Random();
        for (Vertex v : vertices) {
            v.setX(rand.nextInt(700) + 100.0);
            v.setY(rand.nextInt(400) + 100.0);
        }
    }
}
