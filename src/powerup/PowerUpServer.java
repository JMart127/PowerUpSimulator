package powerup;

import field.Field;
import objects.Cube;
import objects.Robot;

public class PowerUpServer implements Field {

    public static Robot[] robots;
    public static Cube[] cubes;
    public static boolean[] plates;
    public static int redScore;
    public static int blueScore;
    public static int[] plateCounts;
    public static enum scale {
        RED, BLUE, NONE
    }
    public static scale[] scales;
    public static boolean game;

    public static void main(String[] args) {
        PowerUpClient[] clients = new PowerUpClient[6];
        redScore = 0;
        blueScore = 0;
        //create arrays
        plates = createPlatform();
        cubes = new Cube[60];
        robots = new Robot[6];
        plateCounts = new int[6];
        scales = new scale[3];
        for (int i = 0; i < 3; i++) {
            scales[i] = scale.NONE;
        }
        //fill up cubes
        for (int i = 0; i < 60; i++) {
            cubes[i] = new Cube(CUBELOC[i].x, CUBELOC[i].y);
        }
        //create frames
        for (int i = 0; i < 6; i++) {
            clients[i] = new PowerUpClient(i);
        }
        //fill robots
        for (int i = 0; i < 6; i++) {
            clients[i].frame.addScorePanel(scales);
            robots[i] = clients[i].frame.getPanel().getRobot();
        }
        for (int i = 0; i < 6; i++) {
            clients[i].frame.getPanel().setBots(robots);
            clients[i].frame.getPanel().setCubes(cubes);
            clients[i].frame.getPanel().setPlates(plates);
        }
        game = true;
        Thread scoreThread  = new Thread() {
            @Override
            public void run() {
                for (int j = 150; j > -1; j--) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    plateCounts = new int[6];
                    for (int i = 0; i < cubes.length; i++) {
                        if(cubes[i].getPlate()>=0) {
                            plateCounts[cubes[i].getPlate()]++;
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        if(plates[i]==true) { //if plate is red
                            if(plateCounts[i]>plateCounts[i+3]) { //if red>blue
                                if(i!=2) { //keep red from scoring on far right
                                    redScore++;
                                    scales[i] = scale.RED; 
                                } 
                            } else if (plateCounts[i]<plateCounts[i+3]) {
                                if(i!=0) { //keep blue from scoring on the first
                                    blueScore++;
                                    scales[i] = scale.BLUE; 
                                }
                            } else {
                                scales[i] = scale.NONE;
                            }
                        } else {
                            if(plateCounts[i]>plateCounts[i+3]) { //if red>blue
                                if(i!=0) { //keep blue from scoring on the first
                                    blueScore++;
                                    scales[i] = scale.BLUE; 
                                }
                            } else if (plateCounts[i]<plateCounts[i+3]) {
                                if(i!=2) { //keep red from scoring on far right
                                    redScore++;
                                    scales[i] = scale.RED; 
                                }
                            } else {
                                scales[i] = scale.NONE;
                            }
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        clients[i].frame.getScore().refresh(redScore, blueScore, j);
                    }
                }
                game = false;
            }   
        };
        scoreThread.start();
        
        while (game) {
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
            if (Math.random() > 0.5) {
                ret[i] = true;
                ret[i + 3] = false;
            } else {
                ret[i] = false;
                ret[i + 3] = true;
            }
        }
        return ret;
    }
}
