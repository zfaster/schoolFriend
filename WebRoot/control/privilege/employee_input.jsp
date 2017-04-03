<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/control/common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/shop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="control/css/common.css" type="text/css"></link>

<script type="text/javascript" src="control/js/public.js"></script>
<script type="text/javascript" src="control/js/jquery-plus-jquery-ui.js"></script> 
<link type="text/css" rel="stylesheet" charset="utf-8" href="control/css/ui-sui.css" /> 
<link rel="stylesheet" href="control/css/common.css" type="text/css"></link>
<script language="javascript" src="<%=request.getContextPath()%>/js/vilidata.js"></script>
<script type="text/javascript">
	var safe = false;
	function checkForm(){
		if(!safe){
			alert("请输入合法用户名");
			return false;
		}
		var check = checkPassword();
		
		if(check)
			check = validataTableForm();
		return check;
	}
	function checkPassword(){
		var password = document.getElementById("password");
		var repassword = document.getElementById("repassword");
		
		if(password.value != repassword.value){
			alert("两次输入的密码不正确");
			return false;
		}
		if(password.value.length<4 ){
			alert("请输入长度4位以上的密码");
			return false;
		}
		return true;
	}
	function checkUsername(){
		
		var username = document.getElementById("username");
		if(username.value=="" || username.value.length<2){
			alert("请输入长度2位以上的用户名");
			safe = false;
			return;
		}
		var userid = 0;
		if(document.getElementById("object.id").value!=''){
			userid =  document.getElementById("object.id").value;
		}
		$.post("control/privilege/employee!checkUsername",
  				{
					username:username.value,
					employeeId:userid
  				}, function(returnedData, status)
  				{
  					if("success" == status)
  					{
  						var result = document.getElementById("checkResult");
  						if(returnedData == 'true'){
  							safe = false;
  							result.innerHTML = "<font color='red' size=2 >用户名已存在</font>";
  						}else{
  							safe = true;
  							result.innerHTML = "<font color='green' size=2 />用户名可以使用</font>";
  						}
  						
  					}
  				}
  			);
	}
	
	
	function init(){
		document.getElementById("username").focus();
	}
	</script>
<title>员工信息添加/修改</title>
</head>
<body onload="init()">
	<center>
		<form
			action='${actionPath}!update'
			method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width:580px;">
				<TBODY>
					<TR>
						<!-- 这里是添加、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">员工信息</TD>
					</TR>
					<TR>
						<td>
								<input type="hidden"  id="object.id" name="id" value="${object.id}">
							<input type="hidden"  id="object.type" name="object.type" value="CLASS_ADMIN">
							<!-- 主输入域开始 -->

							<table class="tableEdit" style="width:600px;" cellspacing="0"
								border="0" cellpadding="0" id="content">
								<tr>
									<td class="tdEditLabel">用户名</td>
									<td class="tdEditContent">
									
										<input type="text" id="username" name="object.username" notNull="true" value="${object.username }" onblur="checkUsername()"/>
										<span id="checkResult"></span>
									</td>
									<td class="tdEditLabel">性别</td>
									<td class="tdEditContent">
									<s:select name="object.sex" list="#{'0':'女','1':'男'}" value="object.sex" ></s:select>
									</td>
								</tr>
							<c:if test="${object.id == null }">
								<tr>
									<td class="tdEditLabel">密码</td>
									<td class="tdEditContent">
										<input type="password" id="password" name="object.password"/>
									</td>
									<td class="tdEditLabel">确认密码</td>
									<td class="tdEditContent">
										<input type="password" id="repassword"  onblur="checkPassword()"/>
									</td>
								</tr>
							</c:if>
								<s:set name="targetObject" value="object" />
								<s:property
									value='%{@com.sys.freemarker.DynaFormFunction@form(#targetObject)}'
									escapeHtml="" />
								
							</table> <!-- 主输入域结束 --></td>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE>
				<TR align="center">
					<TD colspan="3" bgcolor="#EFF3F7"><input type="submit"
						name="saveButton" class="buttom" value="保存信息"> <input
						type="button" class="buttom" value="关闭窗口"
						onclick="window.close()">
						<s:fielderror></s:fielderror>
					</TD>
				</TR>
			</TABLE>
		</form>
	</center>
</body>
</html>