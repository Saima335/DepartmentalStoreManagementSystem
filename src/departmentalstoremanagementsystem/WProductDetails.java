/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentalstoremanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author me
 */
public class WProductDetails extends JFrame{
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t3,t4,t5,t6;
    Choice c1,c2;
    WProductDetails(){
        
        super("Warehouse Product Details");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Product ID: ");
        l2=new JLabel("Rack No: ");
        l3=new JLabel("Quantity On Hand: ");
        l4=new JLabel("Reorder Level Quantity: ");
        l5=new JLabel("Rate: ");
        l6=new JLabel("Supplier ID: ");
        t1= new JTextField(20);
        t2= new JTextField(20);
        t3=new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        c2 = new Choice();
        try{
            Connector c = new Connector();
            //*******************************************************
            ResultSet rs=c.s.executeQuery("select ProductID from productdetails");
	    while(rs.next()){
                c2.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        c1 = new Choice();
        try{
            Connector c = new Connector();
            //*******************************************************
            ResultSet rs=c.s.executeQuery("select SupplierID from supplierdetails");
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("SupplierID")));
	    }
        }catch(Exception ee) { }
        b1=new JButton("Submit");
        b2=new JButton("Home");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(c2);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(c1);
        add(p1,BorderLayout.NORTH);
        JPanel p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(b1);
        p2.add(b2);
        add(p2, BorderLayout.SOUTH);
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
        b2.addActionListener(a);
        b1.addMouseListener(new MouseAdapter() {
            Color color = b1.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = b1.getForeground();
               b1.setForeground(Color.green); // change the color to green when mouse over a button
               b1.setBackground(Color.WHITE);
            }
            public void mouseExited(MouseEvent me) {
               b1.setForeground(color);
               b1.setBackground(Color.BLACK);
            }
        });
        b2.addMouseListener(new MouseAdapter() {
            Color color = b2.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = b2.getForeground();
               b2.setForeground(Color.green); // change the color to green when mouse over a button
               b2.setBackground(Color.WHITE);
            }
            public void mouseExited(MouseEvent me) {
               b2.setForeground(color);
               b2.setBackground(Color.BLACK);
            }
        });
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    WProductDetails(String s1,String s2,String s3){
        super("Warehouse Product Details");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Product ID: ");
        l2=new JLabel("Rack No: ");
        l3=new JLabel("Quantity On Hand: ");
        l4=new JLabel("Reorder Level Quantity: ");
        l5=new JLabel("Rate: ");
        l6=new JLabel("Supplier ID: ");
        t1= new JTextField(20);
        t1.setText(s1);
        t2= new JTextField(20);
        t3=new JTextField(20);
        t3.setText(s2);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6=new JTextField(20);
        t6.setText(s3);
        t1.setEditable(false);
        t3.setEditable(false);
        t6.setEditable(false);
        b1=new JButton("Submit");
        b2=new JButton("Home");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(t6);
        try{
            Connector con=new Connector();
            ResultSet r=con.s.executeQuery("select RackNo,ReorderLevelQuantity from warehouse_requirementofproduct where ProductID="+Integer.parseInt(t1.getText()));
            while(r.next()){
                t2.setText(r.getString("RackNo"));
                t4.setText(Integer.toString(r.getInt("ReorderLevelQuantity")));
            }
            ResultSet q=con.s.executeQuery("select ProductRate from productdetails where ProductID="+Integer.parseInt(t1.getText()));
            while(q.next()){
                t5.setText(Integer.toString(q.getInt("ProductRate")));
            }
            t2.setEditable(false);
            t4.setEditable(false);
            t5.setEditable(false);
            int a=Integer.parseInt(t1.getText());
            String b=t2.getText();
            int c=Integer.parseInt(t3.getText());
            int d=Integer.parseInt(t4.getText());
            int ee=Integer.parseInt(t5.getText());
            int j=Integer.parseInt(t6.getText());
            String m="Insert into warehouse_productdetails(ProductID,RackNo,QuantityOnHand,ReorderLevelQuantity,Rate,SupplierID) values("+a+",'"+b+"',"+c+","+d+","+ee+","+j+")";
            con.s.executeUpdate(m);
        }
        catch(Exception ee){System.out.println(ee); }
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
		
		if(e.getSource()==b1) {
                    if(t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||c1.getSelectedIndex()==-1){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            
                                if (checkAllDigits(t3.getText())){
                                    if (checkAllDigits(t4.getText())){
                                        if (checkAllDigits(t5.getText())){
                                            int a=Integer.parseInt(c2.getSelectedItem());
                                            String b=t2.getText();
                                            int c=Integer.parseInt(t3.getText());
                                            int d=Integer.parseInt(t4.getText());
                                            int ee=Integer.parseInt(t5.getText());
                                            int j=Integer.parseInt(c1.getSelectedItem());
                                            Connector con=new Connector();
                                            String m="Insert into warehouse_productdetails(ProductID,RackNo,QuantityOnHand,ReorderLevelQuantity,Rate,SupplierID) values("+a+",'"+b+"',"+c+","+d+","+ee+","+j+")";
                                            con.s.executeUpdate(m);
                                            JOptionPane.showMessageDialog(null, "Prdouct Details Added Successfully");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Enter only digits as product rate");
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter only digits as product reorder level quantity");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as product quantity on hand");
                                }
                            
                        }catch(Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                        }
                    }
		}
            if (e.getActionCommand()=="Home"){
                dispose();
                DepartmentalStoreManagementSystem system=new DepartmentalStoreManagementSystem();
            }
        }
    }
    public boolean checkAllDigits(String x){
        boolean digitFound = false;//Initialize boolean varibale digitFound to false as we have not searched for digits in item code
        for (char ch : x.toCharArray()) {//For every element of the character array containing each element of the item code 

            if (Character.isDigit(ch)) {//If element of character array is digit
                digitFound = true;//Assign true to digitFound
            } else {//If element is not a digit
                digitFound = false;//Assign false to digitFound
                break;//Break the loop as there is no element of item_code which is not digit
            }
        }
        return digitFound;
    }
}

