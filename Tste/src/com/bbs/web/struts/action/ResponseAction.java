package com.bbs.web.struts.action;

import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.bbs.domain.model.*;
import com.bbs.web.struts.form.*;
import com.bbs.domain.service.ManagerFactory;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.*;
import com.bbs.util.Constants;
import com.bbs.web.page.PageController;
import com.bbs.web.struts.form.PageForm;
import com.bbs.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class ResponseAction extends BaseAction {

	public ActionForward responselist(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		System.out.println("start the responselist method");
		HttpSession session = request.getSession();		
		String sortby = " createdate ";
		String desc = " asc "  ;
		
		PageForm pageform = new PageForm();
		PageController pc = new PageController();
		
		ResponseManager responsemanager = ManagerFactory.createReseponseManager();
		TopicManager topicmanager = ManagerFactory.createTopicManager();		
		String topicid_str = (String )request.getParameter("topicid");
		System.out.println("topicid "+topicid_str);
		Long topicid_long = new Long(0);
		if(topicid_str == null)
		{
			Topic topic_temp = (Topic)session.getAttribute(Constants.CURTOPIC);
			topicid_long = topic_temp.getId();
		}			
		
		topicid_long = new Long(topicid_str);		
		Topic topic = topicmanager.getByID(topicid_long);
		pc.getPageMgr().setPageDAO( ( (PageBaseManager)responsemanager).getPageDAO() );
		if( sortby != null )
		{
			StringBuffer  sortbuff = new StringBuffer();
			sortbuff.append(sortby);
			
			if(desc != null)
			{
				sortbuff.append(" ");
				sortbuff.append(desc);
			}
			pc.getPager().getQueryInfo().setOrderByClause(sortbuff.toString());
		}
		
		session.removeAttribute(Constants.CURTOPIC);
		session.setAttribute(Constants.CURTOPIC,topic);
		
		String whereclause = "c.topic.id = "+topic.getId();
		pc.getPager().getQueryInfo().setWhereClause(whereclause);
		List responselist = pc.toAnyPage( 1 );
	
				
		/*System.out.println("dddddddddddddddddddd");
		
		Set responseset = topic.getResponses();
		for(Iterator it = responseset.iterator();it.hasNext();)
			System.out.println("343434343434"+((Response)it.next()).getContent());
		//System.out.println("ccccccc"+ responselist.size());
		Session s = HibernateUtil.currentSession();
		String hqlstr = "from Response" ;
		Query query = s.createQuery(hqlstr);
	//	query.setParameter("topic",topic);
		List result = query.list();
		for(Iterator it = result.iterator();it.hasNext();)
			System.out.println("909090909"+((Response)it.next()).getContent());*/		
		
		
		
		
		pageform.setCurPage( 1 );
		pageform.setControllerName( "responsepc" );
		session.setAttribute(pageform.getControllerName(),pc);
		request.setAttribute("pageResults",responselist);
		request.setAttribute("pageForm" , pageform);
		request.setAttribute("sortby" , sortby);
		request.setAttribute("desc" , "desc".equals(desc)?"asc":"desc");		
		
		System.out.println("end the responselist method");	
		
		return mapping.findForward("responselist");		
		
	}
	
	public ActionForward prenewresponse(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.USER_KEY);
		if( user == null)
		{
			ActionMessages errors = new ActionErrors();
	 		errors.add("no logong",new ActionMessage("errors.user.logon"));		
	 		saveErrors(request,errors);				
			return mapping.findForward("logon");
		}	
		saveToken(request);
		return mapping.findForward("newresponse");
		
		
	}
	
	
	public ActionForward newresponse(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		HttpSession session = request.getSession();
		ResponseForm responseform = (ResponseForm)form;
		User user = (User)session.getAttribute(Constants.USER_KEY);
		String topicid = (String)request.getParameter("topicid");
		System.out.println("newresponse topicid"+topicid);
		Response newresponse = new Response();
		TopicManager topicmanager = ManagerFactory.createTopicManager();
		ResponseManager responsemanager = ManagerFactory.createReseponseManager();
		if(user == null || topicid == null)
		{
			ActionMessages errors = new ActionErrors();
	 		errors.add("no logong",new ActionMessage("errors.user.logon"));		
	 		saveErrors(request,errors);				
			return mapping.findForward("logon");
		}
					
		Topic temp_topic = topicmanager.getByID(new Long(topicid));		
		newresponse.setContent(responseform.getContent());
		newresponse.setTopic(temp_topic);
		newresponse.setWriter(user);
		newresponse.setLastmodified(new Date(System.currentTimeMillis()));
		temp_topic.setLastmodiedtime(new Date(System.currentTimeMillis()));
		topicmanager.save(temp_topic);
		responsemanager.save(newresponse);
		
		return mapping.findForward("addresponsesuccess");		
		
	}
	
	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		
		HttpSession session = request.getSession();
		String responseid = request.getParameter("responseid");
		ResponseForm responseform = (ResponseForm)form;
		ResponseManager responsemanager = ManagerFactory.createReseponseManager();
		if(responseid != null)
		{
			Response response_update = new Response();
			response_update = responsemanager.getById(new Long(responseid));
			
			if(response_update != null)
			{
				BeanUtils.copyProperty(responseform,"content",response_update);				
			}
			
			responseform.setAction("edit");
			updateFormBean(mapping, request, form);
			return mapping.findForward("editresponse");
			
		}
		
		
		return  mapping.findForward("error");
		
	}
	
	
	
	
}
