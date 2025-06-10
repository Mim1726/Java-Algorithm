package scc;

import java.util.*;

public class TarjanSCC {
    private int timer = 0;
    private int[] ids, low;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private List<List<Integer>> adj;
    private List<List<Integer>> sccs;

    public TarjanSCC(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        ids = new int[n];
        Arrays.fill(ids, -1); // Unvisited
        low = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        sccs = new ArrayList<>();
    }

    // Add a directed edge from u to v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < adj.size(); i++) {
            if (ids[i] == -1) {
                dfs(i);
            }
        }
        return sccs;
    }

    private void dfs(int u) {
        stack.push(u);
        onStack[u] = true;
        ids[u] = low[u] = timer++;

        for (int v : adj.get(u)) {
            if (ids[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (onStack[v]) {
                low[u] = Math.min(low[u], ids[v]);
            }
        }

        // Start of an SCC
        if (ids[u] == low[u]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                scc.add(node);
                if (node == u) break;
            }
            sccs.add(scc);
        }
    }

    public static void main(String[] args) {
        int n = 8;
        TarjanSCC graph = new TarjanSCC(n);

        // Sample graph
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        graph.addEdge(6, 7);

        List<List<Integer>> sccs = graph.findSCCs();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> scc : sccs) {
            System.out.println(scc);
        }
    }
}
