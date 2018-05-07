package powerup;

import objects.Robot;

public class PowerUpServer {
    
    public static Robot[] robots;
    
    public static void main(String[] args) {
        System.out.println("It ran");
        PowerUpClient[] clients = new PowerUpClient[6];
        robots = new Robot[6];
        for (int i = 0; i < 6; i++) {
            clients[i] = new PowerUpClient(i);
        }
        for (int i = 0; i < 6; i++) {
            robots[i] = clients[i].frame.getPanel().getRobot();
        }
        for (int i = 0; i < 6; i++) {
            clients[i].frame.getPanel().setBots(robots);
        }
        //clients[0] = new PowerUpClient(0);
        //robots[0] = clients[0].frame.getPanel().getRobot();
        while(true) {
            try {
                Thread.sleep(50);
                for (int i = 0; i < 6; i++) {
                    clients[i].frame.getPanel().getRobot().doPhysics();
                }
//                for (int i = 0; i < 6; i++) {
//                    clients[0].frame.getPanel().refresh(robots);
//                }
                for (int i = 0; i < 6; i++) {
                    clients[i].frame.getPanel().repaint();
                }
                //clients[0].frame.getPanel().getRobot().doPhysics();
                //clients[0].frame.getPanel().repaint();
//                for (int i = 0; i < 6; i++) {
//                    clients[0].frame.getPanel().getRobot().refeshShape();
//                }
//                for (int i = 0; i < 6; i++) {
//                    clients[0].frame.getPanel().refresh(robots);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
