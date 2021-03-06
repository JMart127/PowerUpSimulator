package field;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;
import objects.Cube;
import objects.Robot;

public class GamePanel extends JPanel implements Field {

    int robotNum;
    Robot[] bots;
    Robot bot;
    Cube[] cubes;
    boolean[] plateCol;

    public GamePanel(int robot) {
        this.robotNum = robot;
        bot = new Robot(robot);
        bots = new Robot[6];
        cubes = new Cube[60];
        plateCol = new boolean[6];
        for (int i = 0; i < 6; i++) {
            plateCol[i] = false;
        }
        createBots();
        createCubes();
        bots[robotNum] = bot;
    }

    private void createBots() {
        for (int i = 0; i < 6; i++) {
            bots[i] = new Robot(i);
        }
    }

    private void createCubes() {
        for (int i = 0; i < 60; i++) {
            cubes[i] = new Cube(0, 0);
        }
    }

    @Override
    public void paintComponent(Graphics og) {
        super.paintComponent(og);
        Graphics2D g = (Graphics2D) og;
        drawBoard(g);
        drawBots(g);
        drawPlates(g);
        drawCubes(g);
    }

    public void drawBoard(Graphics2D g) {
        //Border
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, (int) (FEILD.width * PPI), (int) (FEILD.height * PPI));
        g.setColor(Color.black);
        g.drawRect(2, 2, (int) (FEILD.getWidth() * PPI - 5), (int) (FEILD.height * PPI) - 5);

        //Playing field
        g.setStroke(new BasicStroke((int) (2 * PPI)));
        Polygon field = new Polygon();
        for (int i = 0; i < PLAYINGCORDS[0].length; i++) {
            field.addPoint((int) (PLAYINGCORDS[0][i] * PPI), (int) (PLAYINGCORDS[1][i] * PPI));
        }
        g.drawPolygon(field);

        //Field lines
        g.drawLine((int) (LAUTOLINE * PPI), (int) (HIGHEDGE * PPI), (int) (LAUTOLINE * PPI), (int) (LOWEDGE * PPI));
        g.drawLine((int) (RAUTOLINE * PPI), (int) (HIGHEDGE * PPI), (int) (RAUTOLINE * PPI), (int) (LOWEDGE * PPI));
        g.drawLine((int) (LEFTEDGE * PPI), (int) (MIDDLEY * PPI), (int) (RIGHTEDGE * PPI), (int) (MIDDLEY * PPI));
        g.drawLine((int) (MIDDLEX * PPI), (int) (LOWEDGE * PPI), (int) (MIDDLEX * PPI), (int) (HIGHEDGE * PPI));

        //Fences
        Polygon[] fences = new Polygon[2];
        for (int i = 0; i < FENCES.length; i++) {
            fences[i] = new Polygon();
            for (int j = 0; j < FENCES[i][0].length; j++) {
                fences[i].addPoint((int) (FENCES[i][0][j] * PPI), (int) (FENCES[i][1][j] * PPI));
            }
            g.drawPolygon(fences[i]);
        }

        //Platforms
        Polygon[] platforms = new Polygon[2];
        for (int i = 0; i < PLATFORMS.length; i++) {
            platforms[i] = new Polygon();
            for (int j = 0; j < PLATFORMS[i][0].length; j++) {
                platforms[i].addPoint((int) (PLATFORMS[i][0][j] * PPI), (int) (PLATFORMS[i][1][j] * PPI));
            }
            if (i % 2 == 0) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillPolygon(platforms[i]);
            g.setColor(Color.BLACK);
            g.drawPolygon(platforms[i]);
        }

        //Null zones
        g.setStroke(new BasicStroke((int) (2 * PPI)));
        Polygon[] nzones = new Polygon[2];
        for (int i = 0; i < NULLZONES.length; i++) {
            nzones[i] = new Polygon();
            for (int j = 0; j < NULLZONES[i][0].length; j++) {
                nzones[i].addPoint((int) (NULLZONES[i][0][j] * PPI), (int) (NULLZONES[i][1][j] * PPI));
            }
            g.drawPolygon(nzones[i]);
        }

        //Cube zones
        Polygon[] cubezones = new Polygon[2];
        for (int i = 0; i < CUBEZONES.length; i++) {
            cubezones[i] = new Polygon();
            for (int j = 0; j < CUBEZONES[i][0].length; j++) {
                cubezones[i].addPoint((int) (CUBEZONES[i][0][j] * PPI), (int) (CUBEZONES[i][1][j] * PPI));
            }
            g.drawPolygon(cubezones[i]);
        }

        //Return zones
        Polygon[] returnzones = new Polygon[2];
        for (int i = 0; i < CUBEZONES.length; i++) {
            returnzones[i] = new Polygon();
            for (int j = 0; j < RETURNZONES[i][0].length; j++) {
                returnzones[i].addPoint((int) (RETURNZONES[i][0][j] * PPI), (int) (RETURNZONES[i][1][j] * PPI));
            }
            g.drawPolygon(returnzones[i]);
        }

        //Platform-Switch lines
        //first plate, xcords, second point
        g.drawLine((int) (FENCES[0][0][1] * PPI), (int) (PLATFORMS[0][1][0] * PPI),
                (int) (FENCES[1][0][1] * PPI), (int) (PLATFORMS[0][1][0] * PPI));
        g.drawLine((int) (FENCES[0][0][1] * PPI), (int) (PLATFORMS[0][1][1] * PPI),
                (int) (FENCES[1][0][1] * PPI), (int) (PLATFORMS[0][1][1] * PPI));

        //Switch and scale lines
        g.setStroke(new BasicStroke((int) (10 * PPI)));
        g.setColor(Color.black);
        g.drawLine((int) (LSWITCHMID * PPI), (int) (PLATES[0][1][2] * PPI), (int) (LSWITCHMID * PPI), (int) (PLATES[3][1][2] * PPI));
        g.drawLine((int) (RSWITCHMID * PPI), (int) (PLATES[2][1][2] * PPI), (int) (RSWITCHMID * PPI), (int) (PLATES[5][1][2] * PPI));
        g.setStroke(new BasicStroke((int) (15 * PPI)));
        g.drawLine((int) (MIDDLEX * PPI), (int) (PLATES[1][1][2] * PPI), (int) (MIDDLEX * PPI), (int) (PLATES[4][1][2] * PPI));

    }

    public void drawBots(Graphics2D g) {
        g.setStroke(new BasicStroke(2));
        for (int i = 0; i < bots.length; i++) {
            g.setColor(Color.gray);
            g.fillPolygon(bots[i].getShape());
            if (i < 3) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.blue);
            }
            g.drawPolygon(bots[i].getShape());
        }
    }

    public void drawPlates(Graphics2D g) {
        Polygon[] plates = new Polygon[6];
        for (int i = 0; i < PLATES.length; i++) {
            plates[i] = new Polygon();
            for (int j = 0; j < PLATES[i][0].length; j++) {
                plates[i].addPoint((int) (PLATES[i][0][j] * PPI), (int) (PLATES[i][1][j] * PPI));
            }
            if (plateCol[i] == true) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillPolygon(plates[i]);
            g.setColor(Color.BLACK);
            g.drawPolygon(plates[i]);
        }
    }

    public void drawCubes(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setStroke(new BasicStroke(toInt(2 * PPI)));
        for (int i = 0; i < 60; i++) {
            g.setColor(Color.black);
            g.drawPolygon(cubes[i].getShape());
            g.setColor(Color.yellow);
            g.fillPolygon(cubes[i].getShape());
        }
    }

    public void turnLeft() {
        bot.rotate(10);
    }

    public void turnRight() {
        bot.rotate(-10);
    }

    public void goFoward() {
        bot.foward();
    }

    public void goBack() {
        bot.back();
    }

    public void placeCube() {
        if (bot.hasCube()) {
            bot.checkPlates();
        }
    }

    public void refresh(Robot[] bots) {
        this.bots = bots;
    }

    public Robot getRobot() {
        return bot;
    }

    public void setBots(Robot[] bots) {
        this.bots = bots;
        bot.setBotsArray(bots);
    }

    public void setCubes(Cube[] cubes) {
        this.cubes = cubes;
        bot.setCubeArray(cubes);
    }

    public void setPlates(boolean[] plates) {
        this.plateCol = plates;
    }

    private int toInt(double d) {
        return (int) (Math.round(d));
    }
}
