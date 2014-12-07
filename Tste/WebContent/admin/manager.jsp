

<%@ include file="/forum/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<html:html>
  <head>   
    
    <title>manager.jsp</title>
  
    <style type="text/css">
<!--
@import url("../pic/OSX/OSX.css");
-->
    </style>
  </head>
  
  <body>
  <%
     Pager pager = ((PageController)session.getAttribute("userpc")).getPager(); 
                 
	String sortby = (String)request.getAttribute("sortby");
	String desc   = (String)request.getAttribute("desc");
	Subject subject = (Subject)session.getAttribute(Constants.CURSUBJECT);
 %> 
  
  <table width="100%" border="1">
    <tr>
      <td colspan="4" class="BodyHeader_Middle">&nbsp;</td>
    </tr>
    <tr>
      <td>username</td>
      <td>registerdate</td>
      <td>delete</td>
      <td>lock</td>	  
    </tr>
	   <%		
                     
						List userList = (List) request.getAttribute("pageResults");	
						if ( userList == null ){						
							userList = new ArrayList();
						}
						
						for ( int i = 0 ;i<userList.size();i++){	
						
						User user_temp = (User) userList.get(i);
							
		%>
	
    <tr>
      <td><a href="<%=path%>/register.do?method=viewuser&userid=<%=user_temp.getId()%>"><%=user_temp.getUsername()%></a></td>
      <td><%=user_temp.getRegisterdate().toString()%></td>
      <td><a href="<%=path%>/register.do?method=delete&userid=<%=user_temp.getId()%>"><bean:message key="bbs.delete"/></td>
      <td>
	  <a href="<%=path%>/register.do?method=lock&userid=<%=user_temp.getId()%>">
			<%
				if(user_temp.getStatus().equalsIgnoreCase("y"))
				{
			%>
			<bean:message key="bbs.lock"/>
			<%
			}else
			{
			%>
			<bean:message key="bbs.unlock"/>
			<%
			}
			%>
			</a>
	  </td>
    </tr>
	<%
		}
		%>	
  </table>
  <table width="100%" border="0">
                  
                      <tr><td>
                       	<div align="center">
                       	<html:form action="/forum/userPage.do">
							<input type="hidden" name="method" value="goPage">
							<html:hidden name="pageForm" property="controllerName"/>
							
                       	<bean:message key="page.total.record"/><%=pager.getTotalRows()%><bean:message key="page.record"/>&nbsp;
                       	<% if (pager.isFirstPage()){%> <bean:message key="page.first"/> &nbsp;<bean:message key="page.previous"/><%}else{%><a href="<%=path%>/forum/userPage.do?method=first&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.first"/></a>&nbsp;<a href="<%=path%>/forum/userPage.do?method=previous&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.previous"/></a><%}%>&nbsp;
						<% if (pager.isLastPage()){%><bean:message key="page.next"/> &nbsp;<bean:message key="page.last"/><%}else{%><a href="<%=path%>/forum/userPage.do?method=next&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.next"/></a>&nbsp;<a href="<%=path%>/forum/userPage.do?method=last&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.last"/></a><%}%>&nbsp;
						
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
    
    
  </body>
</html:html>
