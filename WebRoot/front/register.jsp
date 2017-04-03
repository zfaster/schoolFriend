<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/control/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>校友录-注册</title>
	<meta name="description" content="Website Description" />
	<meta name="keywords" content="Website Kwywords" />
	<style type="text/css" media="all">@import "style/style.css";</style>
	<!--[if lt IE 7]>
	<style type="text/css">@import "style/ie.css";</style>
	<script src="script/DD_belatedPNG.js" type="text/javascript"></script>
	<script type="text/javascript">
		DD_belatedPNG.fix('#logo span, #loginarea, #panellogin, .textboxlogin, img');
	</script>
	<![endif]-->
	<!--[if IE 7]><style type="text/css">@import "style/ie7.css";</style><![endif]-->
	<script src="script/jquery.js" type="text/javascript"></script>
	<script src="script/ui_core.js" type="text/javascript"></script>
	<script src="script/ui_tabs.js" type="text/javascript"></script>
	<script src="script/lightbox.js" type="text/javascript"></script>
	<script src="script/twitter.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("#butslide").click(function(){
				$("#panellogin").slideToggle("fast");
				$(this).toggleClass("active"); return false;
			});
			$('#tabsnav').tabs({ fx: { opacity: 'toggle' } });
			$('a.popup').lightBox({fixedNavigation:true});
		});
	</script>
</head>
<body>
<p><a class="skiplink" href="#maincontent">Skip over navigation</a></p>
<script>
	function register() {
		if($('#repassword').val() && $('#newpassword').val()!=$('#repassword').val()){
			alert('输入密码不一致');
			return;
		}
		if(!$('#userName').val()){
			alert('用户名不可为空');
			return;
		}
		if(!$('#newpassword').val()){
			alert('密码不可为空');
			return;
		}
		if(!$('#realname').val()){
			alert('姓名不可为空');
			return;
		}
		if(!$('#phone').val()){
			alert('电话不可为空');
			return;
		}
		if(!$('#address').val()){
			alert('地址不可为空');
			return;
		}
		if(!$('#roomId').val()){
			alert('班级不可为空');
			return;
		}
		$.post('${ctx}/front/student!checkUserName',
				{
					'username':$('#userName').val()

				},function (data) {
					if(data == 'true'){
						$('#frmcontact').submit();
					}else{
						alert('用户名已被使用');
					}
				})
	}
</script>
<div id="container">
	<div id="wrapper">
		<jsp:include page="common/header.jsp"/>
		<div id="content">
			<div id="maincontent">
				<div class="boxbig">
					<h1 class="titlebig">用户注册</h1>
					<div class="boxbigcontent">
						<form method="post" action="${ctx}/front/student!register" enctype="multipart/form-data"  id="frmcontact">
							<div>
								<label>用户名:</label> <input type="text" id="userName" name="student.username" class="textboxcontact"/> <br />
								<label >密码:</label> <input type="password" id="newpassword" name="student.password" class="textboxcontact" /> <br />
								<label>确认密码:</label> <input type="password" id="repassword" name="repassword" class="textboxcontact" /> <br />
								<label>姓名:</label> <input type="text" id="realname" name="student.realname" class="textboxcontact"/> <br />
								<label >电话:</label> <input type="text" id="phone" name="student.phone" class="textboxcontact" /><br />
								<label>地址:</label> <input type="text" id="address" name="student.address" class="textboxcontact"  /><br />
								<label>头像:</label> <input type="file"  name="image" class="textboxcontact"  /><br />
								<label>班级:</label>
								<s:select cssClass="textboxcontact" list="roomList" id="roomId" name="student.room.id" listKey="id" listValue="name"/>
								<br />

								<%--<label for="txtmessage">Message:</label> <textarea cols="50" rows="10" name="message" id="txtmessage" class="textareacontact"></textarea><br />
								--%>
								<label></label><input type="button" onclick="register()" name="submitcontact" value="注册" class="submitcontact" />
							</div>
						</form>
					</div>
					<div class="boxbigcontentbottom"></div>
				</div>
			</div>
			<div id="nav">
				<div class="boxnav">
					<h3 class="titlenav">班级列表</h3>
					<div class="boxnavcontent">
						<ul class="menunav">
							<s:iterator var="obj" value="roomList" status="stat">

								<li <s:if test="#stat.last"> class="last"</s:if>><a href="/front/student!searchStudent?roomId=<s:property value="#obj.id"/>&studentName=<s:property value="studentName"/>"><s:property value="#obj.name"/></a></li>

							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="common/footer.jsp"/>
	</div>
</div>

</body>
</html>