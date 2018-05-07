package objects;

public class Cube {
    
    int x;
    int y;
    boolean isPlaced=false;
    int plate = -1;
    int size = 13;
    
    public Cube(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    public void move(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    
}
