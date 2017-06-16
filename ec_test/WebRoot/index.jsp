<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EC_PMS</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function reloadCode(){
			var time = new Date().getTime();
			//获取一个时间戳，防止浏览器阻止缓存刷新，并将时间戳加到url上
			document.getElementById("imagecode").src="<%=request.getContextPath() %>/servlet/ImageServlet?d="+time;
		}
	</script>
	
	
  </head>
  
  <body  bgcolor="#FAF0E6" > 
	    <h1 align="center">西安邮电大学电子商务1402商品管理系统</h1>
	    <br>
    
    <div style="width:100%;text-align:center">
    
  <form action="<%=request.getContextPath() %>/servlet/LoginServlet" method="get">
      <p>用户名: <input type="text" id="Name"  name="Name" style="width:200px;" /></p>
      <p>密         码: <input type="password" id="Password"  name="Password" style="width:200px;" /></p>
      <p>验证码：<input type="text" name="checkcode" style="width:200px;"/></p>
      <img alt="验证码" id="imagecode" src="<%=request.getContextPath() %>/servlet/ImageServlet"/>
       <a href="javascript: reloadCode();">看不清楚</a>
      <br>
      <br>
      <input type="submit" value="确认登录">  
      </form>   
      
     </div>
  <br>
      
  
  </body>
</html>