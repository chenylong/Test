<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bbs.domain.model.*" %>
<%@ page import="com.bbs.web.struts.form.*"%>
<%@ page import="com.bbs.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>logon</title>
     
   
    <style type="text/css">

    </style>
  </head>
  
  <body>
  
  <table width="100%" height="462" border="1">
    <tr>
      <td height="42" align="center" class="Succ"><img src="pic/xx521_sjzl_08.gif" width="679" height="117"></td>
    </tr>
    <tr>
      <td height="224" align="center">
      <html:errors/>
	  <%
	  
      User user = (User)session.getAttribute(Constants.USER_KEY);     
      if(user != null)
      {
      request.setAttribute("curUser",user);
	  %>
	  <bean:message key="bbs.welcome"/>:<bean:write name="curUser" property="username"/>
	  <a href="<%=path%>/logon.do?method=logout"><bean:message key="bbs.logout"/></a>
	  <%
	  }
	  else
	  {
	  %>	  <html:form action="/logon" method="get">
  		<input type="hidden" name="method" value="logon"/>
	  <table width="300" height="121" border="0" cellspacing="0">
        <tr>
          <td width="51" align="left" class="buttonText"><bean:message key="bbs.username"/>:</td>
          <td width="245" align="left"><html:text property="username"/></td>
        </tr>
        <tr>
          <td><bean:message key="bbs.pwd"/>:</td>
          <td><html:password property="pwd"/></td>
        </tr>
        <tr>
          <td><bean:message key="bbs.logongrole"/>:</td>
          <td><html:radio property="role" value="member"/><bean:message key="bbs.member"/> <html:radio property="role" value="admin"/><bean:message key="bbs.admin"/></td>
        </tr>
        <tr>
          <td align="center" width="20%"><html:submit><bean:message key="bbs.submit"/></html:submit></td>
          <td align="center" width="20%"><html:reset><bean:message key="bbs.reset"/></html:reset></td>
          <td align="center" width="60%"><a href="<%=path%>/subject.do?method=subjectlist"><bean:message key="errors.logonwithnoname"/></a></td>
        </tr>
      </table>
	  </html:form>
	  	  <a href="<%=path%>/register.do?method=preregister"><bean:message key="bbs.register"/></a>
          <%
    }
    %></td>
    </tr>
    <tr>
      <td align="center"><img src="pic/xx521_sjzl_08.gif" width="679" height="117" align="middle"></td>
    </tr>
  </table>
  </body>
</html>
