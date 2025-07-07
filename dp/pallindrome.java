package dp;

import java.util.*;

public class pallindrome{
  
    public static int longest_pallindrome(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        
        for(int i = 0;i< n;i++){
            dp[i][i]=1;
        }
        
        for(int l= 2;l<=n;l++){
            for(int i=0;i<= n-l;i++){
                int j= i+l-1;
                if(s.charAt(i)== s.charAt(j)) {
                    dp[i][j]= 2 + (l== 2 ? 0 : dp[i + 1][j - 1]);
                }else {
                    dp[i][j]= Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
    
    public static int minCnt(String s) {
        int lps = longest_pallindrome(s);
        return s.length() - lps;
    }
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(minCnt(s));
    }
}

