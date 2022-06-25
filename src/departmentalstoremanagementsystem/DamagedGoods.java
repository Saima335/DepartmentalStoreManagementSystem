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
public class DamagedGoods extends JFrame {
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    Choice c1;
    DamagedGoods(String msg){
        super("Damaged Goods");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Damsged Goods Voucher No: ");
        l2=new JLabel("Damaged Goods Voucher Date: ");
        l3=new JLabel("Quantity: ");
        l4=new JLabel("Remark: ");
        l5=new JLabel("total Amount: ");
        l6=new JLabel("Product ID: ");
        l7=new JLabel("Supplier ID: ");
        t1= new JTextField(20);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6=new JTextField(20);
        t7=new JTextField(20);
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select distinct ProductID from purchasedgood where SupplierID="+Integer.parseInt(msg));
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        
        /*c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent event){
                c1.addItem("");
                t5.setEditable(true);
                try{
                    Connector c = new Connector();
                    ResultSet rs=c.s.executeQuery("select SupplierID from purchasedgood where PrdouctID="+Integer.parseInt(event.getItem().toString()));
                    while(rs.next()){
                        t5.setText(Integer.toString(rs.getInt("SupplierID")));
                    }
                }catch(Exception ee) { }  
                t5.setEditable(false);
            }
        });*/
        t7.setText(msg);
        t7.setEditable(false);
        
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
        p1.add(c1);
        p1.add(l7);
        p1.add(t7);
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
                    if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||c1.getSelectedIndex()==-1 ||c1.getSelectedItem().equals("")){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t1.getText())){
                                if (checkAllDigits(t3.getText())){
                                    Connector con=new Connector();
                                    int a=Integer.parseInt(t1.getText());
                                    String b=t2.getText();
                                    int c=Integer.parseInt(t3.getText());
                                    String d=t4.getText();
                                    int f=Integer.parseInt(c1.getSelectedItem());
                                    int g=Integer.parseInt(t7.getText());
                                    String l="select ProductRate from productdetails where ProductID="+f;
                                    ResultSet rs  = con.s.executeQuery(l);
                                    int quantity=0,rate=0,quantity2=0;
                                    while(rs.next()){
                                        rate = rs.getInt("ProductRate");
                                    }
                                    String q="select PurchaseQuantity from purchasedgood where ProductID="+f+"and SupplierID="+g;
                                    ResultSet r= con.s.executeQuery(q);
                                    while(r.next()){
                                        quantity = r.getInt("PurchaseQuantity");
                                        System.out.println(quantity);
                                    }
                                    String s="select QuantityOnHand from warehouse_productdetails where ProductID="+f+"and SupplierID="+g;
                                    ResultSet ras= con.s.executeQuery(s);
                                    while(ras.next()){
                                        quantity2 = ras.getInt("QuantityOnHand");
                                    }
                                    if (c<=quantity){
                                        int ee=rate*c;
                                        t5.setText(Integer.toString(ee));
                                        l5.setVisible(true);
                                        t5.setVisible(true);
                                        t5.setEditable(false);
                                        int left=quantity-c;
                                        int left2=quantity2-c;
                                        String m="Insert into damagedgoodssp(DmgGoodsVoucherNo,DmgGoodsVoucherDate,Quantity,Remark,totalAmount,ProductID,SupplierID) values("+a+","+"TO_DATE('"+b+"','DD/MM/YYYY')"+","+c+",'"+d+"',"+ee+","+f+","+g+")";
                                        con.s.executeUpdate(m);
                                        String n="Update purchasedgood set PurchaseQuantity="+left+"where ProductID="+f+"and SupplierID="+g;;
                                        con.s.executeUpdate(n);
                                        String o="Update warehouse_productdetails set QuantityOnHand="+left2+"where ProductID="+f+"and SupplierID="+g;;
                                        con.s.executeUpdate(o);
                                        JOptionPane.showMessageDialog(null, "Damaged Goods Returned Successfully");
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "This much Quantity is not purchased");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as qunatity");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter only digits as damaged good voucher no");
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
