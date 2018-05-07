package objects;

import field.Field;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
//import org.jbox2d.dynamics.
        
public class Robot {
    
    private final int length=33;
    private final int width=28;
    private final int num;
    //xy is top left corner
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double d = 1.4;
    private final boolean red;
    private int angle;
    private Polygon paint;
    private boolean hasCube;
    AffineTransform transform;
    
    public Robot(int num) {
        this.num = num;
        red = num<3;
        angle = num<3 ? 0 : 180;
        setXY(num);
        transform = new AffineTransform();
        paint = new Polygon();
        setInitPaint(false);
    }
    
    private void setInitPaint(boolean remake) {
        int x2,y2,a2;
        if(remake) {
            x2 = paint.xpoints[0];
            y2 = paint.ypoints[0];
            paint = new Polygon();
            a2=0;
        } else {
            x2=toInt(x);
            y2=toInt(y);
            a2=angle; 
        }
        
        paint.addPoint(x2, y2);
        //System.out.println((int)(x2*Field.PPI) + " " + (int)(y2*Field.PPI) + " " + a2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*length*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*width*Field.PPI)*-1+y2); //multiply by negative one as increasing the y makes it go down
        a2-=90;
        paint.addPoint(x2, y2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*length*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*width*Field.PPI)*-1+y2);
        a2-=90;
        paint.addPoint(x2, y2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*length*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*width*Field.PPI)*-1+y2);
        a2-=90;
        paint.addPoint(x2,y2);
        Rectangle bounds = paint.getBounds();
        x = (bounds.x+bounds.width)/2;
        y = (bounds.y+bounds.height)/2;
    }

    private void setXY(int num) {
        dx = 0;
        dy = 0;
        switch(num) {
            case 0:
                x = Field.LEFTEDGE+1;
                y = (int)(Field.LOWEDGE+50);
                angle = 0;
                break;
            case 1:
                x = Field.LEFTEDGE+1;
                y = Field.MIDDLEY;
                angle = 0;
                break;
            case 2:
                x = Field.LEFTEDGE+1;
                y = (int)(Field.HIGHEDGE-50-width);
                angle = 0;
                break;
            case 3:
                x = Field.RIGHTEDGE-1;
                y = (int)(Field.LOWEDGE+50+width);
                angle = 180;
                break;
            case 4:
                x = Field.RIGHTEDGE-1;
                y = Field.MIDDLEY;
                angle = 180;
                break;
            case 5:
                x = Field.RIGHTEDGE-1;
                y = (int)(Field.HIGHEDGE-50);
                angle = 180;
                break;
        }
        x=toInt(x*Field.PPI);
        y=toInt(y*Field.PPI);
    }
    
    public int toInt(double d) {
        return (int)(Math.round(d));
    }
    
    public void refreshShape(int angle) {
        Point center = calculateCenter(paint); 
        //System.out.println("d0 is " + d0);
        int len = paint.npoints;
        Point2D[] origPts = new Point2D[len];
        for (int i = 0; i < len; i++) {
            origPts[i] = new Point(paint.xpoints[i],paint.ypoints[i]);
        }
        Point2D[] newPts = new Point2D[len];
        AffineTransform.getRotateInstance(Math.toRadians(angle), center.x, center.y)
                .transform(origPts,0,newPts,0,len);
        paint = new Polygon();
        for (int i = 0; i < len; i++) {
            paint.addPoint((int)newPts[i].getX(), (int)newPts[i].getY());
        }
        //System.out.println("Point 1 is " + paint.xpoints[0] +  "," + paint.ypoints[0]);
        if(this.angle%360==0) {
            this.angle=0;
            remakeSquare();
        }
    }
    
    
    
    public void doPhysics(){
        x+=dx;
        y+=dy;
        paint.translate(toInt(dx), toInt(dy));
        dy*=.9;
        dx*=.9;
    }
    
    public Polygon getShape() {
        //System.out.println("New shape given");
        return paint;
    }
    
    public boolean hasCube() {
        return hasCube;
    }
    
    public void setAngle(int diff) {
        angle+=diff;
        if(this.angle>=360) {
            this.angle-=360;
        } else if (this.angle<=-360) {
            this.angle+=360;
        }
        refreshShape(-1*diff);
        //System.out.println("New angle is " + angle);
    }
    
    public Point calculateCenter(Polygon p) {
        Rectangle rect = p.getBounds();
        return new Point(rect.x+rect.width/2, rect.y+rect.height/2);
    }
    
    public void rotate(int angle){
        //d0-=angle;
        setAngle(angle);
    }
    
    public void foward() {
        dy-= (int)(Math.round(Math.sin(Math.toRadians(angle))*d));
        dx+= (int)(Math.round(Math.cos(Math.toRadians(angle))*d));

    }
    
    public void back() {
        dy+= (int)(Math.round(Math.sin(Math.toRadians(angle))*d));
        dx-= (int)(Math.round(Math.cos(Math.toRadians(angle))*d));
    }
    
    private void remakeSquare() {
        setInitPaint(true);
    }
}
