/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerup;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Toolkit;


public interface Field {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    double SCALEFACTOR = screen.height/360.0 > screen.width/888.0 ? screen.width/888.0 : screen.height/360.0;
    Dimension FEILD = new Dimension(888,360); //start 0,0
    int[][] PLAYINGCORDS = {{},{}};
    Polygon PLAYING = new Polygon(PLAYINGCORDS[0], PLAYINGCORDS[1], PLAYINGCORDS[0].length);
}
