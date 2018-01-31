package field;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import objects.Robot;

public class Frame extends JFrame {
    
    GameFrame game = new GameFrame();
    
    public Frame() {
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        getContentPane().add(game, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public JPanel getPanel() {
        return game;
    }
    
}