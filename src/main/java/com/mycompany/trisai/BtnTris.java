/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trisai;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author luca
 */
public class BtnTris extends javax.swing.JButton{
    
    public BtnTris(){
        this.setSize(100, 100);
        this.setText(" ");
        this.setFont(new Font("Arial", Font.PLAIN, 40));
        this.setBackground(Color.white);
        this.setFocusable(false);
    }
}
