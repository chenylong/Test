<%@ include file="top.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>   
   
    <style type="text/css">
<!--
@import url("pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
  <table width="100%" height="66" border="1">  
   
   <%
        	List allsubjects = (List)session.getAttribute(Constants.ALLSUBJECT);
        	System.out.println(allsubjects.size());
        	if(allsubjects != null && allsubjects.size()>0 )
        	{
        	for(Iterator it=allsubjects.iterator();it.hasNext();)
        	{
        	Subject subject = (Subject)it.next();
            request.setAttribute("now",subject);
      %>
    <tr>
      <td colspan="2" class="BodyHeader_Middle">&nbsp;</td>
    </tr>
    <tr>
      <td width="46%"><a href="<%=path%>/topic.do?method=listtopic&subjectid=<%=subject.getId()%>"><bean:write name="now" property="name"/></td>
      <td width="54%" align="right"><bean:write name="now" property="description"/></td>
    </tr>
   <%
    }
    }
    else{
    %>
	<h1> there is no subject</h1>
     <%
    }%> 
	
    </table>
    <%@ include file="bottom.jsp" %>
  </body>
</html>
