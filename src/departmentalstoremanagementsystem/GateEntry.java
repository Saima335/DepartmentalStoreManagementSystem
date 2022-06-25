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
public class GateEntry extends JFrame{
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9;
    Choice c1;
    GateEntry(){
        
        super("Gate Entry");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Gate Entry No: ");
        l2=new JLabel("Gate Entry Date Time in format yyyy/mm/dd:hh:mi:ssam: ");
        l3=new JLabel("Bill No: ");
        l4=new JLabel("Vehicle No: ");
        l5=new JLabel("Driver Name: ");
        l6=new JLabel("Quantituy on Bill: ");
        l7=new JLabel("Actual Quantity: ");
        l8=new JLabel("Remarks: ");
        l9=new JLabel("Product ID: ");
        t1= new JTextField(20);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6=new JTextField(20);
        t7= new JTextField(20);
        t8= new JTextField(20);
        t9= new JTextField(20);
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select ProductID from productdetails");
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        
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
        p1.add(l7);
        p1.add(t7);
        p1.add(l8);
        p1.add(t8);
        p1.add(l9);
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
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //````this.dispose take inner class object so use dispose
            //Usually operate in one form so use dispose
		
		if(e.getSource()==b1) {
                    if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||t6.getText().equals("")||t7.getText().equals("")||t8.getText().equals("")||c1.getSelectedIndex()==-1){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t1.getText())){
                                if (checkAllDigits(t3.getText())){
                                    if (checkAllDigits(t6.getText())){
                                        if (checkAllDigits(t7.getText())){
                                                int a=Integer.parseInt(t1.getText());
                                                String b=t2.getText();
                                                int c=Integer.parseInt(t3.getText());
                                                String d=t4.getText();
                                                String ee=t5.getText();
                                                int f=Integer.parseInt(t6.getText());
                                                int g=Integer.parseInt(t7.getText());
                                                String h=t8.getText();
                                                /*int i=Integer.parseInt(t9.getText());
                                                Connector con=new Connector();
                                                String q="select ProductID from productdetails where ProductID="+i;
                                                ResultSet rs  = con.s.executeQuery(q);
                                                if(rs.next())
                                                {
                                                    //String m="Insert into gateentry(GateEntryNo,GateEntryDateTime,BillNo,VehicleNo,DriverName,QuantityOnBill,ActualQuantity,Remarks,ProductID) values("+a+convert(datetime,",'"+b+"'",5)+","+c+",'"+d+"','"+ee+"',"+f+","+g+","+h+",'"+i+")";
                                                    //con.s.executeUpdate(m);
                                                    JOptionPane.showMessageDialog(null, "Gate Entry Done Successfully");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Prdouct ID doesn't exist");
                                                }*/
                                                int i=Integer.parseInt(c1.getSelectedItem());
                                                Connector con=new Connector();
                                                String m="Insert into gateentry(GateEntryNo,GameEntryDateTime,BillNo,VehicleNo,DriverName,QuantityOnBill,ActualQuantity,Remarks,ProductID) values("+a+",TO_DATE('"+b+"','yyyy/mm/dd:hh:mi:ssam')"+","+c+",'"+d+"','"+ee+"',"+f+","+g+",'"+h+"',"+i+")";
                                                //System.out.println(m);
                                                con.s.executeUpdate(m);
                                                JOptionPane.showMessageDialog(null, "Gate Entry Done Successfully");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Enter only digits as actual quantity");
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Enter only digits as quantity on bill");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as bill no");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter only digits as gate entry no");
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
