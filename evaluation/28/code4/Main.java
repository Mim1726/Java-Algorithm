import java.util.*;

public class Main{
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reverseGraph = new ArrayList<>();
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();
    static int[] kingdom;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // number of planets
        int m = sc.nextInt();  // number of teleporters

        // Initialize graphs
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // Read teleporters
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }

        // 1st DFS pass to fill stack
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs1(i);
            }
        }

        // 2nd DFS pass on the reversed graph to find components
        visited = new boolean[n + 1];
        kingdom = new int[n + 1];
        int label = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                label++;
                dfs2(node, label);
            }
        }

        // Output
        System.out.println(label); // number of kingdoms
        for (int i = 1; i <= n; i++) {
            System.out.print(kingdom[i] + " ");
        }
    }

    static void dfs1(int node) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor);
            }
        }
        stack.push(node);
    }

    static void dfs2(int node, int label) {
        visited[node] = true;
        kingdom[node] = label;
        for (int neighbor : reverseGraph.get(node)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, label);
            }
        }
    }
}
