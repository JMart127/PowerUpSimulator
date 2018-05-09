package objects;

import field.Field;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Cube implements Field {
    
    int x;
    int y;
    boolean isPlaced=false;
    int plate = -1;
    int size = 13;
    Polygon p;
    
    public Cube(int x, int y) {
        this.x=toInt(x*PPI);
        this.y=toInt(y*PPI);
        p = new Polygon();
        drawRect();
    }
    
    public void move(int dx, int dy) {
        p.translate(dx, dy);
        x+=dx;
        y+=dy;
    }
    
    public void draw(int x, int y , int angle) {
        p = new Polygon();
        int x2 = x + toInt(15*Math.sqrt(2)*Math.sin(Math.toRadians(angle+45)));
        int y2 = y + toInt(10*Math.sqrt(2)*Math.cos(Math.toRadians(angle+45)));
        int a2 = angle;
        p.addPoint(x2, y2);
        //System.out.println((int)(x2*Field.PPI) + " " + (int)(y2*Field.PPI) + " " + a2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*size*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*size*Field.PPI)*-1+y2); //multiply by negative one as increasing the y makes it go down
        a2-=90;
        p.addPoint(x2, y2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*size*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*size*Field.PPI)*-1+y2);
        a2-=90;
        p.addPoint(x2, y2);
        x2 = toInt((Math.cos(Math.toRadians(a2))*size*Field.PPI)+x2);
        y2 = toInt((Math.sin(Math.toRadians(a2))*size*Field.PPI)*-1+y2);
        a2-=90;
        p.addPoint(x2,y2);
    }
    
    public void drawRect() {
        p.addPoint(x, y);
        p.addPoint(toInt(x+size*PPI), y);
        p.addPoint(toInt(x+size*PPI), toInt(y+size*PPI));
        p.addPoint(x, toInt(y+size*PPI));
    }
    
    public void refreshShape(int angle) {
        //angle-=angle;
        Point center = calculateCenter(p); 
        //System.out.println("d0 is " + d0);
        int len = p.npoints;
        Point2D[] origPts = new Point2D[len];
        for (int i = 0; i < len; i++) {
            origPts[i] = new Point(p.xpoints[i],p.ypoints[i]);
        }
        Point2D[] newPts = new Point2D[len];
        AffineTransform.getRotateInstance(Math.toRadians(angle), center.x, center.y)
                .transform(origPts,0,newPts,0,len);
        //check to see if can rotate
        p = new Polygon();
        for (int i = 0; i < len; i++) {
            p.addPoint((int)newPts[i].getX(), (int)newPts[i].getY());
        }
    }
    
    public Polygon getShape() {
        return p;
    }

    private int toInt(double d) {
        return (int)(Math.round(d));
    }
    
    private Point calculateCenter(Polygon p) {
        Rectangle rect = p.getBounds();
        return new Point(rect.x+rect.width/2, rect.y+rect.height/2);
    }
}
