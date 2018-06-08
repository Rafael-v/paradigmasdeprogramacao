import java.util.ArrayList;

public class GraphGenerator {
    private Graph graph;

    public Graph newPlanarGraph(int level) {
        graph = new Graph();
        ArrayList<Vertex> vertices = null;
        Vertex next; Edge e;

        for (int i = 0; i < totalVertex(level); i++) 
            graph.addVertex();
        graph.shuffle();

        vertices = graph.getVertices();
        double posX = firstSegment();

        do {
            next = nextVertex(posX);
            if (next == null) continue;
            for (Vertex v : vertices) {
                if (v.getX() >= posX)
                    continue;
                e = new Edge(next, v);
                if (e.checkIntersection(graph.getEdges()) == 1)
                    continue;
                graph.addEdge(next, v);
                posX = next.getX();
            }
        } while (next != null);

        return graph;
    }

    private double firstSegment() {
        Vertex a = nextVertex(0.0);
        Vertex b = nextVertex(a.getX());
        Vertex c = nextVertex(b.getX());
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(c, a);

        return c.getX();
    }

    private Vertex nextVertex(double posX) {
        ArrayList<Vertex> vertices = graph.getVertices();
        Vertex next = null;
        for (Vertex v : vertices) {
            if (v.getX() <= posX)
                continue;
            else if (next == null)
                next = v;
            else if (v.getX() < next.getX())
                next = v;
        }
        return next;
    }

    private int totalVertex(int level) {
        return (level + 4);
    }
}
