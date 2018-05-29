import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

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

    public void reset() {
        vertices.clear();
        edges.clear();
        intersections = 0;
    }
}
