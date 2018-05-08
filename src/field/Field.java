/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package field;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;


public interface Field {
    Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    double PPI = SCREEN.height/360.0 > SCREEN.width/888.0 ? SCREEN.width/888.0 : SCREEN.height/360.0; //Pixels for everyinch of field
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
    double[][][] FENCES = {
        {{LEFTEDGE+140, LEFTEDGE+196, LEFTEDGE+196, LEFTEDGE+140},
            {LOWEDGE+86.25,LOWEDGE+86.25, HIGHEDGE-86.25, HIGHEDGE-86.25}},
        {{RIGHTEDGE-140, RIGHTEDGE-196, RIGHTEDGE-196, RIGHTEDGE-140},
            {LOWEDGE+86.25,LOWEDGE+86.25, HIGHEDGE-86.25, HIGHEDGE-86.25}}};
    //six plates going from left to right top then bottom
    //[Plate][0 for x, 1 for y][specific point]
    double[][][] PLATES = {
        //56 inch gap between two fences; plates are only 4x3
        {{FENCES[0][0][0]+4,FENCES[0][0][1]-4,FENCES[0][0][2]-4,FENCES[0][0][3]+4},
            {FENCES[0][1][0]+4,FENCES[0][1][1]+4,FENCES[0][1][0]+40,FENCES[0][1][1]+40}},
        {{LEFTEDGE+299.65,RIGHTEDGE-299.65,RIGHTEDGE-299.65,LEFTEDGE+299.65},
            {LOWEDGE+71.57,LOWEDGE+71.57,LOWEDGE+107.57,LOWEDGE+107.51}},
        {{FENCES[1][0][0]-4,FENCES[1][0][1]+4,FENCES[1][0][2]+4,FENCES[1][0][3]-4},
            {FENCES[1][1][0]+4,FENCES[1][1][1]+4,FENCES[1][1][0]+40,FENCES[1][1][1]+40}},
        {{FENCES[0][0][0]+4,FENCES[0][0][1]-4,FENCES[0][0][2]-4,FENCES[0][0][3]+4},
            {FENCES[0][1][2]-4,FENCES[0][1][3]-4,FENCES[0][1][2]-40,FENCES[0][1][3]-40}},
        {{LEFTEDGE+299.65,RIGHTEDGE-299.65,RIGHTEDGE-299.65,LEFTEDGE+299.65},
            {HIGHEDGE-71.57,HIGHEDGE-71.57,HIGHEDGE-107.57,HIGHEDGE-107.51}},
        {{FENCES[1][0][0]-4,FENCES[1][0][1]+4,FENCES[1][0][2]+4,FENCES[1][0][3]-4},
            {FENCES[1][1][2]-4,FENCES[1][1][3]-4,FENCES[1][1][2]-40,FENCES[1][1][3]-40}}   
    };
    int LSWITCHMID = LEFTEDGE+168;
    int RSWITCHMID = RIGHTEDGE-168;
    double NULLZONES[][][] = {
        {{LEFTEDGE+288,RIGHTEDGE-288,RIGHTEDGE-288,LEFTEDGE+288},
            {LOWEDGE, LOWEDGE, LOWEDGE+95.25, LOWEDGE+95.25}}, 
        {{LEFTEDGE+288,RIGHTEDGE-288,RIGHTEDGE-288,LEFTEDGE+288},
            {HIGHEDGE, HIGHEDGE, HIGHEDGE-95.25, HIGHEDGE-95.25}}
    };
    double PLATFORMS[][][] = {
        {{MIDDLEX,MIDDLEX,LEFTEDGE+261.47,LEFTEDGE+261.47},
            {LOWEDGE+96.25, HIGHEDGE-96.25, HIGHEDGE-96.25, LOWEDGE+96.25}}, 
        {{MIDDLEX,MIDDLEX,RIGHTEDGE-261.47,RIGHTEDGE-261.47},
            {LOWEDGE+96.25, HIGHEDGE-96.25, HIGHEDGE-96.25, LOWEDGE+96.25}}
    };
    double CUBEZONES[][][] = {
        {{FENCES[0][0][0]-42,FENCES[0][0][0],FENCES[0][0][0],FENCES[0][0][0]-42},
            {MIDDLEY-22.5, MIDDLEY-22.5, MIDDLEY+22.5, MIDDLEY+22.5}}, 
        {{FENCES[1][0][0]+42,FENCES[1][0][0],FENCES[1][0][0],FENCES[1][0][0]+42},
            {MIDDLEY-22.5, MIDDLEY-22.5, MIDDLEY+22.5, MIDDLEY+22.5}}
    };
    double RETURNZONES[][][] = {
        {{LEFTEDGE,LEFTEDGE+36,LEFTEDGE+36,LEFTEDGE},
            {MIDDLEY-12, MIDDLEY-12, MIDDLEY-60, MIDDLEY-60}}, 
        {{RIGHTEDGE, RIGHTEDGE-36, RIGHTEDGE-36, RIGHTEDGE},
            {MIDDLEY+12, MIDDLEY+12, MIDDLEY+60, MIDDLEY+60}}
    };
    Point[] CUBELOC = {
        new Point(131,27),
        new Point(131,27),
        new Point(131,27),
        new Point(131,27),
        new Point(131,27),
        new Point(131,27),
        new Point(131,27),//7
        new Point(744,27),
        new Point(744,27),
        new Point(744,27),
        new Point(744,27),
        new Point(744,27),
        new Point(744,27),
        new Point(744,27),//14
        new Point(744,320),
        new Point(744,320),
        new Point(744,320),
        new Point(744,320),
        new Point(744,320),
        new Point(744,320),
        new Point(744,320),//21
        new Point(131,320),
        new Point(131,320),
        new Point(131,320),
        new Point(131,320),
        new Point(131,320),
        new Point(131,320),
        new Point(131,320),//28
        new Point(245,158),
        new Point(245,173),
        new Point(245,187),
        new Point(629,158),
        new Point(629,173),
        new Point(629,187),
        new Point(232,166),
        new Point(232,180),
        new Point(219,173),
        new Point(643,166),
        new Point(643,180),
        new Point(656,173), //bottom layer in cube zone 40
        new Point(245,166),
        new Point(245,180),
        new Point(232,173),
        new Point(629,166),
        new Point(629,180),
        new Point(643,173), //second layer 46
        new Point(245,173),
        new Point(629,173), //top two 48
        new Point(317,104),
        new Point(317,132),
        new Point(317,160),
        new Point(317,187),        
        new Point(317,215),
        new Point(317,243), //left 6 54
        new Point(557,104),
        new Point(557,132),
        new Point(557,160),
        new Point(557,187),        
        new Point(557,215),
        new Point(557,243), //right 6 60
    };
}