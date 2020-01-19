import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

/**
 * DFS
 */
public class DFS {
    /**
     * Use Stack(LIFO)
     * @param graph
     * @param start
     * @return
     */
    public List<String> dfs(Map<String, List<String>> graph, String start) {
        Stack<String> stack = new Stack<>();
        List<String> visited = new ArrayList<>();
        List<String> path = new ArrayList<>();
        stack.add(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            // remove last the element of stack
            String vertex = stack.pop();
            path.add(vertex);
            List<String> nodes = graph.get(vertex);

            for (String node : nodes) {
                if (!visited.contains(node)) {
                    visited.add(node);
                    stack.push(node);
                }
            }
        }

        return path;
    }

    public static void main(String[] args) {
        DFS d = new DFS();
        Map<String, List<String>> graph = d.createGraph();
        
        System.out.println("Graph:");
        for (String vertice : graph.keySet()) {
            List<String> edges = graph.get(vertice);
            System.out.println("vertice: " + vertice + ", edges: " + Arrays.toString(edges.toArray()));
        }
        System.out.println();

        String[] starts = {"A", "E"};
        for (String start : starts) {
            System.out.println("DFS start from " + start + ": " + Arrays.toString(d.dfs(graph, start).toArray()));
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