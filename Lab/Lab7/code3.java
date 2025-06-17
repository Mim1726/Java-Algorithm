import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner((System.in));
        int n=sc.nextInt();
        sc.nextLine();

        String s=sc.nextLine();
        String[] x=s.split(",");
        int[] prices=new int[n];

        for(int i=0;i<n;i++){
            prices[i]=Integer.parseInt(x[i].trim());
        }

        int maxProfit=maxProfit(prices);
        System.out.println(maxProfit);
    }

    public static int maxProfit(int[] prices){
        int minPrice=Integer.MAX_VALUE;
        int maxProfit=0;

        for(int price:prices){
            if(price<minPrice){
                minPrice=price;
            }
            int profit=price-minPrice;
            if(profit>maxProfit){
                maxProfit=profit;
            }
        }
        return maxProfit;
    }
}
