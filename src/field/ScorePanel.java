package field;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import powerup.PowerUpServer.scale;

public class ScorePanel extends JPanel {

    int redScore;
    int blueScore;
    int seconds = 150;
    scale[] scales;
    String text;
    JTextArea score;
    JTextArea[] scaleTexts;

    public ScorePanel(scale[] scales) {
        this.seconds = seconds;
        this.scales = scales;
        score = new JTextArea();
        scaleTexts = new JTextArea[3];
        scaleTexts[0] = new JTextArea("Switch 1");
        scaleTexts[1] = new JTextArea("  Scale ");
        scaleTexts[2] = new JTextArea("Switch 2");
        setUpPanel();
    }

    public void setUpPanel() {
        setLayout(null);
        for (int i = 0; i < 3; i++) {
            scaleTexts[i].setFont(new Font("Times New Roman", Font.BOLD, 50));
            scaleTexts[i].setForeground(Color.white);
            add(scaleTexts[i]);
            scaleTexts[i].setBounds(i * 225 + 325, 5, 185, 60);
        }
        add(score);
        score.setFont(new Font("Times New Roman", Font.BOLD, 50));
        score.setBackground(new Color(238, 238, 238));
        score.setBounds(305, 70, 675, 120);
        score.setText(getString());
    }

    public void refresh(int red, int blue, int seconds) {
        this.blueScore = blue;
        this.redScore = red;
        this.seconds = seconds;
        for (int i = 0; i < 3; i++) {
            if (scales[i] == scale.RED) {
                scaleTexts[i].setBackground(Color.red);
            } else if (scales[i] == scale.BLUE) {
                scaleTexts[i].setBackground(Color.blue);
            } else {
                scaleTexts[i].setBackground(Color.gray);
            }
        }
        score.setText(getString());
    }

    public String getString() {
        return "Red Score: " + three(redScore) + " Blue Score: " + three(blueScore) + "\n                  Time: " + seconds;
    }

    public String three(int i) {
        if (i >= 100) {
            return i + "";
        } else if (i >= 10) {
            return "0" + i;
        } else {
            return "00" + i;
        }
    }
}
