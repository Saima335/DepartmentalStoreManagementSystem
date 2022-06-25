/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentalstoremanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author me
 */
public class SupplierSection1 extends JFrame {
    private JButton b1,b2,b3;
    private JLabel l1;
    SupplierSection1(){
        
        super("Supplier Section");
        
        setBackground(Color.BLUE);
	setLayout(null);
	setSize(1000,800);
        
        l1=new JLabel();
	l1.setBounds(0,0,1600,800);
	ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("images/SupplierSection.jpg"));
	Image i3=ic.getImage().getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
	ImageIcon icc3=new ImageIcon(i3);
	l1.setIcon(icc3);
	add(l1);
	l1.setLayout(null);
        
        //For all screens create JFrame classess //so here 2 make
        //In each frame add home so home open
        b1=new JButton("Create Account");
        b1.setBounds(500,250,300,30);
	b1.setForeground(Color.ORANGE);
	b1.setBackground(Color.BLACK);
	b1.setFont(new Font("serif",Font.ITALIC,20));
                
        b2=new JButton("Login");
        b2.setBounds(500,450,300,30);
	b2.setForeground(Color.ORANGE);
	b2.setBackground(Color.BLACK);
	b2.setFont(new Font("serif",Font.ITALIC,20));
        
        b3=new JButton("Home");
        b3.setBounds(500,600,300,30);
	b3.setForeground(Color.ORANGE);
	b3.setBackground(Color.BLACK);
	b3.setFont(new Font("serif",Font.ITALIC,20));
        
        MyActionListener m=new MyActionListener();
        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);
        l1.add(b1);
        l1.add(b2);
        l1.add(b3);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
            if (e.getActionCommand()=="Create Account"){
                dispose();
                SupplierDetails details=new SupplierDetails();
            }
            if(e.getActionCommand()=="Login"){
                dispose();
                SupplierLogin login=new SupplierLogin();
            }
            if (e.getActionCommand()=="Home"){
                dispose();
                DepartmentalStoreManagementSystem system=new DepartmentalStoreManagementSystem();
            }
        }
    }
}

