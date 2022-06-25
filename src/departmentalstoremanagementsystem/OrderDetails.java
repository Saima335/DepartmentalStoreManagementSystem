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
public class OrderDetails extends JFrame {
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4,t5;
    Choice c1,c2;
    OrderDetails(String msg){
        super("Customer Order");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Customer ID: ");
        l2=new JLabel("Product ID: ");
        l3=new JLabel("Order ID: ");
        l4=new JLabel("Product Quantity: ");
        l5=new JLabel("Total Amount: ");
        t1= new JTextField(20);
        t1.setText(msg);
        t1.setEditable(false);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select CustomerID from customerdetails");
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("CustomerID")));
	    }
        }catch(Exception ee) { }
        
        c2 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select ProductID from productdetails");
	    while(rs.next()){
                c2.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        
        b1=new JButton("Submit");
        b2=new JButton("Home");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(c1);
        p1.add(l2);
        p1.add(c2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(l5);
        p1.add(t5);
        l5.setVisible(false);
        t5.setVisible(false);
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
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
		
		if(e.getSource()==b1) {
                    if(t3.getText().equals("")||t4.getText().equals("")||c1.getSelectedIndex()==-1 ||c2.getSelectedIndex()==-1){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t3.getText())){
                                if (checkAllDigits(t4.getText())){
                                    Connector con=new Connector();
                                    int a=Integer.parseInt(c1.getSelectedItem());
                                    int b=Integer.parseInt(c2.getSelectedItem());
                                    int c=Integer.parseInt(t3.getText());
                                    int d=Integer.parseInt(t4.getText());
                                    String l="select ProductQuantityOnHand, ProductReorderLevelQuantity, ProductRate from productdetails where ProductID="+b;
                                    ResultSet rs  = con.s.executeQuery(l);
                                    int quantity=0,reorderlevel=0,rate=0;
                                    while(rs.next()){
                                        quantity = rs.getInt("ProductQuantityOnHand");
                                        reorderlevel = rs.getInt("ProductReorderLevelQuantity");
                                        rate = rs.getInt("ProductRate");
                                    }
                                    if (d<=quantity){
                                        //d=Integer.parseInt(t4.getText());
                                        int ee=rate*d;
                                        t5.setText(Integer.toString(ee));
                                        l5.setVisible(true);
                                        t5.setVisible(true);
                                        t5.setEditable(false);
                                        int left=quantity-d;
                                        System.out.println("Done");
                                        String m="Insert into customerorderdetails(CustomerID,ProductID,OrderID,productQuantity,TotalAmount) values("+a+","+b+","+c+","+d+","+ee+")";
                                        con.s.executeUpdate(m);
                                        String n="Update productdetails set ProductQuantityOnHand="+left+"where ProductID="+b;
                                        con.s.executeUpdate(n);
                                        JOptionPane.showMessageDialog(null, "Order Done Successfully");
                                    }
                                    else if(d>quantity && (quantity-d)<=reorderlevel){
                                        JOptionPane.showMessageDialog(null, "Quantity not present in store..Go to requirement of product in product section to take prduct from warehouse");
                                    }
                                    else if((quantity-d)>reorderlevel){
                                        JOptionPane.showMessageDialog(null, "Can't Purchase this much Quantity");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as order id");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter only digits as product qunatity");
                            }
                        }
                        catch(Exception ex) {
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
