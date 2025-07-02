package dp;

import java.util.*;

public class coin_change {
    public static int minCoins(int[] coins, int target){
        int n=coins.length;
        int[] dp=new int[target+1];

        Arrays.fill(dp, target+1);
        dp[0]=0;

        for(int amount=1;amount<=target;amount++){
            for(int coin:coins){
                if(coin <= amount){
                    dp[amount]=Math.min(dp[amount], dp[amount-coin]+1);
                }
            }
        }
        return dp[target] > target ? -1 : dp[target];
    }

    public static void main(String[] args){
        int[] coins={1, 2, 5};
        int target=20;

        int ans=minCoins(coins, target);

        if (ans != -1) {
            System.out.println("Minimum coins needed: " + ans);
        } else {
            System.out.println("It's not possible to make the amount with given coins.");
        }

    }
}
