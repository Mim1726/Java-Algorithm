package lab8;

import java.util.HashMap;

public class fibonacci {
    public static int fibMemo(int n,HashMap<Integer, Integer> map){
        if(n<=1) return n;
        if(map.containsKey(n)) return map.get(n);

        int result=fibMemo(n-1, map)+fibMemo(n-2, map);
        map.put(n, result);
        return result;
    }

    public static int fibTab(int n){
        if(n <= 1) return n;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args){
        System.out.println("fibMemo: "+fibMemo(10, new HashMap<>()));
        System.out.println("fibTab: "+fibTab(10));
    }
}
