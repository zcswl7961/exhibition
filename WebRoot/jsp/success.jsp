<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>测试数据</title>  
  </head>  
    
  <body>  
  	<div align="center">恭喜您，您已成功再受邀请的名单中</div>
  	<div align="center"><img src="<%=path%>${path}"/></div>
  </body>  
</html>