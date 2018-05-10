package field;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import powerup.PowerUpServer.scale;

public class ScorePanel extends JPanel{
    
    
    int redScore;
    int blueScore;
    int seconds = 150;
    scale[] scales;
    String text;
    JTextArea wins;
    JTextArea[] scaleTexts;
    
    public ScorePanel(int redScore, int blueScore, int seconds, scale[] scales) {
        this.redScore=redScore;
        this.blueScore=blueScore;
        this.seconds=seconds;
        this.scales=scales;
        wins = new  JTextArea();
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
            scaleTexts[i].setBounds(i*300+245, 5, 185, 60);
        }
    }
    
    public void refresh() {
        for (int i = 0; i < 3; i++) {
            if(scales[i]==scale.RED) {
                scaleTexts[i].setBackground(Color.red);
            } else if (scales[i]==scale.BLUE) {
                scaleTexts[i].setBackground(Color.blue);
            } else {
                scaleTexts[i].setBackground(Color.gray);
            }
        }
    }
}
