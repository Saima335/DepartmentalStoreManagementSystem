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
public class PurchasedGoods extends JFrame {
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4,t5;
    Choice c1;
    PurchasedGoods(String msg){
        super("Purchase Goods");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Product ID: ");
        l2=new JLabel("Supplier ID: ");
        l3=new JLabel("Purchase No: ");
        l4=new JLabel("Purchase Date: ");
        l5=new JLabel("Purchase Quantity: ");
        t1= new JTextField(20);
        t2= new JTextField(20);
        t2.setText(msg);
        t2.setEditable(false);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select ProductID from warehouse_requirementofproduct where SupplierID="+Integer.parseInt(msg));
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("ProductID")));
	    }
            ResultSet r=c.s.executeQuery("select QuantityRequired from warehouse_requirementofproduct where ProductID="+Integer.parseInt(c1.getSelectedItem()));
            while(r.next()){
                t5.setText(Integer.toString(r.getInt("QuantityRequired")));
	    }
            t1.setEditable(false);
            t5.setEditable(false);
        }catch(Exception ee) {System.out.println(ee); }
        
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent event){
                t1.setEditable(true);
                t5.setEditable(true);
                try{
                    Connector c = new Connector();
                    ResultSet r=c.s.executeQuery("select QuantityRequired from warehouse_requirementofproduct where ProductID="+Integer.parseInt(c1.getSelectedItem()));
                    while(r.next()){
                        t5.setText(Integer.toString(r.getInt("QuantityRequired")));
                    }
                    t1.setEditable(false);
                    t5.setEditable(false);
                }catch(Exception ee) {System.out.println(ee); }
            }
        });
        
        b1=new JButton("Submit");
        b2=new JButton("Home");
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
                    if(t3.getText().equals("") || t4.getText().equals("")||c1.getSelectedIndex()==-1){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t3.getText())){
                                    Connector con=new Connector();
                                    int a=Integer.parseInt(c1.getSelectedItem());
                                    int b=Integer.parseInt(t2.getText());
                                    int c=Integer.parseInt(t3.getText());
                                    String d=t4.getText();
                                    int ee=Integer.parseInt(t5.getText());
                                    ResultSet rs=con.s.executeQuery("select QuantityOnHand from warehouse_productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem())+"and SupplierID="+Integer.parseInt(t2.getText()));
                                    //System.out.println("select QuantityOnHand from warehouse_productdetails where ProductID="+Integer.parseInt(c1.getSelectedItem())+"and SupplierID="+Integer.parseInt(t2.getText()));
                                    //System.out.println(rs.next());
                                    if (rs.next()){
                                    int quantity=rs.getInt("QuantityOnHand");
                                    
                                    //System.out.println("Done1");
                                    int x=Integer.parseInt(t5.getText())+quantity;
                                    String n="Update warehouse_productdetails set QuantityOnHand="+x+"where ProductID="+Integer.parseInt(c1.getSelectedItem())+"and SupplierID="+Integer.parseInt(t2.getText());
                                    //System.out.println("Update warehouse_productdetails set QuantityOnHand="+x+"where ProductID="+Integer.parseInt(c1.getSelectedItem())+"and SupplierID="+Integer.parseInt(t2.getText()));
                                    //System.out.println("Done2");
                                    con.s.executeUpdate(n);
                                    }
                                    else{
                                        
                                        //System.out.println("Done2");
                                        WProductDetails w=new WProductDetails((String)c1.getSelectedItem(),t5.getText(),t2.getText());
                                            
                                        
                                    }
                                    String m="Insert into purchasedgood(ProductID,SupplierID,PurchaseNo,PurchaseDate,PurchaseQuantity) values("+a+","+b+","+c+","+"TO_DATE('"+d+"','DD/MM/YYYY')"+","+ee+")";
                                    con.s.executeUpdate(m);
                                    JOptionPane.showMessageDialog(null, "Purchase Done Successfully");
                                    //System.out.println("Done3");
                                    //String msg=(String)c1.getSelectedItem();
                                    //dispose();
                                    //WarehouseProductDetails details=new WarehouseProductDetails(msg);
                                    /*String l="select ProductQuantityOnHand, ProductReorderLevelQuantity from productdetails where ProductID="+b;
                                    ResultSet rs  = con.s.executeQuery(l);
                                    int quantity=0,reorderlevel=0;
                                    while(rs.next()){
                                        quantity = rs.getInt("ProductQuantityOnHand");
                                        reorderlevel = rs.getInt("ProductReorderLevelQuantity");
                                    }
                                    if (ee<=reorderlevel){
                                        int add=quantity+ee;
                                        //String m="Insert into purchasedgood(ProductID,SupplierID,PurchaseNo,PurchaseDate,PurchaseQuantity) values("+a+","+b+","+c+","+TO_DATE(d)+","+ee+")";
                                        //con.s.executeUpdate(m);
                                        String n="Update productdetails set ProductQuantityOnHand="+add+"where ProductID="+b;
                                        con.s.executeUpdate(n);
                                        JOptionPane.showMessageDialog(null, "Purchase Done Successfully");
                                    }
                                    else if(ee>reorderlevel){
                                        JOptionPane.showMessageDialog(null, "Can't Purchase that much Quantity");
                                    }*/
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as purchase no");
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
