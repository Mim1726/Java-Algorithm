package dp;

import java.util.*;

public class lcs {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String x=sc.nextLine();
        String y=sc.nextLine();

        int m=x.length();
        int n=y.length();

        int[][] dp=new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }
                else if(x.charAt(i-1)==y.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println("Length of LCS: "+dp[m][n]);

        // Step 4: Trace back to find the actual LCS

        int i=m, j=n;
        StringBuilder lcs=new StringBuilder();
        while(i>0 && j>0){
            if(x.charAt(i-1)==y.charAt(j-1)){
                lcs.append(x.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        System.out.println("LCS: "+lcs.reverse().toString());
    }
}
