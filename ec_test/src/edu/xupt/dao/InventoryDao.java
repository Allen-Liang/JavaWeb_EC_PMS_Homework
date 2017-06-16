package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Inventory;
import edu.xupt.util.JdbcUtil;

public class InventoryDao {
	private JdbcUtil jdbc = new JdbcUtil();
		
	public int insert(String id,String product,String sum,String user){
		Connection conn = jdbc.getConn();
		String sql = "insert into inventory(id,product,sum,user) values ('"+id+"','"+product+"','"+sum+"','"+user+"')";
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
		String sql = "delete from inventory where id='"+id+"'";
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
	
	public int updateInventory(Inventory i){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update inventory set product=?,sum=?,user=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, i.getProduct());
			ps.setString(2, i.getSum());
			ps.setString(3, i.getUser());
			System.out.println(i.getId());
			int id = Integer.parseInt(i.getId());
			ps.setInt(4, id);//sql的第n个参数设为
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public List<Inventory> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from inventory";
		List<Inventory> inventorys = new ArrayList<Inventory>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Inventory inventory = new Inventory();
				inventory.setId(rs.getString(1));
				inventory.setProduct(rs.getString(2));
				inventory.setSum(rs.getString(3));
				inventory.setUser(rs.getString(4));
				inventorys.add(inventory);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inventorys;
	}
	
}
