package dp;

public class max_sum {
    public static int maxSum(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        dp[0]=arr[0];
        int maxSum=dp[0];

        for(int i=1;i<n;i++){
            dp[i]=Math.max(arr[i], dp[i-1]+arr[i]);
            maxSum=Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static void main(String[] args){
        int[] arr={9, -3, 1, 7, -15, 2, 3, -4, 2, -7, 6, -2, 8, 4, -9};
        System.out.println("Maximum subarray sum: "+maxSum(arr));
    }
}