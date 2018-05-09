package powerup;

import field.Field;
import objects.Cube;
import objects.Robot;

public class PowerUpServer implements Field{
    
    public static Robot[] robots;
    public static Cube[] cubes;
    public static boolean[] plates;
    
    public static void main(String[] args) {
        PowerUpClient[] clients = new PowerUpClient[6];
        //create arrays
        plates = createPlatform();
        cubes = new Cube[60];
        robots = new Robot[6];
        //fill up cubes
        for (int i = 0; i < 60; i++) {
            cubes[i] = new Cube(CUBELOC[i].x,CUBELOC[i].y);
        }
        //create frames
        for (int i = 0; i < 6; i++) {
            clients[i] = new PowerUpClient(i);
        }
        //fill robots
        for (int i = 0; i < 6; i++) {
            robots[i] = clients[i].frame.getPanel().getRobot();
        }
        for (int i = 0; i < 6; i++) {
            clients[i].frame.getPanel().setBots(robots);
            clients[i].frame.getPanel().setCubes(cubes);
            clients[i].frame.getPanel().setPlates(plates);
            
        }
        while(true) {
            try {
                Thread.sleep(50);
                for (int i = 0; i < 6; i++) {
                    clients[i].frame.getPanel().getRobot().doPhysics();
                }
                for (int i = 0; i < 6; i++) {
                    clients[i].frame.getPanel().repaint();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }
    
    public static boolean[] createPlatform() {
        boolean[] ret = new boolean[6];
        for (int i = 0; i < 3; i++) {
            if(Math.random()>0.5) {
                ret[i] = true;
                ret[i+3] = false;
            } else {
                ret[i] = false;
                ret[i+3] = true;
            }
        }
        return ret;
    }
}
