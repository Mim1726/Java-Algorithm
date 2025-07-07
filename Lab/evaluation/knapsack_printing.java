package evaluation;

import java.util.*;

public class knapsack_printing {
    public static void knapsack(int n,int[] weight,int[] value,int W){
        int[][] dp=new int[n+1][W+1];

        for(int i=1;i<=n;i++){
            for(int w=0;w<=W;w++){
                if(weight[i-1]<=w){
                    int include=value[i-1]+dp[i-1][w-weight[i-1]];
                    int exclude=dp[i-1][w];
                    dp[i][w]=Math.max(include,exclude);
                }
                else{
                    dp[i][w]=dp[i-1][w];
                }
            }
        }

        System.out.println(dp[n][W]);

        List<Integer> list=new ArrayList<>();
        int x=dp[n][W];
        int w=W;
        for (int i=n;i>0 && x>0;i--) {
            if (x!=dp[i-1][w]){
                list.add(i);
                x-=value[i-1];
                w-=weight[i-1];
            }
        }

        for(int i=list.size()-1;i>=0;i--) {
            System.out.print(list.get(i));
            if(i != 0){
                System.out.print(" ");
            }else{
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int W=sc.nextInt();

        int[] weight=new int[n];
        int[] value=new int[n];

        for(int i=0;i<n;i++){
            weight[i]=sc.nextInt();
            value[i]=sc.nextInt();
        }
        knapsack(n, weight, value, W);
    }
}
