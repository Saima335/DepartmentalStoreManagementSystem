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
//************************************Supplier id not be here
public class ProductRequirement extends JFrame{
    JButton b1,b2;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    Choice c1,c2;
    ProductRequirement(){
        super("Product Requirement");
        setLayout(new BorderLayout());
        setSize(400,500);
        l1=new JLabel("Product ID: ");
        l2=new JLabel("Supplier ID: ");
        l3=new JLabel("Product Requirement ID: ");
        l4=new JLabel("Product Requirement Date: ");
        l5=new JLabel("Prdouct Quantity Required: ");
        l6=new JLabel("Product Quantity On Hand: ");
        l7=new JLabel("Product Reorder Level Quantity: ");
        
        t1= new JTextField(20);
        t2= new JTextField(20);
        t3= new JTextField(20);
        t4=new JTextField(20);
        t5=new JTextField(20);
        t6= new JTextField(20);
        t7=new JTextField(20);
        
        c1 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select ProductID from warehouse_productdetails");
	    while(rs.next()){
                c1.add(Integer.toString(rs.getInt("ProductID")));
	    }
        }catch(Exception ee) { }
        
        c2 = new Choice();
        try{
            Connector c = new Connector();
            ResultSet rs=c.s.executeQuery("select SupplierID from supplierdetails");
	    while(rs.next()){
                c2.add(Integer.toString(rs.getInt("SupplierID")));
	    }
        }catch(Exception ee) { }
        
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent event){
                try{
                    c1.addItem("");
                    Connector c = new Connector();
                    ResultSet rs=c.s.executeQuery("select ProductQuantityOnHand, ProductReorderLevelQuantity from productdetails where ProductID="+Integer.parseInt(event.getItem().toString()));
                    while(rs.next()){
                        t6.setText(Integer.toString(rs.getInt("ProductQuantityOnHand")));
                        t7.setText(Integer.toString(rs.getInt("ProductReorderLevelQuantity")));
                    }
                    t6.setEditable(false);
                    t7.setEditable(false);
                }catch(Exception ee) { }
            }
        });
        
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
        p1.add(l6);
        p1.add(t6);
        p1.add(l7);
        p1.add(t7);
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
                    if(t3.getText().equals("") || t4.getText().equals("")|| t5.getText().equals("")||c1.getSelectedIndex()==-1 ||c1.getSelectedItem().equals("")||c2.getSelectedIndex()==-1){
                            JOptionPane.showMessageDialog(null, "Please, Fill All Required Fields");
                    }
                    else {
                        try {
                            if (checkAllDigits(t3.getText())){
                                if (checkAllDigits(t5.getText())){
                                    Connector con=new Connector();
                                    int a=Integer.parseInt(c1.getSelectedItem());
                                    int b=Integer.parseInt(c2.getSelectedItem());
                                    int c=Integer.parseInt(t3.getText());
                                    String d=t4.getText();
                                    int ee=Integer.parseInt(t5.getText());
                                    int f=Integer.parseInt(t6.getText());
                                    int g=Integer.parseInt(t7.getText());
                                    String l="select max(ReorderLevelQuantity), max(QuantityOnHand) from warehouse_productdetails group by ProductID having ProductID="+a;
                                    ResultSet rs  = con.s.executeQuery(l);
                                    
                                    int reorderlevel=0,quantity=0;
                                    while(rs.next()){
                                        reorderlevel = rs.getInt("max(ReorderLevelQuantity)");
                                        //System.out.println(reorderlevel);
                                        quantity = rs.getInt("max(QuantityOnHand)");
                                    }
                                    if (ee<=reorderlevel && ee<=quantity){
                                        String m="Insert into requirementofproduct(ProductID,SupplierID,ProductRequirementID,ProductRequirementDate,ProductQunatityRequired,ProductQuantityOnHand,ProductReorderLevelQuantity) values("+a+","+b+","+c+","+"TO_DATE('"+d+"','DD/MM/YYYY')"+","+ee+",'"+f+"',"+g+")";
                                        con.s.executeUpdate(m);
                                        String query="select ProductQuantityOnHand from productdetails where ProductID="+a;
                                        ResultSet queryRes  = con.s.executeQuery(query);
                                        int qua=0;
                                        while(queryRes.next()){
                                            qua = queryRes.getInt("ProductQuantityOnHand");
                                        }
                                        int newquan=qua+ee;
                                        int newquan2=quantity-ee;
                                        String n="Update productdetails set ProductQuantityOnHand="+newquan+"where ProductID="+a;
                                        con.s.executeUpdate(n);
                                        String o="Update warehouse_productdetails set QuantityOnHand="+newquan2+"where ProductID="+a+"and QuantityOnHand="+quantity;
                                        con.s.executeUpdate(o);
                                        JOptionPane.showMessageDialog(null, "Requirement of product Done Successfully");
                                    }
                                    else if (ee>quantity){
                                        JOptionPane.showMessageDialog(null, "This much Quantity not available in warehouse");
                                    }
                                    else if(ee>reorderlevel){
                                        JOptionPane.showMessageDialog(null, "Can't Purchase that much Quantity");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Enter only digits as product quantity required");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Enter only digits as product requirement id");
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
