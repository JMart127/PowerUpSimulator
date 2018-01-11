package powerup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameFrame extends JPanel implements Field {
    
    public GameFrame() {
        setSize(500, 500);
    }
    
    @Override
    public void paintComponent(Graphics og) {
        super.paintComponent(og);
        Graphics2D g = (Graphics2D)og;
        g.setStroke(new BasicStroke(5));
        g.setColor(Color.green);
        g.fillRect(0, 0, (int) (FEILD.width*SCALEFACTOR), (int) (FEILD.height*SCALEFACTOR));
        g.setColor(Color.gray);
        g.drawRect(2, 2, (int) (FEILD.getWidth()*SCALEFACTOR-5), (int) (FEILD.height*SCALEFACTOR)-5);
    }
}
