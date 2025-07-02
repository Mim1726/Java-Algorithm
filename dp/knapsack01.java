package dp;

import java.util.*;

public class knapsack01 {
    public static int knapsack(int[] values,int[] weights,int W){
        int n=values.length;
        int[][] dp=new int[n+1][W+1];

        for(int i=1;i<=n;i++){
            for(int w=0;w<=W;w++){
                if(weights[i-1]<=w){
                    int include=values[i-1]+dp[i-1][w-weights[i-1]];
                    int exclude=dp[i-1][w];
                    dp[i][w]=Math.max(include,exclude);
                }
                else{
                    dp[i][w]=dp[i-1][w];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args){
        int[] values={12, 10, 20, 15};
        int[] weights={2, 1, 3, 2};
        int capacity=5;

        int maxProfit=knapsack(values, weights, capacity);
        System.out.println("Maximum Profit: "+maxProfit);
    }
}
