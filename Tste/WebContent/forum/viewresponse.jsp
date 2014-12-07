<%@ include file="top.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>

<html:html>
  <head>
    
    <title>viewresponse.jsp</title>   
     <style type="text/css">
<!--
@import url("pic/OSX/OSX.css");
-->
    </style>
</head>  
    <% 
  	Pager pager = ((PageController)session.getAttribute("responsepc")).getPager();
  	String sortby = (String)request.getAttribute("sortby");
  	String desc   = (String)request.getAttribute("desc");
  	Topic curtopic = (Topic)session.getAttribute(Constants.CURTOPIC);
  	User curuser = (User)session.getAttribute(Constants.USER_KEY);
  %>
  
  
  <body>
  <table width="100%" border="1">
  
  <%
   			if(pager.getCurPage() == 1)
   			{
   %>
    <tr>
      <td colspan="2" class="BodyHeader_Middle">&nbsp;</td>
    </tr>	
    <tr>
      <td width="37%" height="62"><%=curtopic.getWriter().getUsername()%></td>
      <td width="63%">
	    <table width="95%" height="95%" border="0" cellspacing="0">
          <tr>
            <td colspan="2"><%=curtopic.getTitle()%></td>
          </tr>
          <tr>
            <td colspan="2"><%=curtopic.getContent()%></td>
          </tr>
          <tr>
            <td width="50%" align="left"><bean:message key="bbs.topicowner"/></td>
            <td width="76%" align="center" valign="bottom">
			
				<%
             		if(curuser != null)
             		{
             			if(curuser.getId() == curtopic.getWriter().getId())
             			{
             		
        		 %>
		 		<a href="<%=path%>/topic.do?method=delete&topicid=<%=curtopic.getId()%>"><bean:message key="bbs.delete"/></a>
	  
	  		    <%
             }
             }
             %>	
			</td>
          </tr>
        </table>
	  </td>
    </tr>
	<%
   }
   %>
   
	<%		
                     
						List responseList = (List) request.getAttribute("pageResults");	
						if ( responseList == null ){						
							responseList = new ArrayList();
						}
						int count = 0;
						for ( int i = 0 ;i<responseList.size();i++){
						Response response_temp = (Response) responseList.get(i);
						System.out.println(response_temp.getContent());
							
		%> 
	
    <tr>
      <td height="31" colspan="2" class="BodyHeader_Middle">&nbsp;</td>
    </tr>
    <tr>
      <td height="76"><%=response_temp.getWriter().getUsername()%></td>
      <td height="76"><table width="95%" height="95%" border="0" cellspacing="0">
        <tr>
          <td colspan="2">
		  <%= response_temp.getContent()%>		  </td>
        </tr>
        <tr>
          <td width="50%"><%=++count%><bean:message key="bbs.floor"/></td>
          <td width="53%" align="center"><%
             		if(curuser != null)
             		{
             			if(curuser.getId() == response_temp.getWriter().getId())
             			{
             		
       %>
	   <a href="<%=path%>/response.do?method=edit&responseid=<%=response_temp.getId()%>"><bean:message key="bbs.edit"/></a>
	    <%
             }
             }
             %></td>
        </tr>
      </table></td>
    </tr>
	<%
		}
	%>	
  </table>
  
  <table width="100%" border="0">
                  
                      <tr><td>
                       	<div align="center">
                       	<html:form action="/forum/responsePage.do">
							<input type="hidden" name="method" value="goPage">
							<html:hidden name="pageForm" property="controllerName"/>
							
                       	<bean:message key="page.total.record"/><%=pager.getTotalRows()%><bean:message key="page.record"/>&nbsp;
                       	<% if (pager.isFirstPage()){%> <bean:message key="page.first"/> &nbsp;<bean:message key="page.previous"/><%}else{%><a href="<%=path%>/forum/responsePage.do?method=first&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.first"/></a>&nbsp;<a href="<%=path%>/forum/responsePage.do?method=previous&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.previous"/></a><%}%>&nbsp;
						<% if (pager.isLastPage()){%><bean:message key="page.next"/> &nbsp;<bean:message key="page.last"/><%}else{%><a href="<%=path%>/forum/responsePage.do?method=next&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.next"/></a>&nbsp;<a href="<%=path%>/forum/responsePage.do?method=last&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.last"/></a><%}%>&nbsp;
						
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
	  
	   <html:form action="/response.do" method="get">
      	<input type="hidden" name="method" value="newresponse"/>
      	<input type="hidden" name="topicid" value="<%=curtopic.getId()%>"/>
      	<html:hidden property="action"/>
      	<html:hidden property="id"/>
      	<table>
      		<tr>
      			<td>
      				<bean:message key="topic.content"/>
      			</td>
      			<td>
      				<html:textarea property="content" cols="100" rows="10"/>
      			</td>
      		</tr>
      		<tr>
      		<td>
      		<html:submit><bean:message key="bbs.submit"/></html:submit>
      		</td>
      		</tr>
      	</table>
      </html:form>
  
  
  <%@ include file="bottom.jsp"%>
  </body>
</html:html>
