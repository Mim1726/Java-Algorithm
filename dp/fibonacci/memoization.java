package dp.fibonacci;

public class memoization{
    static int[] dp=new int[100];

    static int fib(int n){
        if(n<=1) return n;
        if(dp[n]!=-1) return dp[n];
        return dp[n]=fib(n-1)+fib(n-2);
    }

    public static void main(String[] args){
        int n=6;
        for(int i=0;i<dp.length;i++){
            dp[i]=-1;
        }

        System.out.println("Fibonacci of "+n+" is "+fib(n));
    }

}
