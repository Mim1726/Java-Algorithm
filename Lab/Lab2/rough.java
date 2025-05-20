//kruskal
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

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

public class KruskalsAlgorithm {

    public static int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(n);

        int totalCost = 0;
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : edges) {
            if (ds.union(edge.src, edge.dest)) {
                totalCost += edge.weight;
                System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        int n = 4; // number of vertices
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        int cost = kruskalMST(n, edges);
        System.out.println("Total cost of MST: " + cost);
    }
}

//prim
import java.util.*;

public class Prim{
    static class Pair{
        int vertex,weight;

        Pair(int v,int w){
            vertex=v;
            weight=w;
        }
    }

    public static int primMST(int n,List<List<Pair>> adj){
        boolean[] visited=new boolean[n];
        PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
        pq.add(new Pair(0, 0));
        int mstWeight=0;

        while(!pq.isEmpty()){
            Pair current=pq.poll();
            if(visited[current.vertex]) continue;

            visited[current.vertex]=true;
            mstWeight+=current.weight;

            for(Pair neighbor:adj.get(current.vertex)){
                if(!visited[neighbor.vertex]){
                    pq.add(new Pair(neighbor.vertex, neighbor.weight));
                }
            }
        }
        return mstWeight;
    }

    public static void main(String[] args){
        int n=4;
        List<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 1));
        adj.get(1).add(new Pair(0, 1));

        adj.get(0).add(new Pair(2, 3));
        adj.get(2).add(new Pair(0, 3));

        adj.get(1).add(new Pair(2, 1));
        adj.get(2).add(new Pair(1, 1));

        adj.get(1).add(new Pair(3, 4));
        adj.get(3).add(new Pair(1, 4));

        adj.get(2).add(new Pair(3, 1));
        adj.get(3).add(new Pair(2, 1));

        System.out.println("Prim MST Total Weight: "+primMST(n,adj));
    }
}


///dijkstra
import java.util.*;

public class Dijkstra{
    static class Pair{
        int node, weight;
        Pair(int node, int weight){
            this.node=node;
            this.weight=weight;
        }
    }

    public static void dijkstra(int source, List<List<Pair>> adj,int[] dist){
        int n=adj.size();
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source]=0;

        PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
        pq.offer(new Pair(source, 0));

        while(!pq.isEmpty()){
            Pair current=pq.poll();
            int u=current.node;
            int d=current.weight;

            if(d > dist[u]) continue;

            for(Pair neighbor:adj.get(u)){
                int v=neighbor.node;
                int w=neighbor.weight;
                if(dist[v] > dist[u]+w){
                    dist[v]=dist[u]+w;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
    }

    public static void main(String[] args){
        int v=5;
        List<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 10));
        adj.get(0).add(new Pair(2, 3));
        adj.get(1).add(new Pair(2, 1));
        adj.get(1).add(new Pair(3, 2));
        adj.get(2).add(new Pair(1, 4));
        adj.get(2).add(new Pair(3, 8));
        adj.get(2).add(new Pair(4, 2));
        adj.get(3).add(new Pair(4, 7));
        adj.get(4).add(new Pair(3, 9));

        int[] dist=new int[v];
        dijkstra(0, adj, dist);

        System.out.println("Shortest distances from source 0:");
        for(int i=0;i<v;i++){
            System.out.println("To node "+i+"="+dist[i]);
        }
    }
}

//dijkstra shortest path
import java.util.*;

public class Main{
    static class Pair{
        int node, weight;
        Pair(int node, int weight){
            this.node=node;
            this.weight=weight;
        }
    }

    public static void dijkstra(int source, List<List<Pair>> adj,int[] dist,int[] parent){
        int n=adj.size();
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source]=0;

        PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
        pq.offer(new Pair(source, 0));

        while(!pq.isEmpty()){
            Pair current=pq.poll();
            int u=current.node;
            int d=current.weight;

            if(d > dist[u]) continue;

            for(Pair neighbor:adj.get(u)){
                int v=neighbor.node;
                int w=neighbor.weight;
                if(dist[v] > dist[u]+w){
                    dist[v]=dist[u]+w;
                    parent[v]=u;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
    }

    public static void printPath(int[] parent, int dest){
        if (parent[dest] == -1){
            System.out.print(dest);
            return;
        }
        printPath(parent, parent[dest]);
        System.out.print(" -> " + dest);
    }

    public static void main(String[] args){
        int v=5;
        int src=0;
        int dest=5;

        List<List<Pair>> adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Pair(1, 4));
        adj.get(0).add(new Pair(2, 1));
        adj.get(2).add(new Pair(1, 2));
        adj.get(1).add(new Pair(3, 1));
        adj.get(2).add(new Pair(3, 5));
        adj.get(3).add(new Pair(4, 3));
        adj.get(4).add(new Pair(5, 1));

        int[] dist=new int[v];
        int[] parent=new int[v];
        dijkstra(0, adj, dist, parent);

        System.out.println("Shortest distance from " + src + " to " + dest + " is: " + dist[dest]);
        System.out.print("Path: ");
        printPath(parent, dest);
        System.out.println();

    }
}

//bellman ford
import java.util.*;

public class BellmanFord{
    static class Edge{
        int from, to, weight;
        Edge(int from, int to, int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
    }

    public static boolean bellmanFord(int v, List<Edge> edges, int source, int[] dist){
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source]=0;

        for(int i=0;i<v-1;i++){
            for(Edge edge:edges){
                if(dist[edge.from] != Integer.MAX_VALUE &&
                   dist[edge.from]+edge.weight < dist[edge.to]){
                   dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        for(Edge edge:edges){
            if(dist[edge.from] != Integer.MAX_VALUE &&
               dist[edge.from]+edge.weight < dist[edge.to]){
               return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        int v=5;
        List<Edge> edges=new ArrayList<>();

        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        int[] dist=new int[v];
        boolean hasNoNegativeCycle=bellmanFord(v, edges, 0, dist);

        if(hasNoNegativeCycle){
            System.out.println("Shortest distance from source 0:");
            for(int i=0;i<v;i++){
                System.out.println("To node "+i+" = "+dist[i]);
            }
        }
        else{
            System.out.println("Graph contains a negative weight cycle.");
        }
    }
}

// floyd warshall
import java.util.*;

public class FloydWarshall{

    static final int INF=1000000000;

    public static void floydWarshall(int[][] dist,int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k]<INF && dist[k][j]<INF){
                        dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        int n=4;

        int[][] dist={
            {0,5,INF,10},
            {INF,0,3,INF},
            {INF,INF,0,1},
            {INF,INF,INF,0}
        };

        floydWarshall(dist, n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dist[i][j]==INF){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(dist[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
}

//johnson

import java.util.*;

class Edge {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class JohnsonAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    int V; // Number of vertices
    List<Edge> edges;
    List<List<int[]>> adj;

    JohnsonAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
        adj.get(u).add(new int[]{v, w});
    }

    int[] bellmanFord(int src) {
        int[] dist = new int[V + 1]; // Include dummy node
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge e : edges) {
            if (dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) {
                return null; // Negative cycle detected
            }
        }

        return dist;
    }

    int[] dijkstra(int src, List<List<int[]>> newAdj) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];

            if (d > dist[u]) continue;

            for (int[] neighbor : newAdj.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }

    int[][] johnson() {
        // Step 1: Add a dummy node
        int dummy = V;
        for (int i = 0; i < V; i++) {
            edges.add(new Edge(dummy, i, 0));
        }

        // Step 2: Run Bellman-Ford from dummy node
        int[] h = bellmanFord(dummy);
        if (h == null) {
            System.out.println("Graph contains a negative-weight cycle");
            return null;
        }

        // Step 3: Reweight edges
        List<List<int[]>> newAdj = new ArrayList<>();
        for (int i = 0; i < V; i++) newAdj.add(new ArrayList<>());
        for (Edge e : edges) {
            if (e.from == dummy) continue;
            int newWeight = e.weight + h[e.from] - h[e.to];
            newAdj.get(e.from).add(new int[]{e.to, newWeight});
        }

        // Step 4 & 5: Run Dijkstra for all vertices
        int[][] result = new int[V][V];
        for (int u = 0; u < V; u++) {
            int[] dist = dijkstra(u, newAdj);
            for (int v = 0; v < V; v++) {
                if (dist[v] < INF) {
                    result[u][v] = dist[v] - h[u] + h[v]; // Re-adjust weights
                } else {
                    result[u][v] = INF;
                }
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        JohnsonAlgorithm g = new JohnsonAlgorithm(5);
        g.addEdge(0, 1, -1);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 4, 2);
        g.addEdge(3, 2, 5);
        g.addEdge(3, 1, 1);
        g.addEdge(4, 3, -3);

        int[][] result = g.johnson();

        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result.length; j++) {
                    if (result[i][j] == INF) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(result[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}