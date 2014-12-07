
<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>



<html:html>
  <head> 
    
    <title>viewuser.jsp</title>
  
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
    <%
       	User viewuser = (User)request.getAttribute(Constants.VIEWUSER);
       		
	%>
	
    
    <table width="100%" height="406" border="1">
      <tr>
        <td colspan="2" class="BodyHeader_Middle">&nbsp;</td>
      </tr>
      <tr>
        <td><bean:message key="bbs.username"/>:</td>
        <td><%=viewuser.getUsername()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.nickname"/>:</td>
        <td><%=viewuser.getNickname()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.sex"/>:</td>
        <td><%=viewuser.getSex()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.birthday"/>:</td>
        <td><%=viewuser.getBirthday().toString()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.email"/>:</td>
        <td><%=viewuser.getEmail()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.phone"/>:</td>
        <td><%=viewuser.getPhone()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.description"/>:</td>
        <td><%=viewuser.getDescription()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.user.status"/></td>
        <td><%=viewuser.getStatus()%></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.registerdate"/></td>
        <td><%=viewuser.getRegisterdate().toString()%></td>
      </tr>
      <tr>
        <td><a href="<%=path%>/"></td>
      </tr>
    </table>
    <%@ include file="/forum/bottom.jsp"%>
  </body>
</html:html>
