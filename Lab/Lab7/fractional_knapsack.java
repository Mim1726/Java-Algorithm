import java.util.*;

class Item {
    int value, weight;

    // Constructor
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    // Return value/weight ratio
    public double getRatio() {
        return (double) value / weight;
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(int capacity, Item[] items) {
        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.getRatio(), a.getRatio()));

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity == 0) break;

            if (item.weight <= capacity) {
                // Take the whole item
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // Take the fraction of the item
                totalValue += item.getRatio() * capacity;
                capacity = 0; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of items
        int n = sc.nextInt();
        Item[] items = new Item[n];

        // Input value and weight for each item
        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            int weight = sc.nextInt();
            items[i] = new Item(value, weight);
        }

        // Input knapsack capacity
        int capacity = sc.nextInt();

        // Calculate maximum value and print it
        double maxValue = getMaxValue(capacity, items);
        System.out.println((int) maxValue); // Since expected output is integer

        sc.close();
    }
}
