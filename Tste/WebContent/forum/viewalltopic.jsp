
<%@ include file="top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>   
    <title>viewalltopic.jsp</title>     
    <style type="text/css">
<!--
@import url("pic/OSX/OSX.css");

-->
    </style>
  </head>  
  <%
       Pager pager = ((PageController)session.getAttribute("topicpc")).getPager();   
       String sortby = (String)request.getAttribute("sortby");
	   String desc   = (String)request.getAttribute("desc");
	   Subject subject = (Subject)session.getAttribute(Constants.CURSUBJECT);
 %>
  
  
  
  
  
  
  
  <body>
  <table width="100%" border="1">
      <tr>
        <td align="left"> <a href="<%=path%>/topic.do?method=prenewtopic"><bean:message key="bbs.newtopic"/></a></td>
      </tr>
    </table>
	
	<table width="85%" border="1">
      <tr>
        <td width="80%">title</td>
        <td width="20%">writer</td>
      </tr>
	   <%		
                     
						List topicList = (List) request.getAttribute("pageResults");	
						System.out.println("pageResultlength"+topicList.size());
						if ( topicList == null ){						
							topicList = new ArrayList();
						}
						
						for ( int i = 0 ;i<topicList.size();i++){	
						
						Topic topic = (Topic) topicList.get(i);
							
		%>
      <tr>
        <td colspan="2" class="BodyHeader_Middle">&nbsp;</td>
      </tr>
      <tr>
        <td height="50"><a href="<%=path%>/response.do?method=responselist&topicid=<%=topic.getId()%>"><%=topic.getTitle()%></a></td>
        <td height="50"><%=topic.getWriter().getUsername()%></td>
      </tr>
	  <%
		}
		%>	
    </table>
	
	<table width="100%" border="0">
                  
                      <tr><td>
                       	<div align="center">
                       	<html:form action="/forum/topicPage.do">
							<input type="hidden" name="method" value="goPage">
							<html:hidden name="pageForm" property="controllerName"/>
							
                       	<bean:message key="page.total.record"/><%=pager.getTotalRows()%><bean:message key="page.record"/>&nbsp;
                       	<% if (pager.isFirstPage()){%> <bean:message key="page.first"/> &nbsp;<bean:message key="page.previous"/><%}else{%><a href="<%=path%>/forum/topicPage.do?method=first&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.first"/></a>&nbsp;<a href="<%=path%>/forum/topicPage.do?method=previous&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.previous"/></a><%}%>&nbsp;
						<% if (pager.isLastPage()){%><bean:message key="page.next"/> &nbsp;<bean:message key="page.last"/><%}else{%><a href="<%=path%>/forum/topicPage.do?method=next&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.next"/></a>&nbsp;<a href="<%=path%>/forum/topicPage.do?method=last&controllerName=<bean:write name="pageForm" property="controllerName"/>"><bean:message key="page.last"/></a><%}%>&nbsp;
						
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
	
	<%@ include file="bottom.jsp" %>
           
  </body>
</html>
