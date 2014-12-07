<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html:html>
  <head>    
    
    <title>newsubject.jsp</title>    
    
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
   <html:form action="subjectmanager" method="post" focus="name">
  <table width="100%" height="139" border="1">
     <tr>
       <td colspan="2" class="BodyHeader_Middle">&nbsp;</td>
     </tr>
     <tr>
       <td width="37%">subject name:</td>
       <td width="63%"><html:text property="name" /></td>
     </tr>
     <tr>
       <td>subject description:</td>
       <td><html:textarea property="description" /></td>
     </tr>
     <tr>
       <td align="center"><html:submit/></td>
     </tr>
   </table>
    </html:form>
   <%@ include file="/forum/bottom.jsp"%>
  </body>
</html:html>
