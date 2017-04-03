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
<div id="container">
	<div id="wrapper">
		<jsp:include page="common/header.jsp">
            <jsp:param name="target" value="comment"/>
        </jsp:include>
		<div id="content">
			<div id="maincontent">
				<div class="boxbig">
					<h1 class="titlebig">校友留言</h1>
					<div class="boxbigcontent">
						<ul id="listcomment">
							<s:iterator value="pm.datas" var="obj" status="stat">
								<li>
									<s:if test="#obj.student.getImagePath() == null">
										<img src="images/nopicture2.jpg" width="50px" height="50px" alt="<s:property value="#obj.student.name"/>" class="imgavatar" />
									</s:if>
									<s:else>
										<img width="50px" height="50px" src="/<s:property value="#obj.student.getImagePath()"/>" alt="<s:property value="#obj.student.name"/>" class="imgavatar" />
									</s:else>
									<div class="placecomment">
										<h3><strong><s:property value="#obj.student.realname"/></strong>, <s:date name="#obj.createTime" format="yyyy-MM-dd HH:mm:ss"/></h3>
										<p>
											<s:property value="#obj.content"/>
										</p>
									</div>
									<div class="clear"></div>
								</li>
							</s:iterator>

						</ul>
						<jsp:include page="/front/common/pager.jsp">
							<jsp:param name="url" value="${ctx}/front/student!commentList"/>
						</jsp:include>
						<h2 class="subtitle">发表留言</h2>
						<form method="post" action="${ctx}/front/student!comment" id="frmcomment">
							<div>
								<label for="lblcomment">内容 </label>
								<textarea name="message.content" cols="80" rows="10" id="lblcomment" class="textareacomment"></textarea><br />
								<input type="submit" name="submitcomment" class="submitcomment" value="发表留言" />
							</div>
						</form>
					</div>
					<div class="boxbigcontentbottom"></div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="common/footer.jsp"/>
	</div>
</div>

</body>
</html>