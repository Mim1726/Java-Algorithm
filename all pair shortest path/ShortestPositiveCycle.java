import java.util.*;

public class ShortestPositiveCycle {
    static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of nodes and edges: ");
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] dist = new int[n][n];

        // Initialize distance matrix
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);

        // Set self-distance to 0
        for (int i = 0; i < n; i++)
            dist[i][i] = 0;

        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            dist[u][v] = Math.min(dist[u][v], w);  // handle multiple edges
        }

        // Floyd-Warshall: all-pairs shortest paths
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k] < INF && dist[k][j] < INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        // Find shortest positive cycle
        int minCycle = INF;
        for (int i = 0; i < n; i++) {
            if (dist[i][i] > 0 && dist[i][i] < minCycle)
                minCycle = dist[i][i];
        }

        if (minCycle == INF)
            System.out.println("No positive cycle exists.");
        else
            System.out.println("Shortest positive cycle length: " + minCycle);
    }
}
