package greedy;

import java.util.*;

// Node structure for Huffman tree
class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '\0'; // Non-leaf/internal node
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

// Comparator for priority queue (min-heap based on frequency)
class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.freq - b.freq;
    }
}

public class huffman_encoding {

    // Recursively generate Huffman codes from the tree
    public static void generateCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;

        // If it's a leaf node
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freqs = new int[n];

        System.out.println("Enter characters:");
        for (int i = 0; i < n; i++) {
            chars[i] = sc.next().charAt(0);
        }

        System.out.println("Enter frequencies:");
        for (int i = 0; i < n; i++) {
            freqs[i] = sc.nextInt();
        }

        // Build Huffman Tree using a priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < n; i++) {
            pq.offer(new Node(chars[i], freqs[i]));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node(left.freq + right.freq, left, right);
            pq.offer(parent);
        }

        Node root = pq.poll();

        // Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        System.out.println("\nHuffman Codes:");
        for (char ch : huffmanCodes.keySet()) {
            System.out.println(ch + ": " + huffmanCodes.get(ch));
        }
    }
}
