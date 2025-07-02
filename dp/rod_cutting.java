package dp;

public class rod_cutting {
    public static int cutRod(int[] prices, int n){
        int[] dp=new int[n+1];

        for(int i=1;i<=n;i++){
            int maxVal=Integer.MIN_VALUE;
            for(int j=0;j<i;j++){
                maxVal=Math.max(maxVal, prices[j]+dp[i-j-1]);
            }
            dp[i]=maxVal;
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
