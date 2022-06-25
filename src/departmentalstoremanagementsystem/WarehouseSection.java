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
public class WarehouseSection extends JFrame {
    private JButton b1,b2,b3,b4,b5;
    private JLabel l1;
    WarehouseSection(){
        
        super("Warehouse Section");
        
        setBackground(Color.BLUE);
	setLayout(null);
	setSize(1000,800);
        
        l1=new JLabel();
	l1.setBounds(0,0,1600,800);
	ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("images/WarehouseSection.jpg"));
	Image i3=ic.getImage().getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
	ImageIcon icc3=new ImageIcon(i3);
	l1.setIcon(icc3);
	add(l1);
	l1.setLayout(null);
        
        //For all screens create JFrame classess //so here 4 make
        //In each frame add home so home open
        b1=new JButton("Gate Entry");
        b1.setBounds(500,100,350,30);
	b1.setForeground(Color.ORANGE);
	b1.setBackground(Color.BLACK);
	b1.setFont(new Font("serif",Font.ITALIC,20));
                
        b2=new JButton("Warehouse Product Details");
        b2.setBounds(500,220,350,30);
	b2.setForeground(Color.ORANGE);
	b2.setBackground(Color.BLACK);
	b2.setFont(new Font("serif",Font.ITALIC,20));
        
        b3=new JButton("Warehouse Product Requirement");
        b3.setBounds(500,340,350,30);
	b3.setForeground(Color.ORANGE);
	b3.setBackground(Color.BLACK);
	b3.setFont(new Font("serif",Font.ITALIC,20));
        
        b4=new JButton("Gate Pass");
        b4.setBounds(500,460,350,30);
	b4.setForeground(Color.ORANGE);
	b4.setBackground(Color.BLACK);
	b4.setFont(new Font("serif",Font.ITALIC,20));
        
        b5=new JButton("Home");
        b5.setBounds(500,580,350,30);
	b5.setForeground(Color.ORANGE);
	b5.setBackground(Color.BLACK);
	b5.setFont(new Font("serif",Font.ITALIC,20));
        
        MyActionListener m=new MyActionListener();
        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);
        b4.addActionListener(m);
        b5.addActionListener(m);
        l1.add(b1);
        l1.add(b2);
        l1.add(b3);
        l1.add(b4);
        l1.add(b5);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
            if (e.getActionCommand()=="Gate Entry"){
                dispose();
                GateEntry entry=new GateEntry();
            }
            /*if(e.getActionCommand()=="Warehouse Product Details"){
                dispose();
                SearchWarehouseProduct search=new SearchWarehouseProduct();
            }*/
            if(e.getActionCommand()=="Warehouse Product Details"){
                dispose();
                WProductDetails details=new WProductDetails();
            }
            if(e.getActionCommand()=="Warehouse Product Requirement"){
                dispose();
                WarehouseProductRequirement requirement=new WarehouseProductRequirement();
            }
            if(e.getActionCommand()=="Gate Pass"){
                dispose();
                GatePass pass=new GatePass();
            }
            if (e.getActionCommand()=="Home"){
                dispose();
                DepartmentalStoreManagementSystem system=new DepartmentalStoreManagementSystem();
            }
        }
    }
}
