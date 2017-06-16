package com.xupt.key;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	public String MySid;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String piccode = (String) request.getSession().getAttribute("piccode");
		String checkcode = request.getParameter("checkcode");
		checkcode = checkcode.toUpperCase();
		//将用户输入的字母自动转化为大写字母
		response.setContentType("text/html;charset=utf-8");
		//密码校验
		String name =request.getParameter("Name");
        String password =request.getParameter("Password");
        
        
        DoSql logtest = new DoSql();
        String pw = logtest.login(name);
        
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = null;
        //验证码与密码校验
		if((name != null)&&(password != null)&&checkcode.equals(piccode)){
			if(password.equals(pw)){
                 response.sendRedirect("/ec_test/index.html");
			}else{
			      out.println("用户名或者密码错误，请重新输入");
			      }	
			}else{
			out.println("sorry,验证码错误！");
		}
		out.flush();
		//输出流刷新
		out.close();
		
	}
	

}
