package dp;

public class rod_cutting2 {
    public static int cutRod(int[] prices, int n){
        int[] dp=new int[n+1];

        for(int j=1;j<=n;j++){
            int maxVal=Integer.MIN_VALUE;
            for(int i=0;i<j;i++){
                maxVal=Math.max(maxVal, prices[i]+dp[j-i-1]);
            }
            dp[j]=maxVal;
        }

        return dp[n];
    }

    public static void main(String[] args){
        int[] prices={1, 5, 8, 9, 10, 17, 17, 20};
        int n=8;

        int maxProfit=cutRod(prices, n);
        System.out.println("Maximum Profit: "+maxProfit);
    }
    
}
