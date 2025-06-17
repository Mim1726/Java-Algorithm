import java.util.*;

class Node {
    int val;
    Node left, right;

    Node(int x) {
        val = x;
        left = right = null;
    }
}

public class Main {
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

    static int lca(Node root,int n1,int n2){
        if(root==null) return -1;
        if(n1<root.val && n2<root.val){
            return lca(root.left,n1,n2);
        }
        if(n1>root.val && n2>root.val){
            return lca(root.right,n1,n2);
        }
        return root.val;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Node root=null;

        int n=sc.nextInt();
        
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            root=insert(root,x);
        }
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        int ans=lca(root, n1, n2);
        System.out.println(ans);
        /* 
        System.out.print("In-Order Traversal:");
        inorder(root);
        */

        sc.close();
    }
}
