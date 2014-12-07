<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
</style>
<table width="100%" border="1">
  <tr>
    <td height="97" align="center"><img src="pic/xx521_sjzl_08.gif" width="679" height="117"></td>
  </tr>
  <tr>
    <td height="106" align="center">
	<bean:message key="bbs.welcome"/><br>
	<a href="<%=path%>/subject.do?method=subjectlist"> <bean:message key="bbs.enterin"/></td>
  </tr>
</table>

<%@ include file="bottom.jsp" %>