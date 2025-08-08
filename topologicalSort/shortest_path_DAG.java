import java.util.*;

public class shortest_path_DAG {
    static class Graph {
        int vertices;
        List<List<int[]>> adj; // each element: {neighbor, weight}

        Graph(int v) {
            vertices = v;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int weight) {
            adj.get(u).add(new int[]{v, weight});
        }

        // Function to perform Topological Sort
        void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;

            for (int[] neighbor : adj.get(node)) {
                if (!visited[neighbor[0]]) {
                    topologicalSortUtil(neighbor[0], visited, stack);
                }
            }

            stack.push(node);
        }

        // Function to find shortest paths from a source in a DAG
        void shortestPath(int src) {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[vertices];

            // Step 1: Get topological order
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }

            // Step 2: Initialize distances
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // Step 3: Process vertices in topological order
            while (!stack.isEmpty()) {
                int u = stack.pop();

                // If u has been reached before
                if (dist[u] != Integer.MAX_VALUE) {
                    for (int[] edge : adj.get(u)) {
                        int v = edge[0];
                        int weight = edge[1];
                        if (dist[v] > dist[u] + weight) {
                            dist[v] = dist[u] + weight;
                        }
                    }
                }
            }

            // Step 4: Print distances
            System.out.println("Shortest distances from node " + src + ":");
            for (int i = 0; i < vertices; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println(i + " : INF");
                } else {
                    System.out.println(i + " : " + dist[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        g.shortestPath(1);
    }
}
