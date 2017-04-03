<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="/control/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'pager.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<script type="text/javascript">
<!--
	function selectPagesize(option) {
		var pageSize = option.value;
		window.location = document.getElementById("first").href + "&pageSize="
				+ pageSize;
		//alert(document.getElementById("first").href+"&pageSize="+pageSize);
	}
//-->
</script>



<pg:pager url="${param.url }" items="${pm.total }"
	maxPageItems="${pageSize }" export="currentPageNumber = pageNumber">

	<ul id="listpages">
		<c:forEach
				items="${param.params }" var="p" step="1">
			<pg:param name="${p }" />
		</c:forEach>
		<pg:prev>
			<li><a href="${pageUrl }">上一页</a></li>
		</pg:prev>
		<pg:pages>
			<c:if test="${currentPageNumber eq pageNumber }">
				<li><a class="active" href="${pageUrl }">${pageNumber}</a></li>
			</c:if>
			<c:if test="${currentPageNumber ne pageNumber }">
				<li><a href="${pageUrl }">${pageNumber}</a></li>
			</c:if>
		</pg:pages>
		<pg:next>
			<li><a href="${pageUrl }">下一页&#187;</a></li>
		</pg:next>
	</ul>
</pg:pager>
</html>
