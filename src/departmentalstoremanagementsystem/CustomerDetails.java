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
public class CustomerDetails extends JFrame{
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t3,t4,t5,t6;
    JComboBox j1,j2;
    CustomerDetails(){
        
        super("Customer Details");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Customer ID: ");
        l2=new JLabel("Customer Name ");
        l3=new JLabel("Customer City: ");
        l4=new JLabel("Customer State: ");
        l5=new JLabel("Customer Pin Code: ");
        l6=new JLabel("Customer Contact No in the format xxxxxxxxxxx: ");
        t1= new JTextField(20);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6=new JTextField(20);
        
        j1=new JComboBox();
        j2=new JComboBox();
        
        j1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                j2.removeAllItems();
                switch (event.getItem().toString()) {
                    case "Pakistan":
                        j2.addItem("Karachi");
                        j2.addItem("Lahore");
                        j2.addItem("Faisalabad");
                        j2.addItem("Rawalpindi");
                        j2.addItem("Peshawar");
                        break;
                    case "China":
                        j2.addItem("Beijing");
                        j2.addItem("Jinan");
                        j2.addItem("Lhasa");
                        break;
                }
            }
        });
        j1.addItem("Pakistan");
        j1.addItem("China");
        t5.setText("74200");
        t5.setEditable(false);
        
        j2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent event){
                t5.setEditable(true);
                if (event.getItem().toString().equals("Karachi")){
                    t5.setText("74200");
                }
                else if(event.getItem().toString().equals("Lahore")){
                    t5.setText("54000");
                }
                else if(event.getItem().toString().equals("Faisalabad")){
                    t5.setText("38000");
                }
                else if(event.getItem().toString().equals("Rawalpindi")){
                    t5.setText("46000");
                }
                else if(event.getItem().toString().equals("Peshawar")){
                    t5.setText("25000");
                }
                else if (event.getItem().toString().equals("Beijing")){
                    t5.setText("100000");
                }
                else if (event.getItem().toString().equals("Jinan")){
                    t5.setText("250200");
                }
                else if (event.getItem().toString().equals("Lhasa")){
                    t5.setText("850100");
                }
                t5.setEditable(false);
            }
        });
        
        b1=new JButton("Submit");
        b2=new JButton("Home");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(j1);
        p1.add(l4);
        p1.add(j2);
        p1.add(l5);
        p1.add(t5);
        p1.add(l6);
        p1.add(t6);
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
                if(t1.getText().equals("")||t2.getText().equals("")||t6.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                }
                else {
                    try {
                        if (checkAllDigits(t1.getText())){
                            if (checkPhoneNo(t6.getText())){
                                int a=Integer.parseInt(t1.getText());
                                String b=t2.getText();
                                String c=(String)j1.getSelectedItem();
                                String d=(String)j2.getSelectedItem();
                                int ee=Integer.parseInt(t5.getText());
                                String f=t6.getText();
                                Connector con=new Connector();
                                String m="Insert into customerdetails(CustomerID,CustomerName,CustomerCity,CustomerState,CustomerPinCode,ContactNo) values("+a+",'"+b+"','"+c+"','"+d+"',"+ee+",'"+f+"')";
                                con.s.executeUpdate(m);
                                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                                String msg=t1.getText();
                                dispose();
                                CreateAccountCustomer account=new CreateAccountCustomer(msg);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter contatct no in correct format that is xxxxxxxxxxx");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Enter only digits as customer id");
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
    //***********************Do Unique
    public boolean checkPhoneNo(String x){
        String regex = ("\\d{11}");
        if (x.matches(regex)){
            return true;
        }
        else{
            return false;
        }
    }
}

