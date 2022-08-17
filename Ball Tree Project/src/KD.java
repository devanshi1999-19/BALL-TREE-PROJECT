import java.util.Arrays;
import java.util.stream.Stream;

public class KD {
    public static BT kdconstruct(Ball balls[]){
    //     Stream<Ball> ballStream = Arrays.stream(balls);
    //     Stream<Ball> myballStream = ballStream.map(Ball b-> new BT(new BTNode(b)));
    //     Ball[] trees = myballStream.toArray(Ball[]::new);
    // 
    BT[] trees = new BT[balls.length];
    for(int i=0;i<balls.length;i++){
        trees[i] = new BT(new BTNode(balls[i]));
    }
    return null;
    }
    public static Point[] bbox(BT[] trees){
        Point min=new Point(Float.MAX_VALUE, Float.MAX_VALUE);
        Point max=new Point(Float.MIN_VALUE, Float.MIN_VALUE);
        for(BT tree:trees){
            Ball ball=tree.node.entry;
            min.x = Math.min(ball.x, min.x);
            min.y = Math.min(ball.y, min.y);
            max.x = Math.max(ball.x, max.x);
            max.y = Math.max(ball.y, max.y);
        }
        Point[] minmax=new Point[2];
        minmax[0]=min;
        minmax[1]=max;
        return minmax;
    }


}
