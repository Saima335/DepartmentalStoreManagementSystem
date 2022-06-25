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
public class DepartmentalStoreManagementSystem extends JFrame {
    private JButton b1,b2,b3,b4,b5,b6;
    private JLabel l1;
    DepartmentalStoreManagementSystem(){
        
        super("Departmental Store Management System");
        
        setBackground(Color.BLUE);
	setLayout(null);
	setSize(1000,800);
        
        l1=new JLabel();
	l1.setBounds(0,0,1600,800);
	ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("images/store.jpg"));
	Image i3=ic.getImage().getScaledInstance(1600, 800, Image.SCALE_DEFAULT);
	ImageIcon icc3=new ImageIcon(i3);
	l1.setIcon(icc3);
	add(l1);
	l1.setLayout(null);
        
        b1=new JButton("Product Section");
        b1.setBounds(500,100,300,30);
	b1.setForeground(Color.ORANGE);
	b1.setBackground(Color.BLACK);
	b1.setFont(new Font("serif",Font.ITALIC,20));
        
        b2=new JButton("Customer Section");
        b2.setBounds(500,200,300,30);
	b2.setForeground(Color.ORANGE);
	b2.setBackground(Color.BLACK);
	b2.setFont(new Font("serif",Font.ITALIC,20));
                
        b3=new JButton("Supplier Section");
        b3.setBounds(500,300,300,30);
	b3.setForeground(Color.ORANGE);
	b3.setBackground(Color.BLACK);
	b3.setFont(new Font("serif",Font.ITALIC,20));
        
        b4=new JButton("Warehouse Section");
        b4.setBounds(500,400,300,30);
	b4.setForeground(Color.ORANGE);
	b4.setBackground(Color.BLACK);
	b4.setFont(new Font("serif",Font.ITALIC,20));
        
        b5=new JButton("About Us");
        b5.setBounds(500,500,300,30);
	b5.setForeground(Color.ORANGE);
	b5.setBackground(Color.BLACK);
	b5.setFont(new Font("serif",Font.ITALIC,20));
        
        b6=new JButton("Exit");
        b6.setBounds(500,600,300,30);
	b6.setForeground(Color.ORANGE);
	b6.setBackground(Color.BLACK);
	b6.setFont(new Font("serif",Font.ITALIC,20));
        
        MyActionListener m=new MyActionListener();
        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);
        b4.addActionListener(m);
        b5.addActionListener(m);
        b6.addActionListener(m);
        l1.add(b1);
        l1.add(b2);
        l1.add(b3);
        l1.add(b4);
        l1.add(b5);
        l1.add(b6);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);     
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
            if (e.getActionCommand()=="Product Section"){
                dispose();
                ProductSection product=new ProductSection();
            }
            if(e.getActionCommand()=="Customer Section"){
                dispose();
                CustomerSection customer=new CustomerSection();
                //CustomerLogin customer=new CustomerLogin();
            }
            if(e.getActionCommand()=="Supplier Section"){
                dispose();
                SupplierSection1 supplier=new SupplierSection1();
                //SupplierLogin supplier=new SupplierLogin();
            }
            if(e.getActionCommand()=="Warehouse Section"){
                dispose();
                WarehouseSection warehouse=new WarehouseSection(); 
            }
            if(e.getActionCommand()=="About Us"){
                dispose();
                AboutUs about=new AboutUs(); 
            }
            if(e.getActionCommand()=="Exit"){
                System.exit(0); 
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DepartmentalStoreManagementSystem system=new DepartmentalStoreManagementSystem();
    }
    
}
