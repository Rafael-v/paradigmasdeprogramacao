import java.util.ArrayList;

public class GraphGenerator {
    public Graph newPlanarGraph(int level) {
        Graph graph = new Graph();
        Vertex next; Edge e;

        for (int i = 0; i < totalVertex(level); i++) 
            graph.addVertex();
        graph.shuffle();

        ArrayList<Vertex> vertices = graph.getVertices();
        double posX = firstSegment(graph);

        do {
            next = nextVertex(graph, posX);
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

        do {
            graph.shuffle();
        } while (graph.getIntersections() == 0);

        return graph;
    }

    private double firstSegment(Graph graph) {
        Vertex a = nextVertex(graph, 0.0);
        Vertex b = nextVertex(graph, a.getX());
        Vertex c = nextVertex(graph, b.getX());
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(c, a);

        return c.getX();
    }

    private Vertex nextVertex(Graph graph, double posX) {
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
        return (level + 5);
    }
}
