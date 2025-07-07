package dp;

import java.util.Scanner;

public class longest_increasing_sequence {

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        int maxLen = 1;

        // Step 1: Initialize all dp[i] = 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Step 2: Build dp table
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Compute LIS length
        int result = lengthOfLIS(arr);
        System.out.println("Length of Longest Increasing Subsequence: " + result);

        scanner.close();
    }
}

