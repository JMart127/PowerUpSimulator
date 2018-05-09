package objects;

import field.Field;
import static field.Field.FENCES;
import static field.Field.MIDDLEX;
import static field.Field.PLATES;
import static field.Field.PLAYINGCORDS;
import static field.Field.PPI;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
        
public class Robot {
    
    private final int length=33;
    private final int width=28;
    private final int num;
    //xy is top left corner
    private double x;
    private double y;
    private double dx;
    private double dy;
    private final double d = 1.4;
    private final boolean red;
    private int angle;
    private Polygon paint;
    private boolean hasCube=false;
    public int cubeNum=-1;
    public Robot[] bots;
    public Cube[] cubes;
    
    public Robot(int num) {
        this.num = num;
        red = num<3;
        angle = num<3 ? 0 : 180;
        setXY(num);
        paint = new Polygon();
        setInitPaint(false);
    }
    
    public void setBotsArray(Robot[] bots) {
        this.bots=bots;
    }
    
    public void setCubeArray(Cube[] cubes) {
        this.cubes=cubes;
    }
    private void setInitPaint(boolean remake) {
        int x2,y2,a2;
        if(remake) {
            if(angle==0) {
                x2 = paint.xpoints[0];
                y2 = paint.ypoints[0];
                paint = new Polygon();
                a2=0; 
            } else {
                x2 = paint.xpoints[0];
                y2 = paint.ypoints[0];
                paint = new Polygon();
                a2=180; 
            }
            
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
    
    private int toInt(double d) {
        return (int)(Math.round(d));
    }
    
    private void refreshShape(int angle) {
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
        //check to see if can rotate
        Polygon temp = new Polygon();
        for (int i = 0; i < len; i++) {
            temp.addPoint((int)newPts[i].getX(), (int)newPts[i].getY());
        }
        if(check(temp)) {
            paint = temp; 
            if(this.angle%360==0||this.angle%180==0) {
                remakeSquare();
            } 
            if(hasCube) {
                cubes[cubeNum].draw(paint.xpoints[0], paint.ypoints[0], this.angle);
            }
        } else {
            this.angle+=angle; //opposite as passed in angle *-1
            dx=0;
            dy=0;
        }        
    }

    public void doPhysics(){
        Polygon temp = new Polygon();
        for (int i = 0; i < paint.npoints; i++) {
            temp.addPoint(paint.xpoints[i]+toInt(dx), paint.ypoints[i]+toInt(dy));
        }
        if(check(temp)) {
            paint = temp;
            x+=dx;
            y+=dy;
            if(hasCube) {
                cubes[cubeNum].draw(paint.xpoints[0], paint.ypoints[0], this.angle);
            }
            dy*=.9;
            dx*=.9;
        } else {
            dy=0;
            dx=0;
        }
        
    }
    
    public Polygon getShape() {
        //System.out.println("New shape given");
        return paint;
    }
    
    public boolean hasCube() {
        return hasCube;
    }
    
    private void setAngle(int diff) {
        angle+=diff;
        if(this.angle>=360) {
            this.angle-=360;
        } else if (this.angle<=-360) {
            this.angle+=360;
        }
        refreshShape(-1*diff);
        //System.out.println("New angle is " + angle);
    }
    
    private Point calculateCenter(Polygon p) {
        Rectangle rect = p.getBounds();
        return new Point(rect.x+rect.width/2, rect.y+rect.height/2);
    }
    
    public void rotate(int angle){
        //d0-=angle;
        setAngle(angle);
    }
    
    public void foward() {
        dy-= Math.sin(Math.toRadians(angle))*d;
        dx+= Math.cos(Math.toRadians(angle))*d;

    }
    
    public void back() {
        dy+= Math.sin(Math.toRadians(angle))*d;
        dx-= Math.cos(Math.toRadians(angle))*d;
    }
    
    private void remakeSquare() {
        setInitPaint(true);
    }
    
    private boolean check(Polygon p) {
        //field
        Polygon field = new Polygon();
        for(int i = 0; i < PLAYINGCORDS[0].length; i++) {
            field.addPoint(toInt(PLAYINGCORDS[0][i]*PPI), toInt(PLAYINGCORDS[1][i]*PPI));
        }
        for (int i = 0; i < p.npoints; i++) {
            if(!field.contains(p.xpoints[i], p.ypoints[i])) {
                return false;
            }
        }

        ArrayList<Shape> pieces = new ArrayList();
        //fences
        Polygon[] fences = new Polygon[2];
        for (int i = 0; i < FENCES.length; i++) {
            fences[i] = new Polygon();
            for (int j = 0; j < FENCES[i][0].length; j++) {
                fences[i].addPoint(toInt(FENCES[i][0][j]*PPI), toInt(FENCES[i][1][j]*PPI));
            }
            pieces.add(fences[i]);
        }
        //scale
        Rectangle scale = new Rectangle(toInt(MIDDLEX*PPI-8*PPI), toInt(PLATES[1][1][2]*PPI), toInt(15*PPI), toInt((PLATES[4][1][2]*PPI)-(PLATES[1][1][2]*PPI)));
        pieces.add(scale);
        
        //robots
        for (int i = 0; i < bots.length; i++) {
            if(i!=num) {
                pieces.add(bots[i].getShape());
            }
        }
        
        //check for collisions
        Area r = new Area(p);
        Area inter; 
        for (int i = 0; i < pieces.size(); i++) {
            inter = (Area)r.clone();
            inter.intersect(new Area(pieces.get(i)));
            if(!inter.isEmpty()) {
                return false;
            }
        }
        
        if(!hasCube) {
            cubeCheck(r);
        }
        return true;
    }

    private void cubeCheck(Area a) {
        Area inter;
        for (int i = 0; i < cubes.length; i++) {
            inter = (Area)a.clone();
            inter.intersect(new Area(cubes[i].getShape()));
            if(!inter.isEmpty()) {
                hasCube = true;
                cubeNum = i;
                cubes[cubeNum].draw(paint.xpoints[0], paint.ypoints[0], this.angle);
                break;
            }
        }
    }
}