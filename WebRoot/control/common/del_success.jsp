<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="control/css/common.css" type="text/css"></link>
  </head>
  <script type="text/javascript">
  
  	function refresh(){
  		window.returnValue = true;
  		
  	}
  	
  	function clock(){
  		i = i-1;
  		if(document.getElementById("info")){
  			document.getElementById("info").innerHTML = i;
  		}
  		if(i>0)
  			setTimeout("clock();",1000);
  		else
  			window.close();
  	}
  	var i=4;
  	clock();
  </script>
  <body onload="refresh()">
 <center> 删除记录成功！<br/>
本窗口将在<span id="info">3</span>秒后自动关闭  <br/>
    <input type="button" class="buttom" value="关闭窗口" onclick="window.close();">
     </center>  
  </body>
</html>
