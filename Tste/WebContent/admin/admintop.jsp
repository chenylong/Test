<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html:html>
  <head> 
    
    <title>admintop.jsp</title>
 
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
   <table width="100%" border="1">
        <tr>
          <td height="39"><a href="<%=path%>/register.do?method=listuser"><bean:message key="bbs.usermanager"/></a></td>
        </tr>
        <tr>
          <td height="59"><a href="<%=path%>/subjectmanager.do?method=listbyadmin"><bean:message key="bbs.subjectmanager"/></a></td>
        </tr>
      </table>
  <%@ include file="/forum/bottom.jsp"%>
  </body>
</html:html>
