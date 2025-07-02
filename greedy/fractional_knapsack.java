package greedy;

import java.util.*;

class Item {
    int value, weight;

    Item(int value, int weight){
        this.value=value;
        this.weight=weight;
    }

    double getRatio(){
        return (double)value/weight;
    }
}

public class fractional_knapsack{
    public static double getMax(List<Item> items, int capacity){
        items.sort((a,b) -> Double.compare(b.getRatio(), a.getRatio()));

        double totalValue=0.0;
        int currentWeight=0;

        for(Item item:items){
            if(currentWeight+item.weight <= capacity){
                totalValue+=item.value;
                currentWeight+=item.weight;
            }
            else{
                int remain=capacity-currentWeight;
                totalValue+=remain*item.getRatio();
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        List<Item> items=new ArrayList<>();

        for(int i=0;i<n;i++){
            int value=sc.nextInt();
            int weight=sc.nextInt();
            items.add(new Item(value, weight));
        }

        int capacity=sc.nextInt();

        double maxValue=getMax(items, capacity);
        System.out.printf("Maximum value in knapsack = %.2f\n", maxValue);
    }
}