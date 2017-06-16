package edu.xupt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.xupt.util.JdbcUtil;

public class JiajianDao {
	
	
	private JdbcUtil jdbc = new JdbcUtil();
	
	
	
	public String Select(String product) {
		Connection conn = jdbc.getConn();
		String sql = "select sum from inventory where product = '" +product+ "' ";
		Statement st;
		String sum = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				sum = rs.getString(1);
				//System.out.print(password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	
	
	
	public void Jian(String amount,String product) {
		JiajianDao jiajian =new JiajianDao();
		String sumsum=jiajian.Select(product);
		float sum_1 = Float.parseFloat(sumsum) - Float.parseFloat(amount);
		String sum_2 = String.valueOf(sum_1);
		Connection conn = jdbc.getConn();
		String sql = "update inventory set sum = '"+sum_2+"' where product = '"+product+"' ";
		Statement st;
		try {
			st = conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		//JiajianDao jiajian =new JiajianDao();
		//jiajian.Jian("4", "a");
		
	}
	
	
	
}
