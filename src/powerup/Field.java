/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerup;

import java.awt.Dimension;
import java.awt.Toolkit;


public interface Field {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    double PPI = screen.height/360.0 > screen.width/888.0 ? screen.width/888.0 : screen.height/360.0; //Pixels for everyinch of feild
    Dimension FEILD = new Dimension(888,360); //start 0,0
    double[][] PLAYINGCORDS = {{120,155,733,768,768,733,155,120},{48,18.31,18.31,48,312,341.69,341.69,312}};
    double LOWEDGE=18.31;
    double HIGHEDGE=341.69;
    int LEFTEDGE=120;
    int RIGHTEDGE=768;
    int LAUTOLINE=LEFTEDGE+120;
    int RAUTOLINE=RIGHTEDGE-120;
    int MIDDLEX=444;
    int MIDDLEY=180;
    double[][] LFENCE = {{LEFTEDGE+140, LEFTEDGE+196, LEFTEDGE+196, LEFTEDGE+140},
        {LOWEDGE+86.25,LOWEDGE+86.25, HIGHEDGE-86.25, HIGHEDGE-86.25}};
    double[][] RFENCE = {{RIGHTEDGE-140, RIGHTEDGE-196, RIGHTEDGE-196, RIGHTEDGE-140},
        {LOWEDGE+86.25,LOWEDGE+86.25, HIGHEDGE-86.25, HIGHEDGE-86.25}};
    //six plates going from left to right top then bottom
    double[][][] PLATES = {
        //56 inch gap between two fences; plates are only 4x3
        {{LFENCE[0][0]+4,LFENCE[0][1]-4,LFENCE[0][2]-4,LFENCE[0][3]+4},
            {LFENCE[1][0]+4,LFENCE[1][1]+4,LFENCE[1][0]+40,LFENCE[1][1]+40}},
//        {{x1,x2,x3,x4},
//            {y1,y2,y3,y4}},
        {{RFENCE[0][0]-4,RFENCE[0][1]+4,RFENCE[0][2]+4,RFENCE[0][3]-4},
            {RFENCE[1][0]+4,RFENCE[1][1]+4,RFENCE[1][0]+40,RFENCE[1][1]+40}},
        {{LFENCE[0][0]+4,LFENCE[0][1]-4,LFENCE[0][2]-4,LFENCE[0][3]+4},
            {LFENCE[1][2]-4,LFENCE[1][3]-4,LFENCE[1][2]-40,LFENCE[1][3]-40}},
//        {{x1,x2,x3,x4},
//            {y1,y2,y3,y4}},
        {{RFENCE[0][0]-4,RFENCE[0][1]+4,RFENCE[0][2]+4,RFENCE[0][3]-4},
            {RFENCE[1][2]-4,RFENCE[1][3]-4,RFENCE[1][2]-40,RFENCE[1][3]-40}}   
    };
    int LSWITCHMID = LEFTEDGE+168;
    int RSWITCHMID = RIGHTEDGE-168;
}
