<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/control/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<base href="<%=basePath%>">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>校友录</title>
	<meta name="description" content="Website Description" />
	<meta name="keywords" content="Website Kwywords" />
	<style type="text/css" media="all">@import "front/style/style.css";</style>
	<!--[if lt IE 7]>
	<style type="text/css">@import "front/style/ie.css";</style>
	<script src="front/script/DD_belatedPNG.js" type="text/javascript"></script>
	<script type="text/javascript">
		DD_belatedPNG.fix('#logo span, #loginarea, #panellogin, .textboxlogin, #shadowslideshow, img');
	</script>
	<![endif]-->
	<!--[if IE 7]><style type="text/css">@import "front/style/ie7.css";</style><![endif]-->
	<script src="front/script/jquery.js" type="text/javascript"></script>
	<script src="front/script/ui_core.js" type="text/javascript"></script>
	<script src="front/script/ui_tabs.js" type="text/javascript"></script>
	<script src="front/script/lightbox.js" type="text/javascript"></script>
	<script src="front/script/easing.js" type="text/javascript"></script>
	<script src="front/script/jcarousel.js" type="text/javascript"></script>
	<script src="front/script/slideshow.js" type="text/javascript"></script>
	<script src="front/script/twitter.js" type="text/javascript"></script>
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
		<jsp:include page="common/header.jsp"/>
		<div id="content">
			<div id="intro">
				<div id="placeslideshow">
					<div id="slideshow">
						<div id="shadowslideshow"></div>
						<ul>
							<s:iterator value="imageList" var="image">
								<li><img width="503px" height="190px" src="/<s:property value="#image.imagePath"/>" alt="<s:property value="#image.title"/>" /></li>
							</s:iterator>
						</ul>
					</div>
					<div id="captionslideshow">
						<ul>
							<s:iterator value="imageList" var="image">
								<li><s:property value="#image.title"/></li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div id="introright">
					<ul>
						<s:iterator value="imageList" var="image">
							<li><h1><s:property value="#image.title"/></h1>
								<p><s:property value="#image.content"/></p>
							</li>
						</s:iterator>

					</ul>
					<div id="placenav"><a href="#" class="replace" id="butprev"><span></span>Prev</a> <a href="#" class="replace" id="butnext"><span></span>Next</a></div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<jsp:include page="common/footer.jsp"/>
	</div>
</div>

</body>
</html>