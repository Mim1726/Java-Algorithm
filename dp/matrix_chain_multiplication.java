package dp;

public class matrix_chain_multiplication {

    public static int matrixChainOrder(int[] dims) {
        int n = dims.length;

        // dp[i][j] stores min cost of multiplying from i to j matrices
        int[][] dp = new int[n][n];

        // gap = chain length
        for (int gap = 2; gap < n; gap++) {
            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;

                // Try all possible splits
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + dims[i] * dims[k] * dims[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[0][n - 1]; // Final answer
    }

    public static void main(String[] args) {
        // Example: 4 matrices: 10x20, 20x30, 30x40, 40x30
        //int[] dimensions = {10, 20, 30, 40, 30};
        int[] dimensions = {4, 3, 5, 2, 6};

        int minCost = matrixChainOrder(dimensions);
        System.out.println("Minimum number of multiplications: " + minCost);
    }
}
