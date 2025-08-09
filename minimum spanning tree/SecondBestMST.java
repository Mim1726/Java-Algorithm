import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    boolean inMST = false;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);  // Path compression
        return parent[x];
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return false;

        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class SecondBestMST {

    static int N;
    static List<Edge> edges = new ArrayList<>();
    static List<List<Edge>> treeAdj;

    public static int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(n);
        int totalWeight = 0;
        int edgesUsed = 0;

        treeAdj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            treeAdj.add(new ArrayList<>());

        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                edge.inMST = true;
                totalWeight += edge.weight;
                edgesUsed++;

                // Build MST adjacency list (undirected)
                treeAdj.get(edge.src).add(edge);
                treeAdj.get(edge.dest).add(new Edge(edge.dest, edge.src, edge.weight));
            }
        }

        if (edgesUsed != n - 1)
            return Integer.MAX_VALUE;  // MST not possible (disconnected graph)
        return totalWeight;
    }

    // DFS to find the maximum edge weight on the path from u to v in MST
    public static int maxEdgeOnPath(int u, int v, boolean[] visited) {
        if (u == v) return 0;
        visited[u] = true;
        for (Edge edge : treeAdj.get(u)) {
            if (!visited[edge.dest]) {
                int candidate = maxEdgeOnPath(edge.dest, v, visited);
                if (candidate != -1)
                    return Math.max(candidate, edge.weight);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes and edges: ");
        N = sc.nextInt();
        int M = sc.nextInt();

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < M; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(src, dest, w));
        }

        int mstWeight = kruskalMST(N, edges);
        if (mstWeight == Integer.MAX_VALUE) {
            System.out.println("Graph is not connected!");
            return;
        }

        int secondBest = Integer.MAX_VALUE;

        for (Edge edge : edges) {
            if (!edge.inMST) {
                boolean[] visited = new boolean[N];
                int maxEdge = maxEdgeOnPath(edge.src, edge.dest, visited);
                if (maxEdge != -1) {
                    int candidateWeight = mstWeight - maxEdge + edge.weight;
                    if (candidateWeight > mstWeight)  // ensure strictly greater than MST
                        secondBest = Math.min(secondBest, candidateWeight);
                }
            }
        }

        System.out.println("Minimum Spanning Tree weight: " + mstWeight);
        if (secondBest != Integer.MAX_VALUE)
            System.out.println("Second Best MST weight: " + secondBest);
        else
            System.out.println("No Second Best MST exists.");
    }
}
