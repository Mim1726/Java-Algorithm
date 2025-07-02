package dp;

import java.util.Scanner;

public class wine {

    public static int countWays(int n) {
        if (n < 0) return 0;

        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1;  // One way to write 0 (empty sum)

        if (n >= 1) dp[1] = 1;  // {1}
        if (n >= 2) dp[2] = 1;  // {1+1}
        if (n >= 3) dp[3] = 2;  // {1+1+1}, {3}

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value of n: ");
        int n = sc.nextInt();

        int ways = countWays(n);
        System.out.println("Number of ways to write " + n + " as sum of 1, 3, and 4 is: " + ways);
    }
}
