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
public class CreateAccountCustomer extends JFrame{
    JButton b1;
    JLabel l1,l2,l3;
    JTextField t1,t2,t3;
    CreateAccountCustomer(String msg){
        super("Customer Account Customer");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Customer ID: ");
        l2=new JLabel("Username ");
        l3=new JLabel("Password: ");
        t1=new JTextField(20);
        t2=new JTextField(20);
        t3=new JTextField(20);
        b1=new JButton("Create Account");
        t1.setText(msg);
        t1.setEditable(false);
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
        p1.add(l3);
        p1.add(t3);
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
            if(e.getSource()==b1) {
                try{
                    int c=Integer.parseInt(t1.getText());
                    String a=t2.getText();
                    String b=t3.getText();
                    Connector con=new Connector();
                    String m="Insert into LoginHasCustomer(username,password,CustomerID) values('"+a+"','"+b+"',"+c+")";
                    con.s.executeUpdate(m);
                    JOptionPane.showMessageDialog(null, "Customer Account Created Successfully");
                    String msg=t1.getText();
                    dispose();
                    OrderDetails order=new OrderDetails(msg);
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}
