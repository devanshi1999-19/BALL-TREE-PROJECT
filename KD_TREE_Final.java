// import java.util.Scanner;

// import javax.lang.model.util.ElementScanner6;

public class KD_TREE_Final
{
    static int k=2;
    
    class Node{
        int point[]=new int[k];
        Node left , right;
    }
    
    static Node newNode(int arr[])
    {
        KD_TREE_Final m=new KD_TREE_Final();
        Node temp=m.start();
        
        
        for(int i=0;i<k;i++)
        {
            temp.point[i]=arr[i];
        }
        
        temp.left=temp.right=null;
        return temp;
    }
    public Node start()
    {
        Node temp=new Node();
        return temp;
    }
    
    static Node insertRec(Node root, int point[],int depth)
    {
        if(root==null)
        return newNode(point);
        
        int cd= depth%k;
        
        if(point[cd]<root.point[cd])
            root.left=insertRec(root.left,point,depth+1);
        else
            root.right=insertRec(root.right,point,depth+1);
            
        return root;
    }
    
    static Node insert(Node root, int point[])
    {
        return insertRec(root,point,0);
    }
    
    static boolean arePointsSame(int point1[],int point2[])
    {
        for(int i=0;i<k;i++)
        {
            if(point1[i]!=point2[i])
                return false;
        }
        
        return true;
    }
    
   static  Node minNode(Node x, Node y, Node z, int d)
    {
        Node res = x;
        if (y != null && y.point[d] < res.point[d])
           res = y;
        if (z != null && z.point[d] < res.point[d])
           res = z;
        return res;
    }
    
    static Node findMinRec(Node root, int d, int depth)
    {
        // Base cases
        if (root == null)
            return null;
      
        // Current dimension is computed using current depth and total
        // dimensions (k)
        int cd = depth % k;
      
        // Compare point with root with respect to cd (Current dimension)
        if (cd == d)
        {
            if (root.left == null)
                return root;
            return findMinRec(root.left, d, depth+1);
        }
      
        // If current dimension is different then minimum can be anywhere
        // in this subtree
        return minNode(root,
                   findMinRec(root.left, d, depth+1),
                   findMinRec(root.right, d, depth+1), d);
    }

    static Node findMin(Node root, int d)
    {
        // Pass current level or depth as 0
        return findMinRec(root, d, 0);
    }
    
    static void copyPoint(int p1[], int p2[])
    {
       for (int i=0; i<k; i++)
           p1[i] = p2[i];
    }
    
    static Node deleteNodeRec(Node root, int point[], int depth)
    {
        // Given point is not present
        if (root == null)
            return null;
      
        // Find dimension of current node
        int cd = depth % k;
      
        // If the point to be deleted is present at root
        if (arePointsSame(root.point, point))
        {
            // 2.b) If right child is not NULL
            if (root.right != null)
            {
                // Find minimum of root's dimension in right subtree
                Node min = findMin(root.right, cd);
      
                // Copy the minimum to root
                copyPoint(root.point, min.point);
      
                // Recursively delete the minimum
                root.right = deleteNodeRec(root.right, min.point, depth+1);
            }
            else if (root.left != null) // same as above
            {
                Node min = findMin(root.left, cd);
                copyPoint(root.point, min.point);
                root.right = deleteNodeRec(root.left, min.point, depth+1);
            }
            else // If node to be deleted is leaf node
            {
                //delete root;
                return null;
            }
            return root;
        }
      
        // 2) If current node doesn't contain point, search downward
        if (point[cd] < root.point[cd])
            root.left = deleteNodeRec(root.left, point, depth+1);
        else
            root.right = deleteNodeRec(root.right, point, depth+1);
        return root;
    }
    
    static Node deleteNode(Node root, int point[])
    {
       // Pass depth as 0
       return deleteNodeRec(root, point, 0);
    }
    
   static  boolean searchRec(Node root, int point[], int depth)
    {
        // Base cases
        if (root == null)
            return false;
        if (arePointsSame(root.point, point))
            return true;
      
        // Current dimension is computed using current depth and total
        // dimensions (k)
        int cd = depth % k;
      
        // Compare point with root with respect to cd (Current dimension)
        if (point[cd] < root.point[cd])
            return searchRec(root.left, point, depth + 1);
      
        return searchRec(root.right, point, depth + 1);
    }
    
   static  boolean search(Node root, int point[])
    {
        // Pass current depth as 0
        return searchRec(root, point, 0);
    }

    static void printTree(Node root)
    {
        if(root==null)
        return ;
        printTree(root.left);
        System.out.println(root.point[0]+" , "+root.point[1]);
        printTree(root.right);
    }
	public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // Scanner sc = new Scanner(System.in);
		Node root = null;
        int i=0;
        int x[] = new int[2];
        while(i<100000)
        {
            int input = rd_gen(0,2);
            // int input=sc.nextInt();
            switch(input){
                case 0:
                    System.out.println("Insertion : ");
                    x[0] = rd_gen(-100,100);//<x>
                    x[1] = rd_gen(-100,100);//<y>
                    System.out.println(x[0]+" "+x[1]);
                    root = insert(root, x);
                    System.out.println();
                    break;
                case 1:
                    System.out.println("Search : ");
                    System.out.println("Enter the co-ordinates of the point: <x> <y>");
     
                    int query_x = rd_gen(-100,100);//<x>
                    int query_y = rd_gen(-100,100);//<y>
                    System.out.println(query_x+" "+query_y);
             
                    int query[] = { query_x, query_y };
                    if(search(root, query))
                    System.out.println("Found");
                    else
                    System.out.println("Not Found");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Delete node: ");
                    // System.out.println("Select node to delete from : ");
                    // printTree(root);
                    if(root!=null)
                    {System.out.println("Point to be deleted is: "+ root.point[0]+" , "+root.point[1]);
                    root = deleteNode(root, root.point);
                    System.out.println("After deletion tree is: ");
                    // printTree(root);
                    }
                    else 
                    System.out.println("EMPTY TREE");
                    break;
                default:
                    System.out.println("Nodes are : ");
                    //flag=false;
                    printTree(root);
                    System.out.println();
        }
        i++;
    }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("TIME ELAPSED: "+elapsedTime+"ms");
	}
    public static int rd_gen(int min, int max){
            
        //Generate random int value from 0 to 2
       int input = (int)Math.floor(Math.random()*(max-min+1)+min);
       return input;
              
  }
}
