<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <div align="center">请输入您的编号信息</div>
    	<div align="center">
    		<form action="user/login.action">
    			姓名：<input type="text" name="userName"/>
    			手机号码：<input type="text" name="phone"/>
    			邮箱:<input type="text" name="email"/>
    			邀请人:<input type="text" name="inviter"/>
    			<input type="submit" name="s" value="提交">
    		</form>
    	</div>
  </body>
</html>
