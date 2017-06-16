package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Product;
import edu.xupt.util.JdbcUtil;

public class ProductDao {
	private JdbcUtil jdbc = new JdbcUtil();
		
	public int insert(String name,String type,String discription){
		Connection conn = jdbc.getConn();
		String sql = "insert into product(name,type,discription) values ('"+name+"','"+type+"','"+discription+"')";
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
		String sql = "delete from product where id='"+id+"'";
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
	
	public int updateProduct(Product p){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update product set name=?,type=?,discription=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getType());
			ps.setString(3, p.getDiscription());
			System.out.println(p.getId());
			int id = Integer.parseInt(p.getId());
			ps.setInt(4, id);//sql的第n个参数设为
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public List<Product> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from product";
		List<Product> products = new ArrayList<Product>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getString(1));
				product.setName(rs.getString(2));
				product.setType(rs.getString(3));
				product.setDiscription(rs.getString(4));
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	
}
