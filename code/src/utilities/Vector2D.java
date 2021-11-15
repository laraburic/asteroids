package utilities;

import java.util.Vector;

import static java.lang.Math.*;

public final class Vector2D {
    public double x,y;

    public Vector2D(){
        x=0;
        y=0;
    }

    public Vector2D(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Vector2D(Vector2D v){
        this.x=v.x;
        this.y=v.y;
    }

    public Vector2D set(double x,double y){
        this.x=x;
        this.y=y;
        return this;
    }

    public Vector2D set(Vector2D v){
        this.x=v.x;
        this.y=v.y;
        return this;
    }

    public boolean equals(Object o){
        Vector2D v = (Vector2D) o;
        return this.x == v.x && this.y == v.y;
    }

    public String toString(){
        return("x="+this.x+"y="+this.y);
    }

    public double mag(){
        if(!Double.isNaN(this.x)&&!Double.isNaN(this.y)&&!Double.isNaN(hypot(this.x,this.y)))
            return hypot(this.x,this.y);
        return Double.NaN;
    }

    public double angle(){
        if(!Double.isNaN(this.y)&&!Double.isNaN(this.x))
            return Math.atan2(this.y,this.x);
        return Double.NaN;
    }

    public double angle(Vector2D other) {
        double ang = 0;
        if (!Double.isNaN(this.y) && !Double.isNaN(this.x) && !Double.isNaN(other.x) && !Double.isNaN(other.y)) {
            ang = Math.atan2(other.y, other.x) - Math.atan2(this.y, this.x);
            if (ang < -Math.PI)
                ang+=2*Math.PI;
            if (ang > Math.PI)
                ang-=2*Math.PI;
            return ang;
    }
        return Double.NaN;
    }

    public Vector2D add(Vector2D v){
        this.x+=v.x;
        this.y+=v.y;
        return this;
    }

    public Vector2D add(double x,double y){
        this.x+=x;
        this.y+=y;
        return this;
    }

    public Vector2D addScaled(Vector2D v, double fac){
        this.x+=v.x*fac;
        this.y+=v.y*fac;
        return this;
    }

    public Vector2D subtract(Vector2D v){
        this.x-=v.x;
        this.y-=v.y;
        return this;
    }

    public Vector2D subtract(double x, double y){
            this.x =this.x - x;
            this.y =this.y - y;
            return this;
    }

    public Vector2D mult(double fac){
        this.x*=fac;
        this.y*=fac;
        return this;
    }

    public Vector2D rotate(double angle){
        double cos= Math.cos(angle);
        double sin= Math.sin(angle);
        double newx=this.x*cos-this.y*sin;
        double newy=this.x*sin+this.y*cos;
        this.x=newx;
        this.y=newy;
        return this;
    }

    public double dot(Vector2D v){
        if(!Double.isNaN(this.x)&&!Double.isNaN(this.y)&&!Double.isNaN(v.x)&&!Double.isNaN(v.y)&&!Double.isNaN(this.angle(v)))
            return (this.mag()*v.mag()*Math.cos(this.angle(v)));
        return Double.NaN;
    }

    public double dist(Vector2D v){
        if(!Double.isNaN(this.x)&&!Double.isNaN(this.y)&&!Double.isNaN(v.x)&&!Double.isNaN(v.y)) {
            return (new Vector2D(v.x-this.x,v.y-this.y).mag());
        }
        return Double.NaN;
    }

    public Vector2D normalise(){
        Double newx=this.x/this.mag();
        Double newy=this.y/this.mag();
        this.x=newx;
        this.y=newy;
        return this;
    }

    public Vector2D wrap(double w,double h){
        if(this.x<0)
        this.x+=w;
        else if(this.x>=w) this.x-=w;
        if(this.y<0)
        this.y+=h;
        else if(this.y>=h)this.y-=h;
        return this;
    }

    public static Vector2D polar(double angle, double mag){
        double x = mag*Math.cos(angle);
        double y = mag*Math.sin(angle);
        return new Vector2D(x,y);
    }


}
