package dp;

public class wine {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][n];

        // Fill the table for all possible lengths
        for (int length = 1; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                int year = n - (j - i); // current year

                if (i == j) {
                    dp[i][j] = year * prices[i];
                } else {
                    int left = year * prices[i] + dp[i + 1][j];
                    int right = year * prices[j] + dp[i][j - 1];
                    dp[i][j] = Math.max(left, right);
                }
            }
        }

        return dp[0][n - 1]; // full range max profit
    }

    public static void main(String[] args) {
        int[] prices = {2, 4, 6, 2, 5};
        int result = maxProfit(prices);
        System.out.println("Maximum Profit: " + result);
    }
}
