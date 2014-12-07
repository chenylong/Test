

<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head> 
    
    <title>subjectmanager.jsp</title>
   
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body> 
  
 
  <%
     Pager pager = ((PageController)session.getAttribute("subjectpc")).getPager();
     String sortby = (String)request.getAttribute("sortby");
	 String desc   = (String)request.getAttribute("desc");
			
   
%>
  	<td><a href="<%=path%>/subjectmanager.do?method=prenewsubject"><bean:message key="bbs.add"/></a></td>
   
  <table width="100%" border="1">
    <tr>
      <td>name</td>
      <td>description</td>
      <td>delete</td>     
    </tr>
	<%		
                     
						List subjectList = (List) request.getAttribute("pageResults");	
						if ( subjectList == null ){						
							subjectList = new ArrayList();
						}
						
						for ( int i = 0 ;i<subjectList.size();i++){	
						
						Subject subject = (Subject) subjectList.get(i);
							
		%>
    <tr>
      <td><a href="<%=path%>/subjectmanager.do?method=editbyadmin&subjectid=<%=subject.getId()%>"><%=subject.getName()%></a></td>
      <td><%=subject.getDescription()%></td>
      <td><a href="<%=path%>/subjectmanager.do?method=delete&subjectid=<%=subject.getId()%>"><bean:message key="bbs.delete"/></a></td>
      
    </tr>
	<%
		}
		%>	
  </table>
    <table width="100%" border="0">
                  
                      <tr><td>
                       	<div align="center">
                       	<html:form action="/forum/subjectPage.do">
							<input type="hidden" name="method" value="goPage">
							<html:hidden name="pageForm" property="controllerName"/>
							
                       	<bean:message key="page.total.record"/><%=pager.getTotalRows()%><bean:message key="page.record"/>&nbsp;
                       	<% if (pager.isFirstPage()){%> <bean:message key="page.first"/> &nbsp;<bean:message key="page.previous"/><%}else{%><a href="<%=path%>/forum/subjectPage.do?method=first&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.first"/></a>&nbsp;<a href="<%=path%>/forum/subjectPage.do?method=previous&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.previous"/></a><%}%>&nbsp;
						<% if (pager.isLastPage()){%><bean:message key="page.next"/> &nbsp;<bean:message key="page.last"/><%}else{%><a href="<%=path%>/forum/subjectPage.do?method=next&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.next"/></a>&nbsp;<a href="<%=path%>/forum/subjectPage.do?method=last&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.last"/></a><%}%>&nbsp;
						
						<%
							List options = new ArrayList(pager.getTotalPages());
							for ( int i=0; i<pager.getTotalPages();i++)
								options.add( new Integer(i+1));
								pageContext.setAttribute("options", options, PageContext.PAGE_SCOPE);
						%>
						<bean:message key="page.skip"/>
						<html:select name="pageForm" property="curPage" onchange="document.pageForm.submit();">	
				        	<html:options name="options" labelName="options" />
						</html:select>							
						                        
						</div>
					</td></tr>
                   
                   </html:form>                       
                 <p>&nbsp;</p>
        
      </table>
  
  <%@ include file="/forum/bottom.jsp"%>
  
  </body>
</html:html>
