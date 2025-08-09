package greedy;

import java.util.*;

public class CoinChangeGreedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example coin denominations (sorted in descending order for greedy)
        int[] coins = {25, 10, 5, 1};

        System.out.print("Enter amount: ");
        int amount = sc.nextInt();

        List<Integer> result = new ArrayList<>();

        // Greedy approach: take the largest coin possible each time
        for (int coin : coins) {
            while (amount >= coin) {
                amount -= coin;    // Reduce amount
                result.add(coin);  // Store chosen coin
            }
        }

        // Output the result
        System.out.println("Coins used: " + result);
        System.out.println("Total coins: " + result.size());
    }
}
