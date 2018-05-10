package field;

import static field.Field.PPI;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import powerup.PowerUpServer.scale;

public class GameFrame extends JFrame {

    GamePanel game;
    ScorePanel score;

    public GameFrame(int number) {
        game = new GamePanel(number);
        setUpFrame(number);
    }

    public void setUpFrame(int number) {
        int title = number + 1;
        setTitle("Robot " + title);
        setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        setLayout(null);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        add(game);
        game.setBounds(0, 0, screen.width, (int) (360 * PPI));
        //getContentPane().add(game, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        requestFocus();
    }

    public GamePanel getPanel() {
        return game;
    }

    public ScorePanel getScore() {
        return score;
    }

    public void addScorePanel(scale[] scales) {
        score = new ScorePanel(scales);
        add(score, 0);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        score.setBounds(0, (int) (361 * PPI), screen.width, (int) (screen.height - (361 * PPI)));
    }

}
