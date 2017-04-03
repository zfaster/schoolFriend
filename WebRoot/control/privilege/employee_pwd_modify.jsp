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
<script type="text/javascript">
  	var safe = false;
	function checkForm(){
		checkPassword();
		if(document.getElementById("oldPassword").value==""){
			return false;
		};
		return safe;
	}
	function checkPassword(){
		var password = document.getElementById("password");
		var repassword = document.getElementById("repassword");
		
		if(password.value != repassword.value){
			alert("两次输入的密码不正确");
			safe = false;
			return;
		}
		if(password.value.length<4 ){
			alert("请输入长度4位以上的密码");
			safe = false;
			return;
		}
		safe = true;
	}
	</script>
<title>员工信息</title>
</head>
<body onload="initEditor()">
	<center>
		<form
			action='control/privilege/employee!modifyPassword'
			method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width:580px;">
				<TBODY>
					<TR>
						<!-- 这里是添加、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">员工信息</TD>
					</TR>
					<TR>
						<td>
								<input type="hidden" name="employeeId" value="${employee.id}">
							<!-- 主输入域开始 -->

							<table class="tableEdit" style="width:600px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">原始密码</td>
									<td class="tdEditContent">
										<input type="password" id="oldPassword" name="oldPassword"/>
									</td>
								</tr>
								<tr>
									<td class="tdEditLabel">新密码</td>
									<td class="tdEditContent">
										<input type="password" id="password" name="object.password"/>
									</td>
									<td class="tdEditLabel">确认密码</td>
									<td class="tdEditContent">
										<input type="password" id="repassword"  onblur="checkPassword()"/>
									</td>
								</tr>
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