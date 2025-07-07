

import java.util.*;

public class RoadReversal {

    static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int R;
    static List<Edge> edges;
    static int minCost = Integer.MAX_VALUE;

    static void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) dfs(neighbor, graph, visited);
        }
    }

    static boolean isStronglyConnected(List<Edge> edgeList) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i <= R; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (Edge e : edgeList) {
            graph.get(e.from).add(e.to);
            reverseGraph.get(e.to).add(e.from);
        }

        boolean[] visited = new boolean[R + 1];
        dfs(1, graph, visited);
        for (int i = 1; i <= R; i++) {
            if (!visited[i]) return false;
        }

        visited = new boolean[R + 1];
        dfs(1, reverseGraph, visited);
        for (int i = 1; i <= R; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    static void tryAllDirections(int index, List<Edge> current, int costSoFar) {
        if (index == edges.size()) {
            if (isStronglyConnected(current)) {
                minCost = Math.min(minCost, costSoFar);
            }
            return;
        }

        Edge e = edges.get(index);

        // Try original direction (cost 0)
        current.add(new Edge(e.from, e.to, 0));
        tryAllDirections(index + 1, current, costSoFar);
        current.remove(current.size() - 1);

        // Try reversed direction (pay cost)
        current.add(new Edge(e.to, e.from, 0));
        tryAllDirections(index + 1, current, costSoFar + e.cost);
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            R = sc.nextInt();
            if (R == 0) break;

            edges = new ArrayList<>();
            minCost = Integer.MAX_VALUE;

            for (int i = 0; i < R; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                edges.add(new Edge(a, b, c));
            }

            tryAllDirections(0, new ArrayList<>(), 0);
            System.out.println(minCost);
        }
    }
}
