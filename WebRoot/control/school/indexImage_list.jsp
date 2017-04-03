<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/control/common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>管理工作平台</title>
<link rel="stylesheet" href="control/css/common.css" type="text/css"></link>
<script type="text/javascript" src="control/js/public.js"></script>
<script type="text/javascript" src="control/js/jquery-1.4.2.min.js"></script>
</head>
<body onload="initTable()">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="94%" ><span class="STYLE1"> 首页图片</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
             &nbsp;&nbsp;<img src="control/images/add.gif" width="10" height="10" /><a href="javascript:openWin('${actionPath}!updateInput','添加',600,200,1)">添加</a>
             &nbsp;   &nbsp;
             <img src="control/images/del.gif" width="10" height="10" /> <a href="javascript:delAll('${actionPath}!delete','ids')">删除</a>    &nbsp;&nbsp;   &nbsp;
             </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  	<td>

  	</td>
  </tr>
  <tr>
    <td>
    <table width="100%" id="contentTable" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input type="checkbox" name="checkbox" onclick="selectAll(this)"/>
        </div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">序号</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">图片</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">标题</span></div></td>
          <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">内容</span></div></td>
          <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <s:if test="pm.datas != null">
      <s:iterator value="pm.datas" var="obj" status="stat">
      <tr>
        <td height="5%" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="id" value="<s:property value="#obj.id"/>"/>
        </div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#stat.index+1"/></div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
          <s:if test="#obj.getImagePath() == null">
            <img src="control/images/nopicture2.jpg" width="60" height="50"/>
          </s:if>
          <s:else>
            <img src="<s:property value="#obj.getImagePath()" />" width="60" height="50"/>
          </s:else>
        </div></td>
        <td height="15%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#obj.title"/></div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#obj.content"/></div></td>

          <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
                <a href="javascript:del('${actionPath}!delete?ids=<s:property value="#obj.id"/>')" title="">删除</a> |
                <a href="javascript:openWin('${actionPath}!updateInput?id=<s:property value="#obj.id"/>','更新',600,200,1)" title="">编辑</a>
        </div>
        </td>
      </tr>
      </s:iterator>
      </s:if>
      <s:if test="pm.datas.size()==0">
      <tr>
          <td height="20" colspan="15" bgcolor="#FFFFFF" class="STYLE19"><div align="center">没有记录可以显示</div></td>
      </tr>
      </s:if>
    </table></td>
  </tr>

  <tr>
    <td height="30">
		<jsp:include page="/control/common/pager.jsp">
			<jsp:param name="url" value="control/school/indexImage"/>
		</jsp:include>
    </td>
 </tr>
</table>
</body>
</html>

