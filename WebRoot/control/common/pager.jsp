<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

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

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="33%"><div align="left">
					<span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>
							${pm.total} </strong> 条记录，当前第<strong> <c:choose>
								<c:when test="${offset eq 0}">1</c:when>
								<c:otherwise>
									<fmt:formatNumber type="number"
										value="${((offset-offset%pageSize) /pageSize)+1}"></fmt:formatNumber>
								</c:otherwise>
							</c:choose> </strong> 页，共 <strong> <c:choose>
								<c:when test="${(pm.total%pageSize) eq 0}">
									<fmt:formatNumber type="number"
										value="${pm.total /pageSize}"></fmt:formatNumber>
								</c:when>
								<c:otherwise>
									<fmt:formatNumber type="number"
										value="${((pm.total-pm.total%pageSize) /pageSize+1)}"></fmt:formatNumber>
								</c:otherwise>
							</c:choose>
					</strong> 页</span>
				</div>
			</td>

			<td width="67%" align=right noWrap><c:forEach
					items="${param.params }" var="p" step="1">
					<pg:param name="${p }" />
				</c:forEach> <pg:first>
					<a id="first" href="${pageUrl }">首页</a>
				</pg:first> <pg:prev>
					<a href="${pageUrl }">上一条</a>
				</pg:prev> <pg:pages>
					<c:if test="${currentPageNumber eq pageNumber }">
						<font color="red">${pageNumber}</font>
					</c:if>
					<c:if test="${currentPageNumber ne pageNumber }">
						<a href="${pageUrl }">${pageNumber}</a>
					</c:if>

				</pg:pages> <pg:next>
					<a href="${pageUrl }">下一条</a>
				</pg:next> <pg:last>
					<a href="${pageUrl }">尾页</a>
				</pg:last> <select name="pagesize" onchange="selectPagesize(this)">
					<c:forEach begin="5" end="50" step="5" var="i">
						<option value="${i }"
							<c:if test="${i eq pageSize }">selected</c:if>>${i }</option>
					</c:forEach>
			</select></td>
		</tr>
	</table>
</pg:pager>
</html>
