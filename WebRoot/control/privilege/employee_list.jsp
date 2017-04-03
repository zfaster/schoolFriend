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
<script type="text/javascript">

function changState(button){
	var idCheckboxs = document.getElementsByName("id");
	var url = "control/privilege/employee!changeState?ids=-1";
	var checkedIds = [];
	for(var i=0; i<idCheckboxs.length; i++){
		if(idCheckboxs[i].checked){
			checkedIds[checkedIds.length] = idCheckboxs[i].value;
		}
	}

	for(var i=0; i<checkedIds.length; i++){
		url = url + "&ids="+checkedIds[i];
	}
	button.disabled = true;
	if(checkedIds.length > 0){
		$.post(url,
  				{
					state: button.name
  				}, function(returnedData, status)
  				{
  					if("success" == status)
  					{
  						if(returnedData=="success"){
  							alert("状态修改");
  	  						button.disabled = false;
  	  						window.location.reload();
  						}else{
  							alert("修改失败");
  							button.disabled = false;
  						}
  						
  					}
  				}
  			);
	}
}
</script>
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
                <td width="94%" ><span class="STYLE1"> 员工信息列表</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
             &nbsp;&nbsp;<img src="control/images/add.gif" width="10" height="10" /><a href="javascript:openWin('${actionPath}!updateInput','添加员工',600,200,1)">添加</a>  
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
  		<form action="control/privilege/employee" method="get">
  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  			<tr>
  				<td align="right">员工名称：</td>
  				<td width="160px"><input type="text" name="realname" value="<s:property value="realname"/>"></td>
  				<td><input type="submit" class="buttom" name="submit" value="查询"></td>
  			</tr>
  		</table>
  		</form>
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
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">真实姓名</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">性别</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">联系电话</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">照片</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">类型</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <s:if test="pm.datas != null">
      <s:iterator value="pm.datas" var="employee" status="stat">
      <tr>
        <td height="5%" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="id" value="<s:property value="#employee.id"/>"/>
        </div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#stat.index+1"/></div></td>
        <td height="15%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#employee.username"/></div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#employee.realname"/></div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
        <s:if test="#employee.sex==0">
        	女
        </s:if>
        <s:if test="#employee.sex==1">
        	男
        </s:if>
        </div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#employee.phone"/></div></td>
        <td height="10%" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
        			<s:if test="#employee.getImagePath() == null">
        				<img src="control/images/nopicture2.jpg" width="50" height="60"/>
        			</s:if>
        			<s:else>
        				<img src="<s:property value="#employee.getImagePath()" />" width="50" height="60"/>
        			</s:else>
        			
        			</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#employee.type.name"/></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
            <s:if test="#employee.type.name()!='SUPER_ADMIN'">
                <a href="javascript:del('${actionPath}!delete?ids=<s:property value="#employee.id"/>')" title="">删除</a> |
            </s:if>
       <a href="javascript:openWin('${actionPath}!updateInput?id=<s:property value="#employee.id"/>','更新员工',600,200,1)" title="">编辑</a>
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
			<jsp:param name="url" value="control/privilege/employee"/>
			<jsp:param name="params" value="realname"/>
		</jsp:include>
    </td>
 </tr>
</table>
</body>
</html>

