package objects;

import field.Field;
import java.awt.Polygon;

public class Robot {
    
    private final int length=33;
    private final int width=28;
    private final int num;
    //xy is top left corner
    private int x;
    private int y;
    private final boolean red;
    private int angle;
    private Polygon paint;
    
    public Robot(int num) {
        this.num = num;
        red = num<3;
        angle = num<3 ? 0 : 180;
        setXY(num);
    }

    private void setXY(int num) {
        switch(num) {
            case 0:
                x = Field.LEFTEDGE;
                y = (int)(Field.LOWEDGE+50);
                angle = 0;
                break;
            case 1:
                x = Field.LEFTEDGE;
                y = Field.MIDDLEY;
                angle = 0;
                break;
            case 2:
                x = Field.LEFTEDGE;
                y = (int)(Field.HIGHEDGE-50-width);
                angle = 0;
                break;
            case 3:
                x = Field.RIGHTEDGE;
                y = (int)(Field.LOWEDGE+50+width);
                angle = 180;
                break;
            case 4:
                x = Field.RIGHTEDGE;
                y = Field.MIDDLEY;
                angle = 180;
                break;
            case 5:
                x = Field.RIGHTEDGE;
                y = (int)(Field.HIGHEDGE-50);
                angle = 180;
                break;
        }
    }
    
    public Polygon getShape() {
        Polygon shape = new Polygon();
        int x2=x;
        int y2=y;
        int a2=angle;
        shape.addPoint((int)(x2*Field.PPI), (int)(y2*Field.PPI));
        System.out.println((int)(x2*Field.PPI) + " " + (int)(y2*Field.PPI) + " " + a2);
        x2 = (int)(Math.cos(Math.toRadians(a2))*length)+x2;
        y2 = (int)(Math.sin(Math.toRadians(a2))*length)*-1+y2; //multiply by negative one as increasing the y makes it go down
        a2-=90;
        shape.addPoint((int)(x2*Field.PPI), (int)(y2*Field.PPI));
        x2 = (int)(Math.cos(Math.toRadians(a2))*width)+x2;
        y2 = (int)(Math.sin(Math.toRadians(a2))*width)*-1+y2;
        a2-=90;
        shape.addPoint((int)(x2*Field.PPI), (int)(y2*Field.PPI));
        x2 = (int)(Math.cos(Math.toRadians(a2))*length)+x2;
        y2 = (int)(Math.sin(Math.toRadians(a2))*length)*-1+y2;
        a2-=90;
        shape.addPoint((int)(x2*Field.PPI), (int)(y2*Field.PPI));
        return shape;
    }
}
