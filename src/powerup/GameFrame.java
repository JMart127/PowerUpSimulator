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
        g.setStroke(new BasicStroke(5));
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
    }
}