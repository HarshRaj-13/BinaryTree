import java.util.*;

public class KthLevelOfTree {
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data)
        {
            this.data = data;
            this.left = null; 
            this.right = null;
        }
    }

    public static void KlevelOrder(Node root , int k) // O(n)
    {
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>(); // queue is for level order traversal 
        q.add(root);
        q.add(null);
        int count = 1;
        while(!q.isEmpty())
        {
            Node curr = q.remove();
            if(curr == null){
                count++;
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else
            {
                if(count == k){
                    System.out.print(curr.data + " ");
                }
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right != null){
                    q.add(curr.right);
                }
            }
        }
    }
    public static void Klevel(Node root , int level , int k)  // O(n)
    {
        if(level == k){
            System.out.print(root.data + " ");
            return;
        }
        Klevel(root.left, level+1, k);
        Klevel(root.right, level+1, k);
    }
    public static void main(String[] args) {
         /*
                        1
                      /  \
                     2    3
                    / \  / \
                   4  5  6  7 
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Klevel(root, 1, 1);
        System.out.println();
        KlevelOrder(root, 1);
    }
}
