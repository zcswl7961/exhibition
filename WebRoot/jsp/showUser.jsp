<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>测试</title>  
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.9.0.js"></script>
  </head>  
    
  <body>  
		<div id="selectID" style="display:block">
    		用户名：<input type="text" id="userName"/>
    		<br>
    		号码：<input type="text" id="password"/>
    			<br>
    		邮箱：<input type="text" id="email"/>
    			<br>
    		邀请人:<input type="text" id="inviter"/>
    		<a href="javascript:void(0);" onclick="submit();">提交</a>
   		</div>
   		<div align="center" style="display:none" id="successMessage">
   			<h3>恭喜您,你以通过审核信息,请您保管好你的二维码信息，以便扫码进入会场</h3>
   			<img src="" id="qrcodeUrl"/>
   		</div>
   		<div align="center" style="display:none" id="noticeMessage">
   			<h3>对不起，您的信息有误，不在受邀名单中</h3>
   		</div>
  </body>  
</html>
<script type="text/javascript">
  		
  		function submit(){
  			var userName=$('#userName').val();
  			var password=$('#password').val();
  			var email=$('#email').val();
  			var inviter=$('#inviter').val();
  			var information={
  				exhName:userName,
  				exhPhone:password,
  				exhEmail:email,
  				exhInviter:inviter
  			}
  			$.ajax({
  				type:'post',
  				url: 'http://10.250.196.76:8080/Test/user/login.action',
  				data:JSON.stringify(information),
				contentType:'application/json;charset=utf-8',
				dataType: "json",
				success: function(data){
					if(data.flag==0){
						//表示该用户不在受邀名单中
						$("#selectID").attr("style","display:none;");//显示div
						$("#noticeMessage").attr("style","display:block;");//显示div
					}
					else if (data.flag==1){
						$("#selectID").attr("style","display:none;");//显示div
						$("#successMessage").attr("style","display:block;");//显示div
						$('#qrcodeUrl').attr('src',data.qrcodeUrl);
					}
				}
  			});
  		}
  		
</script>