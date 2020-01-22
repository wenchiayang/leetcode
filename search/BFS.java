import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * BFS
 */
public class BFS {
    /**
     * Use Queue(FIFO)
     * @param graph
     * @param start
     * @return
     */
    public List<String> bfs(Map<String, List<String>> graph, String start) {
        LinkedList<String> queue = new LinkedList<>();
        List<String> visited = new ArrayList<>();
        List<String> path = new ArrayList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // remove first the element of queue
            String vertex = queue.poll();
            path.add(vertex);
            List<String> nodes = graph.get(vertex);

            for (String node : nodes) {
                if (!visited.contains(node)) {
                    visited.add(node);
                    queue.add(node);
                }
            }
        }

        return path;
    }

    public static void main(String[] args) {
        BFS b = new BFS();
        Map<String, List<String>> graph = b.createGraph();
        
        System.out.println("Graph:");
        for (String vertice : graph.keySet()) {
            List<String> edges = graph.get(vertice);
            System.out.println("vertice: " + vertice + ", edges: " + Arrays.toString(edges.toArray()));
        }
        System.out.println();

        String[] starts = {"A", "E"};
        for (String start : starts) {
            System.out.println("BFS start from " + start + ": " + Arrays.toString(b.bfs(graph, start).toArray()));
        }
    }

    private Map<String, List<String>> createGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        String[] vertices = {"A", "B", "C", "D", "E", "F"};
        String[][] edges = {
            {"B", "C"}, {"A", "C", "D"}, {"A", "B", "D", "E"}, {"B", "C", "E", "F"}, {"C", "D"}, {"D"}
        };

        for (int i = 0; i < vertices.length; i++) {
            List<String> list = Arrays.asList(edges[i]);
            graph.put(vertices[i], list);
        }
        
        return graph;
    }
}