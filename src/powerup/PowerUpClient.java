package powerup;

import field.GameFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PowerUpClient {
    
    GameFrame frame;
    
    public PowerUpClient(int num) {
        frame = new GameFrame(num);
        
        frame.addKeyListener(new KeyListener() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        frame.getPanel().goFoward();
                        break;
                    case KeyEvent.VK_DOWN:
                        frame.getPanel().goBack();
                        break;
                    case KeyEvent.VK_LEFT:
                        frame.getPanel().turnLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        frame.getPanel().turnRight();
                        break;
                    default:
                       break;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {}
            
            @Override
            public void keyTyped(KeyEvent e) {}
        });
    }
    
}