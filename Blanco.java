/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoA;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Blanco extends Peon{
    ImageIcon img = new ImageIcon(this.getClass().getResource("peon.png")); 
    public Blanco() {
        ;
    }
    
    public void mover2(int a, int num) {
        if(a ==2)
            ;
    }
    
    public ImageIcon getI(){
        return img;
    }
    public boolean isWhite(){
        return true;
    }
    
    
    
}
