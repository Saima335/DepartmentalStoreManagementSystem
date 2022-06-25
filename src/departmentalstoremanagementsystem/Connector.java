/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package departmentalstoremanagementsystem;

import java.sql.*;

/**
 *
 * @author me
 */
public class Connector {
    Connection c;
	Statement s;
	public Connector() {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//c=DriverManager.getConnection("jdbc:mysql:///university","saima","kausar");
                        c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","saima", "kausar");
			s=c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
