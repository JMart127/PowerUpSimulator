package powerup;

import field.GameFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static powerup.PowerUpServer.game;

public class PowerUpClient {

    GameFrame frame;

    public PowerUpClient(int num) {
        frame = new GameFrame(num);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (game) {
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
                        case KeyEvent.VK_SPACE:
                            frame.getPanel().placeCube();
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
    }

}
