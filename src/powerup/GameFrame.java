package powerup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameFrame extends JPanel {
    
    public GameFrame() {
        setSize(500, 500);
    }
    
    @Override
    public void paintComponent(Graphics og) {
        super.paintComponent(og);
        Graphics2D g = (Graphics2D)og;
        g.setBackground(Color.GREEN);
    }
}
