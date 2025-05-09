import java.util.*;

class Node implements Comparable<Node> {
    int id, cost;
    Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

class Edge {
    int to, weight;
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class DijkstraShortestPath {
    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(List<List<Edge>> adj, int src, int[] dist, int[] parent) {
        int n = adj.size();
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            for (Edge e : adj.get(u)) {
                int v = e.to;
                int weight = e.weight;

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Node(v, dist[v]));
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
        int n = 5; // number of nodes
        int src = 0;
        int dest = 4;

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Example graph
        // Add edges: u -> v with weight w
        adj.get(0).add(new Edge(1, 10));
        adj.get(0).add(new Edge(2, 3));
        adj.get(1).add(new Edge(2, 1));
        adj.get(1).add(new Edge(3, 2));
        adj.get(2).add(new Edge(1, 4));
        adj.get(2).add(new Edge(3, 8));
        adj.get(2).add(new Edge(4, 2));
        adj.get(3).add(new Edge(4, 7));
        adj.get(4).add(new Edge(3, 9));

        int[] dist = new int[n];
        int[] parent = new int[n];

        dijkstra(adj, src, dist, parent);

        System.out.println("Shortest distance from " + src + " to " + dest + " is: " + dist[dest]);
        System.out.print("Path: ");
        printPath(parent, dest);
        System.out.println();
    }
}
