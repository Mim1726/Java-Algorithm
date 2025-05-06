import java.util.*;

// Edge class to store two vertices and their connecting weight
class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    // Sorting by weight (ascending)
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

// Disjoint Set Union (Union-Find) with path compression
class DSU {
    int[] parent;

    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    // Find with path compression
    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // Union by parent
    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) return false;
        parent[pu] = pv;
        return true;
    }
}

public class KruskalAlgorithm {
    public static int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges); // Sort edges by weight
        DSU dsu = new DSU(n);
        int mstWeight = 0;

        System.out.println("Edges in MST:");
        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) {
                mstWeight += edge.weight;
                System.out.println(edge.u + " - " + edge.v + " (Weight: " + edge.weight + ")");
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int n = 4; // Number of vertices

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 4));
        edges.add(new Edge(2, 3, 1));

        int totalWeight = kruskalMST(n, edges);
        System.out.println("Total Weight of MST: " + totalWeight);
    }
}
