package edu.xupt.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import edu.xupt.dao.UserDao;
import edu.xupt.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	    @RequestMapping("/add.do")
		public void userAdd(String name,String password,String firstname,String lastname,String email,String phone,HttpServletResponse response){
			UserDao user = new UserDao();
			name = firstname+lastname;
			int r =user.insert(name,password,firstname,lastname,email,phone);
			System.out.println(name);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			try {
				writer = response.getWriter();
				if(r>0){
					writer.append("OK");
				}else{
					writer.append("NO");
				}
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	    
	    
	    @RequestMapping("/getAll.do")
		public void getUserAll(HttpServletResponse response){
			UserDao user = new UserDao();
			List<User> users = user.selectAll2();
			System.out.println("get user info!");
			response.setCharacterEncoding("utf-8");
			Writer writer;
			try {
				writer = response.getWriter();
				String jsonString = JSON.toJSONString(users);
				System.out.println(jsonString);
				writer.append(JSON.toJSONString(users));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	    
	    @RequestMapping("/updateUser.do")
		public void updateUser(String id, String name,String password,String firstname,
				String lastname,String email,String phone,HttpServletResponse response){
			System.out.println("*****************update user!************");
			System.out.println("id="+id+" name="+name+" password="+password+" firstname"+firstname+" lastname="+lastname);
			UserDao userDao = new UserDao();
			User u = new User();
			name = firstname+lastname;
			u.setEmail(email);
			u.setFirstname(firstname);
			u.setId(id);
			u.setLastname(lastname);
			u.setName(name);
			u.setPassword(password);
			u.setPhone(phone);
			int r = userDao.updateUser(u);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			try {
				writer = response.getWriter();
				if(r>0){
					writer.append("OK");
				}else{
					writer.append("NO");
				}
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		
		@RequestMapping("/delete2.do")
		public void userDelete2(String id,HttpServletResponse response){
			UserDao user = new UserDao();
			int r = user.deleteById(id);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				writer = response.getWriter();
				if(r>0){
					System.out.println("delete success!");
					map.put("success", true);
				}else{
					map.put("success", false);
					System.out.println("delete fail!");
				}
				writer.append(JSON.toJSONString(map));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
}
