/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoA;

import javax.swing.*;

public class Casilla extends JButton{
    private int fila;
    private int columna;
    private Peon p;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        p = null;
    }

    public Casilla() {
        
    }

    public int getF() {
        return fila;
    }

    public int getC() {
        return columna;
    }

    public void setF(int fila) {
        this.fila = fila;
    }

    public void setC(int columna) {
        this.columna = columna;
    }
    
    

    public boolean isP() {
        if(p != null)
            return true;
        else
            return false;
    }
    public void SetP(Peon p) {
        this.p = p;
    }
    public Peon getP() {
        return p;
    }
    public void emptyP() {
        p = null;
    }
    
    public boolean isW(){
        return p.isWhite();
    }
    
}
