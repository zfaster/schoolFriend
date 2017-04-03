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
	<title>校友录</title>
	<meta name="description" content="Website Description" />
	<meta name="keywords" content="Website Kwywords" />
	<style type="text/css" media="all">@import "style/style.css";</style>
	<!--[if lt IE 7]>
	<style type="text/css">@import "style/ie.css";</style>
	<script src="script/DD_belatedPNG.js" type="text/javascript"></script>
	<script type="text/javascript">
		DD_belatedPNG.fix('#logo span, #loginarea, #panellogin, .textboxlogin, #shadowslideshow, img');
	</script>
	<![endif]-->
	<!--[if IE 7]><style type="text/css">@import "style/ie7.css";</style><![endif]-->
	<script src="script/jquery.js" type="text/javascript"></script>
	<script src="script/ui_core.js" type="text/javascript"></script>
	<script src="script/ui_tabs.js" type="text/javascript"></script>
	<script src="script/lightbox.js" type="text/javascript"></script>
	<script src="script/easing.js" type="text/javascript"></script>
	<script src="script/jcarousel.js" type="text/javascript"></script>
	<script src="script/slideshow.js" type="text/javascript"></script>
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
<div id="container">
	<div id="wrapper">
		<jsp:include page="common/header.jsp">
			<jsp:param name="target" value="contact"/>
		</jsp:include>
		<div id="content">

			<div id="maincontent">
				<div class="boxbig">
					<h1 class="titlebig">校友录</h1>
					<div class="boxbigcontent">
						<ul id="listgallery">
							<s:iterator value="pm.datas" var="obj" status="stat">
								<li><a>
									<s:if test="#obj.getImagePath() == null">
										<img src="images/nopicture2.jpg" alt="<s:property value="#obj.realname"/>" />
									</s:if>
									<s:else>
										<img src="/<s:property value="#obj.getImagePath()" />" width="100" height="100" alt="<s:property value="#obj.realname"/>" />
									</s:else>
									<br />
									姓名:<s:property value="#obj.realname"/>
                                    <br />
                                    电话：<s:property value="#obj.phone"/>
                                </a>

								</li>
							</s:iterator>
						</ul>

						<div class="clear"></div>
						<jsp:include page="/front/common/pager.jsp">
							<jsp:param name="url" value="${ctx}/front/student!searchStudent"/>
							<jsp:param name="params" value="studentName"/>
						</jsp:include>
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