/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectoA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Ajedrez extends JFrame {
    private static final int ancho = 800;
    private static final int largo = 800;
    private static Casilla selec = new Casilla();
    private Casilla [][] tab = new Casilla[9][9];
    private boolean turnoW = true;
    private JLabel turno = new JLabel();
    private JLabel error = new JLabel();
    private JLabel aviso = new JLabel();
    
    
    public Ajedrez() {
        setSize(ancho,largo);
        setTitle("Peones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        contenido();
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void contenido(){
        JPanel a =new JPanel();
        a.setLayout(new GridLayout(0,5));
        a.add(new JLabel(""));
        a.add(new JLabel("Turno: "));
        turno.setFont(new Font("Verdana", Font.PLAIN, 16));
        turno.setBackground(Color.red);
        a.add(turno);
        for (int i = 0; i < 8; i++) {
            a.add(new JLabel(""));
        }
        a.add(new JLabel("\nError: "));
        error.setFont(new Font("Verdana", Font.PLAIN, 14));
        a.add(error);
        for (int i = 0; i < 8; i++) {
            a.add(new JLabel(""));
        }
        a.add(new JLabel("\nAviso: "));
        aviso.setFont(new Font("Verdana", Font.PLAIN, 16));
        a.add(aviso);
        a.add(new JLabel(""));
        turnos();
        add(a,BorderLayout.NORTH);
        

        JPanel ab =new JPanel();
        ab.setLayout(new GridLayout(8,8));
        for (int i = 8; i > 0; i--) {
            for (int j = 1; j < 9; j++) {
                tab[i][j]= new Casilla(i, j);
                if(i%2==0 && j%2==0 ||(i%2==1 && j%2==1)){
                    tab[i][j].setBackground(Color.WHITE);
                }
                else{
                    tab[i][j].setBackground(Color.BLACK);
                }
                tab[i][j].addActionListener(new Listen());
                tab[i][j].setSize(100, 100);
                ab.add(tab[i][j]);
            }
        }
        add(ab);
        
        ImageIcon b = new ImageIcon("peon.png");
        for (int i = 1; i < 9; i++) {
            tab[2][i].SetP(new Blanco());
            tab[7][i].SetP(new Negras());
            pieza(tab[7][i],tab[7][i].getP());
            pieza(tab[2][i],tab[2][i].getP());
        }
    }
    
    public void pieza(Casilla b,Peon p){
        b.setIcon(new ImageIcon(p.getI().getImage().getScaledInstance(b.getWidth(), b.getHeight(),Image.SCALE_SMOOTH)));
    }
    public void turnos(){
        if(turnoW)
            turno.setText("Es turno del Blanco");
        else
            turno.setText("Es turno del Negro");
        
    }
    
    private class Listen implements ActionListener{
        public void actionPerformed(ActionEvent e){
            error.setText("");
            
            Casilla a = (Casilla) e.getSource();
            if(!a.isP() && !selec.isP())            //sI NO hay un peon en la seleccion ni tampoco uno seleccionado
                error.setText("No hay un peon alli");
            else if (a.isP() && !selec.isP()){        //sI hay un peon en la seleccion y no esta uno seleccionado
                selec.SetP(a.getP());
                selec.setF(a.getF());
                selec.setC(a.getC());
            }
            else if(!a.isP() && selec.isP()){        //Si no hay un peon en la casilla y hay uno seleccionado
                if(selec.isW() && turnoW){
                    avanzarB(a, selec);
                }
                else if(selec.isW() && !turnoW){
                    error.setText("No ES SU TURNO");
                }
                else if(!selec.isW() && !turnoW){
                    avanzarN(a, selec);
                }
                else{
                    error.setText("No ES SU TURNO");
                }
                selec.SetP(null);
            }
            else if(a.isP() && selec.isP()){        //Si hay un peon en la casilla y hay uno seleccionado
                if(selec.isW() && turnoW){
                    capturarB(a, selec);
                }
                else if(selec.isW() && !turnoW){
                    error.setText("No ES SU TURNO");
                }
                else if(!selec.isW() && !turnoW){
                    capturarN(a, selec);
                }
                else{
                    error.setText("No ES SU TURNO");
                }
                selec.SetP(null);
            }
            turnos();
        }
        
        
        public void avanzarB(Casilla a, Casilla selec){
            if(a.getC() ==selec.getC() && a.getF()== selec.getF()+1 ){
                cambio(a, selec);
                pieza(a, a.getP());
                
                if(a.getF()==8){
                    aviso.setText("Ganaron las blancas");
                    aviso.setBackground(Color.LIGHT_GRAY);
                    fin();
                }
                
                turnoW=false;       //cambio a turno de las negras
            }
            else if(a.getC() ==selec.getC() && a.getF()== selec.getF()+2 && selec.getF()==2 ){
                cambio(a, selec);
                pieza(a, a.getP());
                
                turnoW=false;       //cambio a turno de las negras
            }
            else{
                error.setText("No valido");
                selec.SetP(null);
            }
        }
        public void capturarB(Casilla a, Casilla selec){
            if((a.getC()==selec.getC()+1 || a.getC()==selec.getC()-1) && a.getF()== selec.getF()+1 && !a.isW()){
                cambio(a, selec);
                pieza(a, a.getP());
                
                turnoW=false;       //cambio a turno a las negras
            }
            else{
                error.setText("No valido");
                selec.SetP(null);
            }
        }
        public void capturarN(Casilla a, Casilla selec){
            if((a.getC()==selec.getC()+1 || a.getC()==selec.getC()-1) && a.getF()== selec.getF()-1 && a.isW()){
                cambio(a, selec);
                pieza(a, a.getP());
                
                turnoW=true;       //cambio a turno a las blancas
            }
            else{
                error.setText("No valido");
                selec.SetP(null);
            }
        }
        
        
        public void avanzarN(Casilla a, Casilla selec){
            if(a.getC() ==selec.getC() && a.getF()== selec.getF()-1 ){
                cambio(a, selec);
                pieza(a, a.getP());
                
                if(a.getF()==1){
                    aviso.setText("Ganaron las negras");
                    aviso.setBackground(Color.DARK_GRAY);
                    fin();
                }
                
                turnoW=true;       //cambio a turno de las blancas
            }
            else if(a.getC() ==selec.getC() && a.getF()== selec.getF()-2 && selec.getF()==7 ){
                cambio(a, selec);
                pieza(a, a.getP());
                
                turnoW=true;       //cambio a turno de las blancas
            }
            else{
                error.setText("No valido");
                selec.SetP(null);
            }
        }
        
        
        public void cambio(Casilla c, Casilla d){
            try{
                tab[c.getF()][c.getC()].SetP(selec.getP());
                tab[d.getF()][d.getC()].setIcon(null);
                tab[d.getF()][d.getC()].emptyP();
                d.emptyP();
            }
            catch(NullPointerException e){
                ;
            }
        }
        public void fin(){
            for (int i = 8; i > 0; i--) {
                for (int j = 1; j < 9; j++) {
                    tab[i][j].setEnabled(false);
                }
            }
        }
    }
    public static void main(String[] args) {
        Ajedrez start = new Ajedrez();
    }
    
    
    
//
//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
