<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/control/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="control/css/common.css" type="text/css"></link>

<script language="javascript" src="control/js/public.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/vilidata.js"></script>
<script type="text/javascript">
</script>
<title>信息添加/修改</title>
</head>
<body >
	<center>
		<form
			action='${actionPath}!update'
			method="post" enctype="multipart/form-data" onsubmit="return validataTableForm();">
			<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width:580px;">
				<TBODY>
					<TR>
						<td align="center" class="tdEditTitle">信息列表</TD>
					</TR>
					<TR>
						<td><c:if test="${object.id ne 0}">
								<input type="hidden" name="id" value="${object.id}">
							</c:if>
							<input type="hidden" name="productid" value="${productid}">
							<input type="hidden" name="styleId" value="${styleId}">
							<input type="hidden" name="orderId" value="${orderId}">
							 <input type="hidden" name="parentId" value="${parentId}">
							<table class="tableEdit" style="width:580px;" cellspacing="0"
								border="0" cellpadding="0" id="content">


								<s:set name="targetObject" value="object" />
								<s:property
									value='%{@com.sys.freemarker.DynaFormFunction@form(#targetObject)}' escapeHtml=""/>
							</table> <s:fielderror></s:fielderror></td>

					</TR>
				</TBODY>
			</TABLE>

			<TABLE>
				<TR align="center">
					<TD colspan="3" bgcolor="#EFF3F7"><input type="submit"
						name="saveButton" class="buttom" value="保存信息"> <input
						type="button" class="buttom" value="关闭窗口"
						onclick="window.close()"></TD>
				</TR>
			</TABLE>
		</form>
	</center>
</body>
</html>