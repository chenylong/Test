<%@ include file="top.jsp"%>

<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>   
    
    <title>newtopic.jsp</title>   
   
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
  <html:form action="topic" method="get" focus="title">
    <input type="hidden" name="method" value="newtopic">
  <table width="100%" height="114" border="1">
    <tr>
      <td><bean:message key="topic.title"/></td>
      <td><html:text property="title" /></td>
    </tr>
    <tr>
      <td><bean:message key="topic.content"/></td>
      <td><html:textarea property="content" /></td>
    </tr>
    <tr>
      <td colspan="2"><html:submit><bean:message key="bbs.submit"/></html:submit></td>
    </tr>
  </table>
  </html:form>
  <%@ include file="bottom.jsp"%>
  </body>
</html:html>
