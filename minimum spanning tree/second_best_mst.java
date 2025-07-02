import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;
    boolean inMST = false;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UnionFind {
    int[] parent, rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int u) {
        if (parent[u] != u)
            parent[u] = find(parent[u]);
        return parent[u];
    }

    boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv)
            return false;

        if (rank[pu] < rank[pv])
            parent[pu] = pv;
        else if (rank[pu] > rank[pv])
            parent[pv] = pu;
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
        return true;
    }
}

public class second_best_mst {
    static int N;
    static List<Edge> edges = new ArrayList<>();
    static List<List<Edge>> treeAdj;

    static int kruskal(List<Edge> edges, int n) {
        UnionFind uf = new UnionFind(n);
        Collections.sort(edges);
        int totalWeight = 0;
        int count = 0;

        treeAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) treeAdj.add(new ArrayList<>());

        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                edge.inMST = true;
                totalWeight += edge.weight;
                count++;

                // Build MST tree
                treeAdj.get(edge.u).add(edge);
                treeAdj.get(edge.v).add(new Edge(edge.v, edge.u, edge.weight));
            }
        }

        return (count == n - 1) ? totalWeight : Integer.MAX_VALUE;
    }

    // DFS to find the maximum edge weight on the path from u to v
    static int maxEdgeOnPath(int u, int v, boolean[] visited) {
        if (u == v) return 0;
        visited[u] = true;
        for (Edge e : treeAdj.get(u)) {
            if (!visited[e.v]) {
                int result = maxEdgeOnPath(e.v, v, visited);
                if (result != -1)
                    return Math.max(result, e.weight);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of nodes and edges: ");
        N = sc.nextInt();
        int M = sc.nextInt();

        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }

        int mstWeight = kruskal(edges, N);
        if (mstWeight == Integer.MAX_VALUE) {
            System.out.println("Graph is not connected!");
            return;
        }

        int secondBest = Integer.MAX_VALUE;
        for (Edge edge : edges) {
            if (!edge.inMST) {
                boolean[] visited = new boolean[N];
                int maxEdge = maxEdgeOnPath(edge.u, edge.v, visited);
                if (maxEdge != -1)
                    secondBest = Math.min(secondBest, mstWeight - maxEdge + edge.weight);
            }
        }

        System.out.println("Minimum Spanning Tree weight: " + mstWeight);
        if (secondBest != Integer.MAX_VALUE)
            System.out.println("Second Best MST weight: " + secondBest);
        else
            System.out.println("No Second Best MST exists.");
    }
}
