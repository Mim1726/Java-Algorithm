import java.util.*;

public class TransitiveClosure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        // Initialize transitive closure matrix as a copy of the original graph
        int[][] tc = new int[n][n];
        for (int i = 0; i < n; i++)
            tc[i] = Arrays.copyOf(graph[i], n);

        // Floyd-Warshall to compute transitive closure
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (tc[i][k] == 1 && tc[k][j] == 1)
                        tc[i][j] = 1;

        // Print result
        System.out.println("Transitive Closure Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(tc[i][j] + " ");
            System.out.println();
        }
    }
}
