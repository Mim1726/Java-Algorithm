import java.util.*;

public class ArticulationPoints {
    private int timer;
    private List<List<Integer>> adj;
    private boolean[] visited;
    private int[] tin, low;
    private Set<Integer> articulationPoints;

    public ArticulationPoints(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[n];
        tin = new int[n];
        low = new int[n];
        articulationPoints = new HashSet<>();
        timer = 0;
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // since undirected graph
    }

    public void findArticulationPoints() {
        for (int i = 0; i < adj.size(); i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }

        System.out.println("Articulation Points: " + articulationPoints);
    }

    private void dfs(int u, int parent) {
        visited[u] = true;
        tin[u] = low[u] = timer++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue; // don't go back to parent

            if (visited[v]) {
                // back edge
                low[u] = Math.min(low[u], tin[v]);
            } else {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);

                if (low[v] >= tin[u] && parent != -1) {
                    articulationPoints.add(u);
                }
                children++;
            }
        }

        if (parent == -1 && children > 1) {
            articulationPoints.add(u);
        }
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes
        ArticulationPoints graph = new ArticulationPoints(n);

        // Example graph
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        graph.findArticulationPoints();
    }
}
