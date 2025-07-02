package dp;

public class rock_climbing {
    public static int minDangerPath(int[][] wall){
        int n=wall.length;
        int m=wall[0].length;

        int[][] dp=new int[n][m];

        for(int j=0;j<m;j++){
            dp[n-1][j]=wall[n-1][j];
        }

        for(int i=n-2;i>=0;i--){
            for(int j=0;j<m;j++){
                int minBelow=dp[i+1][j];
                if(j>0){
                    minBelow=Math.min(minBelow, dp[i+1][j-1]);
                }
                if(j<m-1){
                    minBelow=Math.min(minBelow, dp[i+1][j+1]);
                }

                dp[i][j]=wall[i][j]+minBelow;
            }
        }

        int minDanger=dp[0][0];
        for(int j=1;j<m;j++){
            minDanger=Math.min(minDanger, dp[0][j]);
        }

        return minDanger;
    }

    public static void main(String[] args){
        int[][] wall={
            {2, 8, 9, 5, 8},
            {4, 4, 6, 2, 3},
            {5, 7, 5, 6, 1},
            {3, 2, 5, 4, 8}
        };

        System.out.println("Minimum danger path: "+minDangerPath(wall));
    }
}
