package powerup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;

public class GameFrame extends JPanel implements Field {
    
    public GameFrame() {
        setSize(500, 500);
    }
    
    @Override
    public void paintComponent(Graphics og) {
        super.paintComponent(og);
        Graphics2D g = (Graphics2D)og;
        drawBoard(g);
    }
    
    public void drawBoard(Graphics2D g) {
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.green);
        g.fillRect(0, 0, (int) (FEILD.width*PPI), (int) (FEILD.height*PPI));
        g.setColor(Color.gray);
        g.drawRect(2, 2, (int) (FEILD.getWidth()*PPI-5), (int) (FEILD.height*PPI)-5);
        //playing field
        g.setStroke(new BasicStroke((int)(2*PPI)));
        g.setColor(Color.black);
        Polygon field = new Polygon();
        for(int i = 0; i < PLAYINGCORDS[0].length; i++) {
            field.addPoint((int) (PLAYINGCORDS[0][i]*PPI), (int)(PLAYINGCORDS[1][i]*PPI));
        }
        g.drawPolygon(field);
        //Field lines
        g.drawLine((int)(LAUTOLINE*PPI), (int)(HIGHEDGE*PPI), (int)(LAUTOLINE*PPI), (int)(LOWEDGE*PPI));
        g.drawLine((int)(RAUTOLINE*PPI), (int)(HIGHEDGE*PPI), (int)(RAUTOLINE*PPI), (int)(LOWEDGE*PPI));
        
        //Fences
        Polygon lFence = new Polygon();
        for(int i = 0; i < LFENCE[0].length; i++) {
            lFence.addPoint((int) (LFENCE[0][i]*PPI), (int)(LFENCE[1][i]*PPI));
        }
        g.drawPolygon(lFence);
        Polygon rFence = new Polygon();
        for(int i = 0; i < RFENCE[0].length; i++) {
            rFence.addPoint((int) (RFENCE[0][i]*PPI), (int)(RFENCE[1][i]*PPI));
        }
        g.drawPolygon(rFence);
        //Plates
        g.setColor(Color.red);
        Polygon[] plates = new Polygon[6];
        for (int i = 0; i < PLATES.length; i++) {
            plates[i] = new Polygon();
            for (int j = 0; j < PLATES[i][0].length; j++) {
                plates[i].addPoint((int) (PLATES[i][0][j]*PPI), (int)(PLATES[i][1][j]*PPI));
            }
            g.drawPolygon(plates[i]);
        }
        //Switch and scale lines
        g.setStroke(new BasicStroke((int)(10*PPI)));
        g.setColor(Color.black);
        g.drawLine((int)(LSWITCHMID*PPI), (int)(PLATES[0][1][2]*PPI), (int)(LSWITCHMID*PPI), (int)(PLATES[3][1][2]*PPI)); 
        g.drawLine((int)(RSWITCHMID*PPI), (int)(PLATES[2][1][2]*PPI), (int)(RSWITCHMID*PPI), (int)(PLATES[5][1][2]*PPI));
        g.setStroke(new BasicStroke((int)(15*PPI)));
        g.drawLine((int)(MIDDLEX*PPI), (int)(PLATES[1][1][2]*PPI), (int)(MIDDLEX*PPI), (int)(PLATES[4][1][2]*PPI));

        //Null zones
        g.setStroke(new BasicStroke(2));
        //g.setColor(Color.red);
        Polygon[] nzones = new Polygon[2];
        for (int i = 0; i < NULLZONES.length; i++) {
            nzones[i] = new Polygon();
            for (int j = 0; j < NULLZONES[i][0].length; j++) {
                nzones[i].addPoint((int) (NULLZONES[i][0][j]*PPI), (int)(NULLZONES[i][1][j]*PPI));
            }
            g.drawPolygon(nzones[i]);
        }
    }
}