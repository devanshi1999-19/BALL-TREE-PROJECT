public class BTNode {
  
    // Entry is a Ball, while left and right are BT's
  Ball entry;
  BT left=null;
  BT right=null;  
    public BTNode(Ball entry,BT left,BT right){
        this.entry=entry;
        this.left=left;
        this.right=right;

    }
    public BTNode(Ball entry){
      this.entry=entry;
    }
    // Returns true iff this is a leaf node
    public boolean leaf () {
        return (this.left==null && this.right==null);
      }
      
    }
