<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html:html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
  
  
  
  <html:errors />
  
    <html:form action="/usermanager" method="get">
      <input type="hidden" name="method" value="register"/>
      <html:hidden property="action"/>
      <html:hidden property="id"/>
    
    <table width="100%" height="375" border="1">
      <tr>
        <td><bean:message key="bbs.username"/>:</td>
        <td><html:text property="username"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.pwd"/>:</td>
        <td><html:password property="pwd"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.confirmpwd"/>:</td>
        <td><html:password property="confirmpwd"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.nickname"/>:</td>
        <td><html:text property="nickname"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.sex"/>:</td>
        <td><html:radio property="sex" value="M"/><bean:message key="bbs.male"/>
      		<html:radio property="sex" value="F"/><bean:message key="bbs.female"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.birthday"/>:</td>
        <td><html:text property="birthday"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.email"/>:</td>
        <td><html:text property="email"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.phone"/>:</td>
        <td><html:text property="phone"/></td>
      </tr>
      <tr>
        <td><bean:message key="bbs.description"/>:</td>
        <td><html:text property="description"/></td>
      </tr>
      <tr>
        <td align="center"><html:submit><bean:message key="bbs.submit"/></html:submit>  <html:reset><bean:message key="bbs.reset"/></html:reset></td>
      </tr>
    </table>
	</html:form>
    <%@ include file="/forum/bottom.jsp"%>
  </body>
</html:html>
