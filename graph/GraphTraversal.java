package graph;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Edge {
    Vertex n1;
    Vertex n2;
}

class Vertex {
    int id;
    List<Integer> adjVertices = new ArrayList<Integer>();
    public Vertex(int id) { this.id = id; }
    public Vertex(int id, Integer... adjNodes) {
        this(id);
        for(Integer n : adjNodes)
            addAdjecent(n);
    }
    public void addAdjecent(Integer n) { adjVertices.add(n); }
    
    @Override public boolean equals(Object object) {
        if(!(object instanceof Vertex))
            return false;
        
        Vertex v = (Vertex) object;
        return v.id == this.id;
    }
    
    @Override public int hashCode() {
        return id;
    }
    
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ");
        for(Integer vertex : adjVertices)
            sb.append(vertex + " ");
        return sb.toString();
    }
}

class Graph {
    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Edge> edges = new ArrayList<Edge>();
    
    public Graph(Vertex...vertices) {
        for(Vertex vertex : vertices) {
            addVertex(vertex);
        }
    }
    public void addVertex(Vertex vertex) { vertices.add(vertex); }
    public void addEdge(Edge edge) { edges.add(edge); }
    
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes:\n");
        for(Vertex vertex : vertices)
            sb.append(vertex.toString() + "\n");
        
        sb.append("Edges:\n");
        for(Edge edge : edges)
            sb.append(edge.toString() + "\n");
        
        return sb.toString();
    }
}

public class GraphTraversal {
    static enum COLOR { WHITE, GRAY, BLACK };

    public static Graph init() {
        Vertex v0 = new Vertex(0, 1, 3);
        Vertex v1 = new Vertex(1, 4);
        Vertex v2 = new Vertex(2, 4, 5);
        Vertex v3 = new Vertex(3, 1);
        Vertex v4 = new Vertex(4, 3);
        Vertex v5 = new Vertex(5);

        Graph graph = new Graph(v0, v1, v2, v3, v4, v5);
        
        return graph;
    }

    public static void breadthFirstSearch(Graph graph, int source) {
        System.out.println("\n\n#################### BFS: " + source + " ####################");
        List<Vertex> vertices = graph.vertices;
        COLOR[] colors = new COLOR[vertices.size()];
        int[] dist = new int[vertices.size()];

        Vertex vertex = null;
        for(Vertex v : vertices) {
            if(v.id == source) {
                vertex = v;
                colors[v.id] = COLOR.GRAY;
                dist[v.id] = 0;
            } else {
                colors[v.id] = COLOR.WHITE;
                dist[v.id] = 999999;
            }
        }
        
        if(vertex == null) {
            throw new IllegalArgumentException();
        }

        Queue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(source);
        
        while(queue.peek() != null) {
            Vertex v = vertices.get(queue.poll());
            List<Integer> list = v.adjVertices;
            for(Integer adjVer : list) {
                if(colors[adjVer] == COLOR.WHITE) {
                    colors[adjVer] = COLOR.GRAY;
                    dist[adjVer] = dist[v.id] + 1;
                    queue.offer(adjVer);
                }
            }
            colors[v.id] = COLOR.BLACK;
        }
        
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(colors));
    }

    static COLOR[] colors = null;
    static int[] dist = null;

    public static void depthFirstSearch(Graph graph) {
        System.out.println("\n\n=================================================");
        List<Vertex> vertices = graph.vertices;
        
        for(Vertex v : vertices) {
            colors = new COLOR[vertices.size()];
            dist = new int[vertices.size()];
            
            int time = 0;
            for(Vertex v1 : vertices) {
                colors[v1.id] = COLOR.WHITE;
            }

            if(colors[v.id] == COLOR.WHITE)
                DFSVisit(vertices, v.id, time);
        }

        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(colors));
    }
    
    public static void DFSVisit(List<Vertex> vertices, int source, int time) {
        colors[source] = COLOR.GRAY;
        time++;
        
        Vertex vertex = vertices.get(source);
        for(Integer adjVer : vertex.adjVertices) {
            if(colors[adjVer] == COLOR.WHITE) {
                DFSVisit(vertices, adjVer, time);
            }
            colors[source] = COLOR.BLACK;
            dist[source] = time+1;
            time++;
        }
        System.out.println(Arrays.toString(dist));
        System.out.println(Arrays.toString(colors));
    }

    public static void main(String[] args) {
        Graph graph = init();
        System.out.println(graph.toString());
        
        for (int i = 0; i < 6; i++) {
            breadthFirstSearch(graph, i);
        }
        
        depthFirstSearch(graph);
        
        
        Object[] arguments = {
                new Integer(7),
                new Date(),
                "a disturbance in the Force"
            };

        String result = MessageFormat.format(
            "At {1,time} on {1,date}, there was {2} on planet "
             + "{0,number,integer}.", arguments);
        System.out.println(result); 
    }
}