import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ball {
    float x;
    float y;
    float r;
    public Ball(float x,float y,float r){
        this.x=x;
        this.y=y;
        this.r=r;
    }
    public static Ball copy_from(Ball other){
        Ball b2=new Ball(other.x,other.y,other.r);
        return b2;
    }
          // returns true iff the smallest enclosing ball of a and b is neither a nor b
  public static boolean isBasis2(Ball a, Ball b) {
    return !a.encloses(b) && !b.encloses(a);
  }
  public static boolean isBasis3(Ball a,Ball b,Ball c) {
    return (
      Ball.isBasis2(a, b) &&
      !a.union(b).encloses(c) &&
      Ball.isBasis2(a, c) &&
      !a.union(c).encloses(b) &&
      Ball.isBasis2(b, c) &&
      !b.union(c).encloses(a)
    );
  }
  // If three balls form a 'basis' (see isBasis3), computes the
  // smallest enclosing circle of these
  public static Ball encloseBasis3(Ball a,Ball b,Ball c) {

    float x1=a.x;
    float y1=a.y;
    float r1=a.r;

    float x2=b.x;
    float y2=b.y;
    float r2=b.r;
    
    float x3=c.x;
    float y3=c.y;
    float r3=c.r;

    float a2 = x1 - x2;
    float a3 = x1 - x3;
    float b2 = y1 - y2;
    float b3 = y1 - y3;
    float c2 = r2 - r1;
    float c3 = r3 - r1;
    float d1 = x1 * x1 + y1 * y1 - r1 * r1;
    float d2 = d1 - x2 * x2 - y2 * y2 + r2 * r2;
    float d3 = d1 - x3 * x3 - y3 * y3 + r3 * r3;
    float ab = a3 * b2 - a2 * b3;
    float xa = (b2 * d3 - b3 * d2) / (ab * 2) - x1;
    float xb = (b3 * c2 - b2 * c3) / ab;
    float ya = (a3 * d2 - a2 * d3) / (ab * 2) - y1;
    float yb = (a2 * c3 - a3 * c2) / ab;
    float A = xb * xb + yb * yb - 1;
    float B = 2 * (r1 + xa * xb + ya * yb);
    float C = xa * xa + ya * ya - r1 * r1;
   
    float r = -(float)(Math.abs(A) > 1e-10
      ? (B + Math.sqrt(B * B - 4 * A * C)) / (2 * A)
      : C / B);
    return new Ball(x1 + xa + xb * r, y1 + ya + yb * r, r);
  }

   // Given a basis B and a circle p ⊈ B, returns the new basis Bʹ.
   public static Ball[] extendBasis(Ball[] B,Ball p) {
    int i, j;
    if(B==null){
      Ball[] newbasis={p};
      return newbasis;
    }
    
    if (p.enclosesAll(B)) {
        Ball[] newbasis={p};
        return newbasis;
    }

    // If we get here then B must have at least one element.
    for (i = 0; i < B.length; ++i) {
      if (!p.encloses(B[i]) && p.union(B[i]).enclosesAll(B)) {
        Ball[] newbasis={B[i],p};
        return newbasis;
      }
      Ball[] nullbasis={};
      return nullbasis;
    }

    // If we get here then B must have at least two elements.
    for (i = 0; i < B.length - 1; ++i) {
      for (j = i + 1; j < B.length; ++j) {
        if (
          !B[i].union(B[j]).encloses(p) &&
          !B[i].union(p).encloses(B[j]) &&
          !B[j].union(p).encloses(B[i]) &&
          Ball.encloseBasis3(B[i], B[j], p).enclosesAll(B)
        ) {
            Ball[] newbasis={B[i],B[j],p};
            return newbasis;
        }
      }
    }
    return B;

    // If we get here then something is very wrong.

    //throw new UserDefinedException("This is user-defined exception");  
  }
  // Returns a ball enclosing all balls in L
  public static Ball enclose(Ball[] L){
    // let { x, y, r } = d3.packEnclose(L);
    // return new Ball(x, y, r);
      int i = 0;
      //testing code for d3.shuffle
      List<Ball> list =Arrays.asList(L); 
      Collections.shuffle(list);
      list.toArray(L);
      int n=L.length;
      // System.out.println("length of L"+L.length);
      //int n = d3.shuffle((L = L.slice())).length;
      Ball[] B=null;
      Ball p;
      Ball e=null;
    while (i < n) {
      p = L[i];
      if (e!=null && e.encloses(p)) ++i;
      else {
        //doubt extendBasis( B is empty)
        B=Ball.extendBasis(B, p);
        // for(int k=0;k<B.length;k++){
        //   System.out.println(B[k].x+":"+B[k].y+":"+B[k].r);
        // }
        e = encloseBasis(B);
        i=0;
      }
    }
    return e;
  }
  

  public static  Ball encloseBasis(Ball[] B){
    switch (B.length) {
      case 1:
        return B[0];
      case 2:
        return B[0].union(B[1]);
      case 3:
        return Ball.encloseBasis3(B[0], B[1], B[2]);
    }
    return null;

  }



    public boolean equals(Ball other){
        float dx=this.x-other.x;
        float dy=this.y-other.y;
        float dr=this.r-other.r;
        return dx*dx ==0 && dy*dy==0 && dr*dr==0;
    }
    
    public boolean encloses(Ball other) {
        float dr = this.r - other.r + (float)1e-6; 
        float dx=this.x-other.x;
        float dy=this.y-other.y;
        return dr >= 0 && dr * dr > dx * dx + dy * dy;
      }
      public boolean containsPoint(Point p) {
        float dx = this.x - p.x;
        float dy = this.y - p.y;
        return dx * dx + dy * dy <= this.r * this.r;
      }
    
      // Returns true iff this ball intersects other
      public boolean intersects(Ball other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy) < this.r + other.r;
      }
    
      // Returns true iff this b ⊆ this for all b ∈ others.
      public boolean enclosesAll(Ball[] others) {
        for(Ball b: others) if (!this.encloses(b)) return false;
        return true;
      }

  

  // returns smallest enclosing ball containing this and other
  public Ball union(Ball other) {
    if (other.encloses(this)) return other;
    if (this.encloses(other)) return this;
    
    float x1=this.x;
    float y1=this.y;
    float r1=this.r;

    float x2=other.x;
    float y2=other.y;
    float r2=other.r;

    float x21 = x2 - x1,
      y21 = y2 - y1,
      r21 = r2 - r1;
    float l = (float)Math.sqrt(x21 * x21 + y21 * y21);
    return new Ball((x1 + x2 + (x21 / l) * r21) / 2,
      (y1 + y2 + (y21 / l) * r21) / 2,
      (l + r1 + r2) / 2
    );
  }
  public Ball expand(Ball other) {
    float x1=this.x;
    float y1=this.y;
    float r1=this.r;

    float x2=other.x;
    float y2=other.y;
    float r2=other.r;
    float x21 = x2 - x1,
      y21 = y2 - y1;
      //r21 = r2 - r1;
    float l =(float) Math.sqrt(x21 * x21 + y21 * y21);
    if (l <= this.r + other.r) return this;

    float R = (l - r2 + r1) / 2;
    return new Ball(x1 + (x21 * (R - r1)) / l, y1 + (y21 * (R - r1)) / l, R);
  }
    // Returns the distance from a point to this ball
    public float dist(Point p) {
        return (float)Math.max(
          0,Math.sqrt((this.x - p.x) *(this.x - p.x) + (this.y - p.y) *(this.y - p.y)) - this.r);
      }
    
      // Area of this ball
      public float get_area() {
        return this.r * this.r * (float)Math.PI;
      }

}