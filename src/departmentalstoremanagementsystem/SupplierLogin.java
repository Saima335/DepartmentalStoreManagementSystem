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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author me
 */
public class SupplierLogin extends JFrame{
    JButton b1;
    JLabel l1,l2;
    JTextField t1,t2;
    SupplierLogin(){
        super("Supplier Login");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Username ");
        l2=new JLabel("Password: ");
        t1=new JTextField(20);
        t2=new JTextField(20);
        b1=new JButton("Login");
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(20,10));
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(t2);
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
                    String a=t1.getText();
                    String b=t2.getText();
                    Connector con=new Connector();
                    String q="select * from LoginHasSupplier where username='"+a+"' and password='"+b+"'";
                    ResultSet rs=con.s.executeQuery(q);
                    if(rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successfully");
                        String s="select SupplierID from LoginHasSupplier where username='"+a+"' and password='"+b+"'";
                        ResultSet r=con.s.executeQuery(s);
                        String msg="";
                        while(r.next()){
                            msg=Integer.toString(r.getInt("SupplierID"));
                        }
                        dispose();
                        SupplierSection2 supplier=new SupplierSection2(msg);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                }
                catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}

