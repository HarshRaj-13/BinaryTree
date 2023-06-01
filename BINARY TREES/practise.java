import java.util.*;
public class practise{
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }
    }
    public static void inOrder(Node root)
    {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    public static void levelOrder(Node root)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty())
        {
            Node curr = q.remove();
            if(curr == null)
            {
                System.out.println();
                if(q.isEmpty())
                    break;
                else
                    q.add(null);
            }
            else{
                System.out.print(curr.data + " ");
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
    }
    public static int height(Node root)
    {
        if(root == null)
            return 0;
        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(lh, rh) + 1;
    }
    public static int countNode(Node root)
    {
        if(root == null)
            return 0;
        int lcount = countNode(root.left);
        int rcount = countNode(root.right);

        return lcount + rcount + 1;
    }
    public static int sumTree(Node root)
    {
        if(root == null)
            return 0;
        int lsum = sumTree(root.left);
        int rsum = sumTree(root.right);

        return lsum + rsum + root.data;
    }
    public static int dia1(Node root)
    {
        if(root == null)
            return 0;
        int ldia = dia1(root.left);
        int rdia = dia1(root.right);
        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(rdia, ldia), lh + rh + 1);
    }
    static class info{
        int ht;
        int dia;

        info(int ht , int dia){
            this.ht = ht;
            this.dia = dia;
        }
    }
    public static info dia2(Node root)
    {
        if(root == null)    
            return new info(0, 0);
        
        info linfo = dia2(root.left);
        info rinfo = dia2(root.right);
        int ht = Math.max(linfo.ht, rinfo.ht)+1;
        int dia = Math.max(Math.max(linfo.dia, rinfo.dia), linfo.ht + rinfo.ht + 1);

        return new info(ht, dia);
    }
    public static boolean isSubTree(Node root , Node subRoot)
    {
        if(root == null)
            return false;
        if(root.data == subRoot.data){
            if(isIdentical(root , subRoot))
                return true;
        }
        return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
    }
    public static boolean isIdentical(Node node , Node subNode)
    {
        if(node == null && subNode == null)
            return true;
        else if(node == null || subNode == null || node.data != subNode.data)
            return false;
        if(!isIdentical(node.left, subNode.left))
            return false;
        if(!isIdentical(node.right, subNode.right))
            return false;
        return true;
    }
    static class res{
        int hd;
        Node node;

        res(int hd , Node node){
            this.hd = hd;
            this.node = node;
        }
    }
    public static void topView(Node root)
    {
        Queue<res> q = new LinkedList<>();
        HashMap<Integer,Node> map = new HashMap<>();
        int min = 0 ; int max = 0;
        q.add(new res(0, root));
        q.add(null);
        while(!q.isEmpty())
        {
            res curr = q.remove();
            if(curr == null)
            {
                if(q.isEmpty())
                    break;
                else
                    q.add(null);
            }
            else{
                if(!map.containsKey(curr.hd))   
                    map.put(curr.hd, curr.node);
                if(curr.node.left != null){
                    q.add(new res(curr.hd-1, curr.node.left));
                    min = Math.min(min, curr.hd-1);
                }
                if(curr.node.right != null){
                    q.add(new res(curr.hd+1, curr.node.right));
                    max = Math.max(max, curr.hd+1);
                }
            }
        }
        for(int i = min ; i<=max ; i++){
            System.out.print(map.get(i).data + " ");
        }
    }
    public static void kthLevel(Node root , int level , int k)
    {
        
        if(level == k){
            System.out.print(root.data + " ");
            return;
        }
        kthLevel(root.left, level+1, k);
        kthLevel(root.right, level+1, k);
    }
    public static Node lca1(Node root , int n1 , int n2)
    {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root , n1 , path1);
        getPath(root , n2 , path2);

        int i = 0 ;
        for( ; i<path1.size() && i<path2.size() ; i++)
        {
            if(path1.get(i) != path2.get(i))
                break;
        }
        Node lca = path1.get(i-1);
        return lca;

    }
    public static boolean getPath(Node root , int n , ArrayList<Node> path)
    {
        if(root == null)
            return false;
        path.add(root);
        if(root.data == n)
            return true;
        boolean foundLeft = getPath(root.left, n, path);
        boolean foundRight = getPath(root.right, n, path);

        if(foundLeft || foundRight)
            return true;
        path.remove(path.size()-1);
        return false;
    }
    public static Node lca2(Node root , int n1 , int n2)
    {
        if(root == null || root.data == n1 || root.data == n2)
            return root;
        Node leftLca = lca2(root.left, n1, n2);
        Node rightLca = lca2(root.right, n1, n2);

        if(rightLca == null)
            return leftLca;
        if(leftLca == null)
            return rightLca;
        
        return root;
    }
    public static int minDist(Node root , int n1 , int n2)
    {
        Node lca = lca2(root, n1, n2);
        int dist1 = lcaDist(lca , n1);
        int dist2 = lcaDist(lca , n2);

        return dist1 + dist2;
    }
    public static int lcaDist(Node root , int n)
    {
        if(root == null)
            return -1;
        if(root.data == n)
            return 0;
        int leftDist = lcaDist(root.left, n);
        int rightDist = lcaDist(root.right, n);
        
        if(leftDist == -1 && rightDist == -1)
            return -1;
        else if(leftDist == -1)
            return rightDist+1;
        else
            return leftDist+1;
    }
    public static int kAncestor(Node root , int n , int k)
    {
        if(root == null)
            return -1;
        if(root.data == n)
            return 0;
        int left = kAncestor(root.left, n , k);
        int right = kAncestor(root.right, n , k);
        
        if(left == -1 && right == -1)
            return -1;
        int max = Math.max(left, right);
        if(max+1 == k)
            System.out.println("kAncestor is : " + root.data);
        
        return max+1;
        
    }
    public static int sumTrees(Node root)
    {
        if(root == null)
            return 0;
        int leftChild = sumTrees(root.left);
        int rightChild = sumTrees(root.right);
        int data = root.data;
        int newLeft = root.left == null ? 0 : root.left.data;
        int newRight = root.right == null ? 0 : root.right.data;

        root.data = newLeft + newRight + leftChild + rightChild;

        return data;
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

                 //   2    
                //   / \ 
               //   4   5 
       Node subRoot = new Node(2);
       subRoot.left = new Node(4);
       subRoot.right = new Node(5);

       System.out.println("height of a tree is: " + height(root));
       System.out.println("No. of Node in a tree is: " + countNode(root));
       System.out.println("sum Tree is: " + sumTree(root));
       System.out.println("Diameter1 of a Tree is: " + dia1(root));
       System.out.println("Diameter2 of a Tree is: " + dia2(root).dia);
       System.out.println("isSubTree is : " + isSubTree(root, subRoot));
       System.out.print("Top view element of a Tree is: ");topView(root);System.out.println();
       System.out.print("kth level element of tree is: "); kthLevel(root, 0, 2);System.out.println();
       System.out.print("Lca for the given Tree is: " + lca1(root, 4, 5).data);System.out.println();
       System.out.println("Lca2 for the given Tree is: " + lca2(root, 4, 5).data);
       System.out.println("Minimum dist b/w two nodes is: " + minDist(root, 4, 7));kAncestor(root, 4, 1);
       System.out.println();
       System.out.println("sumTree is: " + sumTrees(root)); levelOrder(root);
   }
}