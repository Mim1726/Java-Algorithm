import java.util.*;

public class GridDijkstra {
    static class Cell implements Comparable<Cell> {
        int x, y, cost;
        public Cell(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        public int compareTo(Cell other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // right, down, up, left

    public static int dijkstra(int[][] grid, int sx, int sy, int ex, int ey) {
        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        dist[sx][sy] = grid[sx][sy];
        pq.add(new Cell(sx, sy, dist[sx][sy]));

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int x = curr.x, y = curr.y, cost = curr.cost;

            if (x == ex && y == ey) return cost;

            if (cost > dist[x][y]) continue;

            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    int newCost = cost + grid[nx][ny];
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.add(new Cell(nx, ny, newCost));
                    }
                }
            }
        }
        return -1; // if unreachable
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: grid dimensions
        System.out.print("Enter number of rows (n): ");
        int n = sc.nextInt();
        System.out.print("Enter number of columns (m): ");
        int m = sc.nextInt();

        int[][] grid = new int[n][m];
        System.out.println("Enter the grid values:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();

        // Input: start and end positions
        System.out.print("Enter start cell (sx sy): ");
        int sx = sc.nextInt(), sy = sc.nextInt();
        System.out.print("Enter end cell (ex ey): ");
        int ex = sc.nextInt(), ey = sc.nextInt();

        int result = dijkstra(grid, sx, sy, ex, ey);
        System.out.println("Minimum cost from start to end: " + result);
    }
}
