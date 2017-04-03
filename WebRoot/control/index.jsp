<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/control/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script src="control/Style/menu.js" type="text/javascript"></script>
<link rel="stylesheet" href="control/Style/default.css" type="text/css" />
<title>后台管理系统</title>
</head>
<body>
<div id="header">
	<div style="float: left;margin: 10px 0px 0px 10px">
		<span style="font-size: 20px;font-weight: bold">校友录后台管理系统</span>
	</div>
	<div style="float: right;">
		<div style="float: left;margin: 10px 10px 0px 0px">
			<span style="font-size: 16px;font-weight: bold;margin-right: 10px">欢迎您，<s:property value="#session.employee.username"/></span>
			<span style="font-size: 16px;font-weight: bold">
				<a href="control/privilege/employee!quit">退出</a>
			</span>
		</div>
	</div>
</div>

<div id="contents">
	<div class="left">
		
		<div class="menu_top"></div>
		<div class="menu" id="TabPage3">
			<ul id="TabPage2">
				<s:if test="#session.employee.type.name()=='SUPER_ADMIN'">
					<li id="left_tab1" class="Selected" onClick="javascript:border_left('TabPage2','left_tab1');" title="管理员">管理</li>
					<li id="left_tab2" onClick="javascript:border_left('TabPage2','left_tab2');" title="日志">班级</li>
				</s:if>
				<s:else>
					<li id="left_tab2" class="Selected" onClick="javascript:border_left('TabPage2','left_tab2');" title="日志">班级</li>
				</s:else>
			</ul>
			<div id="left_menu_cnt">
				<s:if test="#session.employee.type.name()=='SUPER_ADMIN'">
						<ul id="dleft_tab1">
							<li id="now11"><a href="control/privilege/employee"  onClick="go_cmdurl('员工管理',this)" target="content1" title="员工管理">员工管理</a></li>
							<li id="now12"><a href="control/school/indexImage"  onClick="go_cmdurl('首页图片',this)" target="content1" title="首页图片">首页图片</a></li>
						</ul>
				</s:if>
				<ul id="dleft_tab2" <s:if test="#session.employee.type.name()=='SUPER_ADMIN'">style="display:none;"</s:if>>
					<li id="now23"><a href="control/school/classRoom" onClick="go_cmdurl('班级管理',this)" target="content1" title="班级管理">班级管理</a></li>
					<s:if test="#session.employee.type.name()!='SUPER_ADMIN'">
						<li id="now22"><a href="control/school/student" onClick="go_cmdurl('校友管理',this)" target="content1" title="校友管理">校友管理</a></li>
						<li id="now27"><a href="control/school/message" onClick="go_cmdurl('留言管理',this)" target="content1" title="留言管理">留言管理</a></li>
					</s:if>
				</ul>

			</div>
			<div class="clear"></div>
		</div>
		<div class="menu_end"></div>
	</div>
	<div class="right">
		<div id="cnt" style="height: 99%">
		  <div id="dTab1" class="Box">
		  <iframe id="content1"  name="content1" frameborder="0" scrolling="auto" style="height: 100%"></iframe>
		  </div>
		</div>
	</div>
	<div class="clear"></div>
</div>

</body>
</html>
