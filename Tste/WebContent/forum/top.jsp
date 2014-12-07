<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bbs.domain.model.*" %>
<%@ page import="com.bbs.web.struts.form.*"%>
<%@ page import="com.bbs.util.*"%>
<%@ page import="com.bbs.web.page.*"%>
<%@ page import="com.bbs.util.LabelValue"%>
<%@ page import="com.bbs.util.DateUtil"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
@import url("../pic/OSX/OSX.css");
-->
</style>


  
<table width="100%" border="1">
  <tr>
    <td height="61" align="center"><img src="pic/xx521_sjzl_08.gif" width="679" height="117"></td>
  </tr>
  
  <%
      User user = (User)session.getAttribute(Constants.USER_KEY);
      request.setAttribute("curUser",user);
      if(user != null)
      {
  %>
  <tr>
    <td height="34" align="left">
	<bean:message key="bbs.welcome"/>:<bean:write name="curUser" property="username"/>&nbsp
   <a href="<%=path%>/register.do?method=edit"><bean:message key="bbs.myaccount"/></a>&nbsp
   <a href="<%=path%>/logon.do?method=logout"><bean:message key="bbs.logout"/></a></td>
  </tr>
   <%
  }
  else
  {
  %>
  <tr>
    <td height="36" align="left">
	<a href="<%=path%>/user/logon.jsp"><bean:message key="bbs.logon"/></a>
    <a href="<%=path%>/usermanager.do?method=preregister"><bean:message key="bbs.register"/></a>
	</td>
  </tr>
  <%
  }
  %>
  
</table>
