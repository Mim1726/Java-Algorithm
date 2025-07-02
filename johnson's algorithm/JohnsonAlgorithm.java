import java.util.*;

class JohnsonAlgorithm {
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
    }

    static final int INF = Integer.MAX_VALUE / 2;

    // Bellman-Ford to calculate h[v]
    static int[] bellmanFord(int V, List<Edge> edges, int source) {
        int[] h = new int[V + 1];
        Arrays.fill(h, INF);
        h[source] = 0;

        for (int i = 0; i < V; i++) {
            for (Edge e : edges) {
                if (h[e.u] != INF && h[e.u] + e.w < h[e.v]) {
                    h[e.v] = h[e.u] + e.w;
                }
            }
        }

        // Check for negative cycles
        for (Edge e : edges) {
            if (h[e.u] != INF && h[e.u] + e.w < h[e.v]) {
                System.out.println("Graph contains a negative weight cycle");
                return null;
            }
        }

        return h;
    }

    // Dijkstra's Algorithm using PriorityQueue
    static int[] dijkstra(List<List<Edge>> adj, int src) {
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;

            for (Edge e : adj.get(u)) {
                if (dist[u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[u] + e.w;
                    pq.offer(new int[]{e.v, dist[e.v]});
                }
            }
        }

        return dist;
    }

    public static void johnson(int V, List<Edge> edges) {
        // Step 1: Add new node 0 and connect to all nodes with weight 0
        List<Edge> extendedEdges = new ArrayList<>(edges);
        for (int i = 1; i <= V; i++) {
            extendedEdges.add(new Edge(0, i, 0));
        }

        // Step 2: Bellman-Ford to get h[v]
        int[] h = bellmanFord(V, extendedEdges, 0);
        if (h == null) return;

        // Step 3: Reweight edges
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
        for (Edge e : edges) {
            int newWeight = e.w + h[e.u] - h[e.v];
            adj.get(e.u).add(new Edge(e.u, e.v, newWeight));
        }

        // Step 4: Run Dijkstra from each node
        System.out.println("All-Pairs Shortest Paths (Original Distances):");
        for (int u = 1; u <= V; u++) {
            int[] d = dijkstra(adj, u);
            for (int v = 1; v <= V; v++) {
                int originalDist = d[v] + h[v] - h[u];
                System.out.print((originalDist >= INF / 2 ? "INF" : originalDist) + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 6;
        List<Edge> edges = List.of(
            new Edge(1, 2, 1),
            new Edge(1, 4, 2),
            new Edge(2, 3, 2),
            new Edge(2, 5, 7),
            new Edge(3, 6, 10),
            new Edge(4, 2, -4),
            new Edge(4, 5, 3),
            new Edge(5, 6, 5),
            new Edge(6, 3, -8)
        );

        johnson(V, edges);
    }
}
