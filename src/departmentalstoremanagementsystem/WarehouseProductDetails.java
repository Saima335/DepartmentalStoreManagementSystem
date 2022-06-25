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
public class WarehouseProductDetails extends JFrame {
    /*JButton b1;
    Choice c1,c2;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t3,t4,t5,t6;
    /*WarehouseProductDetails(){
        super("Warehouse Product Details");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Product ID: ");
        l2=new JLabel("Rack No: ");
        l3=new JLabel("Quantity On Hand: ");
        l4=new JLabel("Reorder Level Quantity: ");
        l5=new JLabel("Rate: ");
        l6=new JLabel("Supplier ID: ");
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            //*******************************************************
            ResultSet rs=c.s.executeQuery("select ProductID from productdetails");
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        c2 = new Choice();
        try{
            Connector c = new Connector();
            //*******************************************************
            ResultSet rs=c.s.executeQuery("select SupplierID from supplierdetails");
	    while(rs.next()){
                c2.add(Integer.toString(rs.getInt("SupplierID")));
	    }
        }catch(Exception ee) { }
        try{
            Connector c = new Connector();
            ResultSet r=c.s.executeQuery("select ProductQuantityOnHand,ProductReorderLevelQuantity,ProductRate from productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem()));
            while(r.next()){
                t3.setText(Integer.toString(r.getInt("ProductQuantityOnHand")));
                t4.setText(Integer.toString(r.getInt("ProductReorderLevelQuantity")));
                t5.setText(Integer.toString(r.getInt("ProductRate")));
                //Connector con=new Connector();
                //String d="delete from productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem());
                //con.s.executeUpdate(d);
            }
            t3.setEditable(false);
            t4.setEditable(false);
            t5.setEditable(false);
        }catch(Exception ee) {System.out.println(ee); }
         c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent event){
                try{
                    System.out.println("Done");
                    t3.setEditable(true);
                    t4.setEditable(true);
                    t5.setEditable(true);;
                    //c1.add("");
                    Connector c = new Connector();
                    ResultSet r=c.s.executeQuery("select ProductQuantityOnHand,ProductReorderLevelQuantity,ProductRate from productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem()));
                    while(r.next()){
                        t3.setText(Integer.toString(r.getInt("ProductQuantityOnHand")));
                        t4.setText(Integer.toString(r.getInt("ProductReorderLevelQuantity")));
                        t5.setText(Integer.toString(r.getInt("ProductRate")));
                        //Connector con=new Connector();
                        //String d="delete from productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem());
                        //con.s.executeUpdate(d);
                    }
                    t3.setEditable(false);
                    t4.setEditable(false);
                    t5.setEditable(false);
                }catch(Exception ee) {System.out.println(ee); }
            }
         });
        
        b1=new JButton("Submit");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(c1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
        p1.add(l4);
        p1.add(t4);
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(c2);
        add(p1,BorderLayout.NORTH);
        JPanel p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(b1);
        add(p2, BorderLayout.SOUTH);
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
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
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/
    /*WarehouseProductDetails(String msg){
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
        t1.setText(msg);
        t1.setEditable(false);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6= new JTextField(20);
        
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select SupplierID from purchasedgood where ProductID="+Integer.parseInt(msg));
	    while(rs.next()){
                t6.setText(Integer.toString(rs.getInt("SupplierID")));
	    }
            ResultSet r=c.s.executeQuery("select RackNo,QuantityOnHand,ReorderLevelQuantity from warehouse_requirementofproduct where ProductID="+Integer.parseInt(msg));
	    while(r.next()){
                t2.setText(r.getString("RackNo"));
                t3.setText(Integer.toString(r.getInt("QuantityOnHand")));
                t4.setText(Integer.toString(r.getInt("ReorderLevelQuantity")));
	    }
            ResultSet q=c.s.executeQuery("select Rate from warehouse_productdetails where ProductID="+Integer.parseInt(msg));
	    while(q.next()){
                t5.setText(Integer.toString(q.getInt("Rate")));
	    }
            t6.setEditable(false);
            t5.setEditable(false);
            t2.setEditable(false);
            t3.setEditable(false);
            t4.setEditable(false);
        }catch(Exception ee) { System.out.println(ee);}
        
        b1=new JButton("Submit");
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
        add(p1,BorderLayout.NORTH);
        JPanel p2=new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(b1);
        add(p2, BorderLayout.SOUTH);
        MyActionListener a=new MyActionListener();
        b1.addActionListener(a);
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
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
		
		if(e.getSource()==b1) {
                    if(t2.getText().equals("")||t5.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t5.getText())){
                                Connector con=new Connector();
                                ResultSet rs=con.s.executeQuery("select QuantiyOnHand from warehouse_productdetails where PrdouctID="+Integer.parseInt(t1.getText()));
                                int quantity=0;
                                while (rs.next()){
                                    quantity=rs.getInt("QuantityOnHand");
                                }
                                String m="Update warehouse_productdetails set QuantiyOnHand="+t3.getText()+quantity;
                                con.s.executeUpdate(m);
                                /*int a=Integer.parseInt(t1.getText());
                                String b=t2.getText();
                                int c=Integer.parseInt(t3.getText());
                                int d=Integer.parseInt(t4.getText());
                                int ee=Integer.parseInt(t5.getText());
                                int f=Integer.parseInt(t6.getText());
                                Connector con=new Connector();
                                String m="Insert into warehouse_productdetails(ProductID,RackNo,QuantityOnHand,ReorderLevelQuantity,Rate,SupplierID) values("+a+",'"+b+"',"+c+","+d+","+ee+","+f+")";
                                con.s.executeUpdate(m);
                                JOptionPane.showMessageDialog(null, "Warehouse Prdouct Details Added Successfully");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter only digits as rate");
                            }
                        }catch(Exception ex) {
                                JOptionPane.showMessageDialog(null, ex);
                        }
                    }
		}
        }
    }*/
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
