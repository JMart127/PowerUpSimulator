package field;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    GamePanel game;
    
    public GameFrame(int number) {
        game = new GamePanel(number);
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        getContentPane().add(game, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        requestFocus();
    }
    
    
    
    public GamePanel getPanel() {
        return game;
    }
    
}