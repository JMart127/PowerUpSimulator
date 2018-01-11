/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class PowerUp {

    public static void main(String[] args) {
        createFrame();
    }
    
    public static void createFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("PowerUP");
        frame.getContentPane().add(new GameFrame(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size);
        frame.setResizable(false);
        frame.setBackground(Color.GREEN);
        frame.setVisible(true);
    }
    
}
