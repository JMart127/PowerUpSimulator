package objects;

import field.Field;
import java.awt.Polygon;

public class Cube implements Field {
    
    int x;
    int y;
    boolean isPlaced=false;
    int plate = -1;
    int size = toInt(13*PPI);
    Polygon p;
    
    public Cube(int x, int y) {
        this.x=toInt(x*PPI);
        this.y=toInt(y*PPI);
        p = new Polygon();
        refreshShape();
    }
    
    public void setLoc(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    private void refreshShape() {
        p= new Polygon();
        p.addPoint(x, y);
        p.addPoint(x+size, y);
        p.addPoint(x+size, y+size);
        p.addPoint(x, y+size);
    }
    
    public Polygon getShape() {
        refreshShape();
        return p;
    }

    private int toInt(double d) {
        return (int)(Math.round(d));
    }
}
