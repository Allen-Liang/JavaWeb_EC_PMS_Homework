package com.xupt.key;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.xupt.util.JdbcUtil;


public class DoSql {
	private JdbcUtil jdbc = new JdbcUtil();

//登录密码校验操作	 
	 public String login(String name){
		 Connection conn = jdbc.getConn();
    	 String sql = "select password from administrator where name='"+name+"'";
    	 Statement st;
 		 String password = null;
 		 try {
 			 st = conn.createStatement();
 			 ResultSet rs = st.executeQuery(sql);
 			 while (rs.next()) {
 				 password = rs.getString(1);
 				//System.out.print(password);
 			 }
 		 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		return password;
 	}
	  
	 
public static void main(String[] args) {
		//DoSql test = new DoSql();
		//System.out.print(test.login("aa"));


	 }
	
	
	
}
