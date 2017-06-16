package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.xupt.entity.Order;
import edu.xupt.util.JdbcUtil;

public class OrderDao {
	private JdbcUtil jdbc = new JdbcUtil();
		
	public int insert(String product,String amount,String price,String user){
		Connection conn = jdbc.getConn();
		
		float total_1 = Float.parseFloat(amount) * Float.parseFloat(price);
		String total = String.valueOf(total_1);
		
		Date date = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_1 =sdf.format(date);
		//获取系统时间
		
		String sql = "insert into orders(product,amount,price,total,user,time) values ('"+product+"','"+amount+"','"+price+"','"+total+"','"+user+"','"+date_1+"')";
		System.out.print(sql);
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	

	public int deleteById(String id){
		Connection conn = jdbc.getConn();
		String sql = "delete from orders where id='"+id+"'";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateOder(Order o){
		Connection conn = jdbc.getConn();
		int result = 0;
		
       
		
		
		
		String sql = "update orders set product=?,amount=?,price=?,total=?,user=?,time=? where id=?";
		try {
			
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Date date = new Date();
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_1 =sdf.format(date);
			//获取系统时间			
			
			
			ps.setString(1, o.getProduct());
			ps.setString(2, o.getAmount());
			ps.setString(3, o.getPrice());
			ps.setString(4, o.getTotal());
			ps.setString(5, o.getUser());
			ps.setString(6, date_1);
			System.out.println(o.getId());
			int id = Integer.parseInt(o.getId());
			ps.setInt(7, id);
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public List<Order> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from orders";
		List<Order> orders = new ArrayList<Order>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getString(1));
				order.setProduct(rs.getString(2));
				order.setAmount(rs.getString(3));
				order.setPrice(rs.getString(4));
				order.setTotal(rs.getString(5));
				order.setUser(rs.getString(6));
				order.setTime(rs.getString(7));
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
}
