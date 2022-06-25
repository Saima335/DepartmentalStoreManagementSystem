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
public class AboutUs extends JFrame {
    private JPanel contentPane;
    private JButton b;
    
    public AboutUs() {

        super("About Us: Sauda Sarf");
        setBackground(new Color(173, 216, 230));
        setBounds(500, 250, 700, 500);
        setLocation(200,100);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("New label");
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("images/store.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(400, 20, 300, 200);
        contentPane.add(l1);


        JLabel l3 = new JLabel("Departmental Store");
        l3.setForeground(new Color(0, 250, 154));
        l3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
        l3.setBounds(80, 40, 400, 55);
        contentPane.add(l3);

        JLabel l4 = new JLabel("Mangement System");
        l4.setForeground(new Color(127, 255, 0));
        l4.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
        l4.setBounds(70, 90, 405, 40);
        contentPane.add(l4);


        JLabel l6 = new JLabel("Name: Saima Kausar");
        l6.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l6.setBounds(70, 198, 600, 35);
        contentPane.add(l6);

        JLabel l7 = new JLabel("FAX: 111-222-3333");
        l7.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l7.setBounds(70, 260, 600, 34);
        contentPane.add(l7);

        JLabel l8 = new JLabel("Email: saimakausar123@gmail.com");
        l8.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
        l8.setBounds(70, 340, 600, 34);
        contentPane.add(l8);

        JLabel l9 = new JLabel("Timing: 10AM to 11PM");
        l9.setFont(new Font("Trebuchet MS", Font.BOLD , 22));
        l9.setBounds(70, 370, 600, 34);
        contentPane.add(l9);


        JLabel l10 = new JLabel("Contact: +921112223333");
        l10.setForeground(new Color(47, 79, 79));
        l10.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
        l10.setBounds(70, 400, 600, 34);
        contentPane.add(l10);
        
        b=new JButton("Home");
        b.setBounds(500, 400, 100, 30);
	b.setForeground(Color.ORANGE);
	b.setBackground(Color.BLACK);
	b.setFont(new Font("serif",Font.ITALIC,20));
        contentPane.add(b);
        
        MyActionListener m=new MyActionListener();
        b.addActionListener(m);

        contentPane.setBackground(Color.WHITE);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand()=="Home"){
                dispose();
                DepartmentalStoreManagementSystem system=new DepartmentalStoreManagementSystem();
            }
        }
    }
    
}
