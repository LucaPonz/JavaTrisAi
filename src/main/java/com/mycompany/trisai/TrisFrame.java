/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.trisai;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author luca
 */
public class TrisFrame extends javax.swing.JFrame {

    /**
     * Creates new form TrisFrame
     */
    public boolean player = true; //human
    public BtnTris[] grid = new BtnTris[9];
    public String human = "O";
    public String computer = "X"; //isMaximasing
    public int [][] triple = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    
    
    public TrisFrame() {
        initComponents();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BtnTris btn = new BtnTris();
                btn.setLocation(j * btn.getWidth(), i * btn.getHeight());
                this.add(btn);
                grid[i * 3 + j] = btn;
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(player){
                            btn.setText(human);
                            if(checkVictory() != null){
                                switch(checkVictory()){
                                    case 1:
                                        JOptionPane.showMessageDialog(null, "HA vinto X");
                                        break;
                                    case -1:
                                        JOptionPane.showMessageDialog(null, "HA vinto O");
                                        break;
                                    case 0:
                                        JOptionPane.showMessageDialog(null, "Pareggio");
                                        break;
                                }
                            }else changePlayer();
                        }
                    }
                });
            }
        }
        this.setSize(300,300);
        this.setLayout(new GridLayout(3,3));
    }
    
    public Integer checkVictory(){
        Integer winner = null;
        for (int i=0; i<triple.length; i++){
            if(grid[triple[i][0]].getText().equals(human) &&
               grid[triple[i][1]].getText().equals(human) &&
               grid[triple[i][2]].getText().equals(human)){
               winner = -1;
            }
            if(grid[triple[i][0]].getText().equals(computer) &&
               grid[triple[i][1]].getText().equals(computer) &&
               grid[triple[i][2]].getText().equals(computer)){
               winner = 1;
            }
        }
        int freeSpot = 0;
        for (int i=0; i<9; i++){
            if(grid[i].getText().equals(" ")){
                freeSpot++;
            }
        }
        if (winner == null && freeSpot == 0){
            winner = 0;
        }
        return winner;
    }
    
    
    
    public void changePlayer(){
        if(player){
            player = false;
            playAi();
        }
        else{
            player = true;
        }
    }
    
    public void playAi(){
        int bestScore = -2;
        Integer score;
        int move = 0;
        for(int i=0; i<9; i++){
            if(grid[i].getText().equals(" ")){
                grid[i].setText(computer);
                score= miniMax(grid, true);
                grid[i].setText(" ");
                if (score > bestScore){
                    bestScore = score;
                    move = i;
                }
        }
        }
        grid[move].setText(computer);
        
        if(checkVictory() != null){
            switch (checkVictory()) {
                case 1:
                    JOptionPane.showMessageDialog(null, "HA vinto X");
                    break;
                case -1:
                    JOptionPane.showMessageDialog(null, "HA vinto O");
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Pareggio");
                    break;
            }
        } else
            changePlayer();
    }
    
    
    
    public Integer miniMax(BtnTris[] grid, boolean isMaximizing){
        Integer result = checkVictory();
        if (result != null){
            return result;
        }
        
        else{
            if(isMaximizing){
                int bestScore = 2;
                for(int i=0; i<9; i++){
                    if(grid[i].getText().equals(" ")){
                        grid[i].setText(human);
                        result = miniMax(grid, false);
                        grid[i].setText(" ");
                        if(result < bestScore){
                            bestScore = result;
                        }
                    }
                }
                return bestScore;
                
            }else{
                int bestScore = -2;
                for(int i=0; i<9; i++){
                    if(grid[i].getText().equals(" ")){
                        grid[i].setText(computer);
                        result = miniMax(grid, true);
                        grid[i].setText(" ");
                        if(result > bestScore){
                            bestScore = result;
                        }
                    }
                }
                return bestScore;
            }
            
        }
    }

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrisFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrisFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
