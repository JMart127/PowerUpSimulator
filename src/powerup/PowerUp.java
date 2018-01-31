package powerup;

import java.util.ArrayList;
import objects.Robot;
import field.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PowerUp {
    public static ArrayList<Robot> robots;
    
    public static void main(String[] args) {
        Frame frame = new Frame();
        robots = new ArrayList();
        createBots(robots);
        try {
            Thread.sleep(1000);
            //frame.refreshRobots(robots);
        } catch (InterruptedException ex) {
            Logger.getLogger(PowerUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createBots(ArrayList<Robot> robots) {
        for (int i = 0; i < 6; i++) {
            robots.add(new Robot(i));
        }
    }
    
}
