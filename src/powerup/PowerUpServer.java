package powerup;

import field.Field;
import objects.Cube;
import objects.Robot;

public class PowerUpServer implements Field{
    
    public static Robot[] robots;
    public static Cube[] cubes;
    
    public static void main(String[] args) {
        PowerUpClient[] clients = new PowerUpClient[6];
        cubes = new Cube[60];
        for (int i = 0; i < 60; i++) {
            cubes[i] = new Cube(CUBELOC[i].x,CUBELOC[i].y);
        }
        robots = new Robot[6];
        for (int i = 0; i < 6; i++) {
            clients[i] = new PowerUpClient(i);
        }
        for (int i = 0; i < 6; i++) {
            robots[i] = clients[i].frame.getPanel().getRobot();
        }
        for (int i = 0; i < 6; i++) {
            clients[i].frame.getPanel().setBots(robots);
            clients[i].frame.getPanel().setCubes(cubes);
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
}
