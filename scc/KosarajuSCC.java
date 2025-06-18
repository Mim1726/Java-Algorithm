package scc;

import java.util.*;

public class KosarajuSCC {
    static class Graph {
        int V;
        List<List<Integer>> adj;
        
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
        }

        // Step 1: Fill stack with finishing times
        void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    fillOrder(neighbor, visited, stack);
                }
            }
            stack.push(v);
        }

        // Step 2: Reverse the graph
        Graph getTranspose() {
            Graph transposed = new Graph(V);
            for (int v = 0; v < V; v++) {
                for (int neighbor : adj.get(v)) {
                    transposed.addEdge(neighbor, v);
                }
            }
            return transposed;
        }

        // Step 3: DFS on transposed graph
        void DFSUtil(int v, boolean[] visited, List<Integer> component) {
            visited[v] = true;
            component.add(v);
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    DFSUtil(neighbor, visited, component);
                }
            }
        }

        // Find and print all SCCs
        void printSCCs() {
            Stack<Integer> stack = new Stack<>();

            // Step 1: Fill stack with finishing times
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    fillOrder(i, visited, stack);
                }
            }

            // Step 2: Transpose the graph
            Graph transposed = getTranspose();

            // Step 3: Process all vertices in order defined by stack
            Arrays.fill(visited, false);
            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visited[v]) {
                    List<Integer> component = new ArrayList<>();
                    transposed.DFSUtil(v, visited, component);
                    System.out.println("SCC: " + component);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Strongly Connected Components:");
        g.printSCCs();
    }
}

