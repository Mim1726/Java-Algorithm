import java.util.*;

class Node {
    int val;
    Node left, right;

    Node(int x) {
        val = x;
        left = right = null;
    }
}

public class binary_search_tree {
    static Node insert(Node root, int x) {
        if (root == null) {
            return new Node(x);
        }
        if (x < root.val) {
            root.left = insert(root.left, x);
        } else if (x > root.val) {
            root.right = insert(root.right, x);
        }
        return root;
    }

    static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    static boolean search(Node root,int key){
        if(root==null) return false;
        if(root.val==key) return true;
        if(key<root.val) return search(root.left, key);
        return search(root.right, key);
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Node root=null;

        int n=sc.nextInt();
        
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            root=insert(root,x);
        }
        int key=sc.nextInt();

        if(search(root,key)==true){
            System.out.println("40 found in the BST.");
        }
        System.out.print("In-Order Traversal:");
        inorder(root);
        sc.close();
    }
}
