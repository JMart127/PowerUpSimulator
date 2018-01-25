package field;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
    
    public Frame() {
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        getContentPane().add(new GameFrame(), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}