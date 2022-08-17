import java.util.ArrayList;

public class BT{
    BTNode node;
    public BT(BTNode node){
      this.node=node;
    }
    public int height(){
      if (this.node==null) return -1;
      if (this.node.leaf()) return 0;
    return Math.max(this.node.left.height() + 1, this.node.right.height() + 1);
    }
     // Total area of the tree
public float area() {
  if (this.node.leaf()) return this.node.entry.get_area();
  return (
    this.node.entry.get_area() + this.node.left.area() + this.node.right.area()
  );
}

public ArrayList<BTNode> inorder() {
  ArrayList<BTNode> inorder_list=new ArrayList<>();
  if (this.node!=null) {
    if (this.node.left!=null) inorder_list.addAll(this.node.left.inorder());
    inorder_list.add(this.node);
    if (this.node.right!=null) inorder_list.addAll(this.node.right.inorder());
  }
  return inorder_list;
}

public ArrayList<BTNode> level(int l) {
  ArrayList<BTNode> level_list=new ArrayList<>();
  if (l == 0){
    level_list.add(this.node);
    return level_list;
  }
  else {
    if (this.node.left!=null) level_list.addAll(this.node.left.level(l - 1));
    if (this.node.right!=null) level_list.addAll(this.node.right.level(l - 1));
  }
  return level_list;
}

 // Similar to level, but includes leaf nodes if at level shallower than l
 public ArrayList<BTNode> approximation(int l) {
     ArrayList<BTNode> approx_list=new ArrayList<>();
  if (l == 0 || this.node.leaf()) approx_list.add(this.node);
  else {
    if (this.node.left!=null) approx_list.addAll(this.node.left.approximation(l - 1));
    if (this.node.right!=null) approx_list.addAll(this.node.right.approximation(l - 1));
  }
  return approx_list;
}

// Generates all leaf nodes of this tree
public ArrayList<Ball> leafBalls() {
    ArrayList<Ball> leafball_list=new ArrayList<>();
    if (this.node.leaf()) leafball_list.add(this.node.entry);
    else {
      leafball_list.addAll(this.node.left.leafBalls());
      leafball_list.addAll(this.node.right.leafBalls());
    }
    return leafball_list;
  }
  // Generates all leaf notes within distance d of point p

//   public ArrayList<BTNode> within(Point p, float d) {
//     int withinSteps = 0;
//     if (this.node!=null) return BT.traverse(this.node,withinSteps);
//     return null;
//   }

//     public static ArrayList<BTNode> traverse(BTNode node, int withinSteps) {

//     ArrayList<BTNode> traverse_list=new ArrayList<>();
    
//     float dnode = node.entry.dist(p);
//     withinSteps++;
//     if (dnode <= d) {
//       if (node.leaf()) traverse_list.add(node);
//       else {
//         float dleft = node.left.node.entry.dist(p);
//         float dright = node.right.node.entry.dist(p);

//         if (dleft < dright) {
//           traverse_list.addAll(traverse(node.left.node));
//            traverse_list.addAll(traverse(node.right.node));
//         } else {
//            traverse_list.addAll(traverse(node.left.node));
//            traverse_list.addAll(traverse(node.right.node));
//         }
//       }
//     }
//   }


  }