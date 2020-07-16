/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.image.RescaleOp;

/**
 *
 * @author shadowlord101
 */
public class HexAnimation {
    
    private float Alpha = 0.00f;
    
    private int HexX;
    private int HexY;
    
    public HexAnimation(int x,int y){
        HexX = x;
        HexY = y;
    }
    
    public void SetAlpha(float a){Alpha = a;}
    public float GetAlpha(){return Alpha;}
    public int GetX(){return HexX;}
    public int GetY(){return HexY;}
}
