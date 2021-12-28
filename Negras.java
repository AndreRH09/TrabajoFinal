/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoA;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Negras extends Peon{
    ImageIcon im = new ImageIcon(this.getClass().getResource("pawn.png")); 
    public Negras() {
        ;
    }
    
    public void mover2(int a, int num) {
        if(a ==2)
            ;
    }
    
    public ImageIcon getI(){
        return im;
    }
    
    public boolean isWhite(){
        return false;
    }
}
