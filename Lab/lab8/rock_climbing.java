package lab8;

public class rock_climbing {
    public static int rockClimbing(int[][] wall){
        int n=wall.length;
        int m=wall[0].length;

        int[][] dp=new int[n][m];

        for(int j=0;j<m;j++){
            dp[n-1][j]=wall[n-1][j];
        }

        for(int i=n-2;i>=0;i--){
            for(int j=0;j<m;j++){
                int maxBelow=dp[i+1][j];
                if(j>0){
                    maxBelow=Math.max(maxBelow, dp[i+1][j-1]);
                }
                if(j<m-1){
                    maxBelow=Math.max(maxBelow, dp[i+1][j+1]);
                }
                dp[i][j]=wall[i][j]+maxBelow;
            }
        }

        int maxEnergy=0;
        for(int j=0;j<m;j++){
            maxEnergy=Math.max(maxEnergy, dp[0][j]);
        }

        return maxEnergy;
    }

    public static void main(String[] args){
        int[][] wall={
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Max energy collected: "+rockClimbing(wall));
    }
}