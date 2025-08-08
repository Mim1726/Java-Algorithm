import java.util.*;

public class shortest_path {
    static class Pair {
        int node, weight;
        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void dijkstra(int source, List<List<Pair>> adj, int[] dist, int[] parent) {
        int n = adj.size();
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;
            int d = current.weight;

            if (d > dist[u]) continue; // Skip outdated entries

            for (Pair neighbor : adj.get(u)) {
                int v = neighbor.node;
                int w = neighbor.weight;

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    parent[v] = u; // store path
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
    }

    public static void printPath(int[] parent, int dest) {
        if (parent[dest] == -1) {
            System.out.print(dest);
            return;
        }
        printPath(parent, parent[dest]);
        System.out.print(" -> " + dest);
    }

    public static void main(String[] args) {
        int v = 5;
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        // Graph edges
        adj.get(0).add(new Pair(1, 10));
        adj.get(0).add(new Pair(2, 3));
        adj.get(1).add(new Pair(2, 1));
        adj.get(1).add(new Pair(3, 2));
        adj.get(2).add(new Pair(1, 4));
        adj.get(2).add(new Pair(3, 8));
        adj.get(2).add(new Pair(4, 2));
        adj.get(3).add(new Pair(4, 7));
        adj.get(4).add(new Pair(3, 9));

        int[] dist = new int[v];
        int[] parent = new int[v];

        int source = 0;
        int destination = 4;

        dijkstra(source, adj, dist, parent);

        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("To node " + i + " = " + dist[i]);
        }

        System.out.println("\nShortest path from " + source + " to " + destination + ":");
        printPath(parent, destination);
        System.out.println("\nTotal cost: " + dist[destination]);
    }
}
