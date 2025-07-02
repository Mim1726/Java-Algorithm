package dp;

import java.util.*;

public class subset_sum{
    public static boolean isSubsetSum(int[] nums,int target){
        int n=nums.length;
        boolean[][] dp=new boolean[n+1][target+1];

        for(int i=0;i<n;i++){
            dp[i][0]=true;
        }

        for(int i=1;i<=n;i++){
            for(int sum=1;sum<=target;sum++){
                if(nums[i-1]<=sum){
                    dp[i][sum]=dp[i-1][sum] || dp[i-1][sum-nums[i-1]];
                }
                else{
                    dp[i][sum]=dp[i-1][sum];
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        int[] nums=new int[n];

        for(int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }

        int target=sc.nextInt();

        boolean result=isSubsetSum(nums, target);

        if(result){
            System.out.println("Yes, a subset with the target sum exists.");
        }
        else{
            System.out.println("No, such a subset does not exist.");
        }
    }
}