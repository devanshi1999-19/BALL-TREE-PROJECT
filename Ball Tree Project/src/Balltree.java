public class Balltree{
    public static void main(String[] args){
        Ball b1=new Ball(1,4,3);
        Ball b2=Ball.copy_from(b1);
        Ball b3=new Ball(21,3,17);
        Ball unionBall;
        //System.out.println(b2.x+":"+b2.y+":"+b2.r);
        //boolean res=b1.equals(b2);
        //System.out.println("result "+res);
        
        
        //boolean res=b1.containsPoint(new Point(1,3));
        //boolean res=b1.intersects(b3);
        Ball[] ballarr={b2,b3};
        //boolean res=b1.enclosesAll(ballarr);
        unionBall=b1.union(b3);
        //System.out.println(unionBall.x+":"+unionBall.y+":"+unionBall.r);
        Ball enclose_ball=Ball.enclose(ballarr);
        System.out.println(enclose_ball.x+" : "+enclose_ball.y+" : "+enclose_ball.r);
        //System.out.println("result "+res);
       
    }
    
}