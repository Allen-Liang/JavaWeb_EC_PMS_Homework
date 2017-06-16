package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.User;
import edu.xupt.util.JdbcUtil;

public class UserDao {
	private JdbcUtil jdbc = new JdbcUtil();
		
	public int insert(String name,String password,String firstname,String lastname,String email,String phone){
		Connection conn = jdbc.getConn();
		String sql = "insert into user(name,password,firstname,lastname,email,phone) values ('"+name+"','"+password+"','"+firstname+"','"+lastname+"','"+email+"','"+phone+"')";
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
		String sql = "delete from user where id='"+id+"'";
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
	
	public int updateUser(User u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update user set name=?,firstname=?,lastname=?,password=?,phone=?,email=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getFirstname());
			ps.setString(3, u.getLastname());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getEmail());
			System.out.println(u.getId());
			int id = Integer.parseInt(u.getId());
			ps.setInt(7, id);
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public List<User> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from user";
		List<User> users = new ArrayList<User>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString(2));
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(3));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
				user.setEmail(rs.getString(7));
				user.setPhone(rs.getString(6));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
}
