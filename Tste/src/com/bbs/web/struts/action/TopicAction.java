package com.bbs.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bbs.domain.service.*;
import com.bbs.domain.service.impl.*;
import com.bbs.domain.model.*;
import java.util.*;

import com.bbs.util.*;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.web.page.PageController;
import com.bbs.web.struts.form.*;

public class TopicAction extends BaseAction {
	
	public ActionForward browse(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
	if (log.isDebugEnabled()) {
		log.debug("Entering 'add' method");
	}
	System.out.println("Enter the browse method");
	HttpSession session = request.getSession();
	SubjectManager manager = (SubjectManager)ManagerFactory.createSubjectManager();
	List allsubjects = manager.getAllSubject();
	
	session.removeAttribute(Constants.ALLSUBJECT);
	session.setAttribute(Constants.ALLSUBJECT,allsubjects);
	System.out.println("11111111111");
	return mapping.findForward("viewsubject");	
	}

	public ActionForward listtopic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		HttpSession session = request.getSession();
		
		System.out.println("start the  toplist method");
		
		String sortby = " lastmodiedtime ";
		String desc = " desc "  ;
		
		PageForm pageform = new PageForm();
		PageController pc = new PageController();
		
		TopicManager topicmanager = ManagerFactory.createTopicManager();
		SubjectManager subjectmanager = ManagerFactory.createSubjectManager();		
		String subjectid_str = (String )request.getParameter("subjectid");
		if( subjectid_str == null )
		{
			Subject subject = (Subject)session.getAttribute(Constants.CURSUBJECT);
			subjectid_str = subject.getId().toString();
		
		}
		
		System.out.println("subjectid "+subjectid_str);
		
		Long subjectid_long = new Long(subjectid_str);		
		Subject subject = subjectmanager.getByID(subjectid_long);		
		pc.getPageMgr().setPageDAO( ( (PageBaseManager)topicmanager ).getPageDAO() );
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
		
		
		String whereclause = "c.subject.id = "+subject.getId();
		pc.getPager().getQueryInfo().setWhereClause(whereclause);
		List topiclist = pc.toAnyPage( 1 );
		pageform.setCurPage( 1 );
		pageform.setControllerName( "topicpc" );
		session.setAttribute(pageform.getControllerName(),pc);
		request.setAttribute("pageResults",topiclist);
		request.setAttribute("pageForm" , pageform);
		request.setAttribute("sortby" , sortby);
		request.setAttribute("desc" , "desc".equals(desc)?"asc":"desc");		
		
		session.removeAttribute(Constants.CURSUBJECT);
		session.setAttribute(Constants.CURSUBJECT,subject);
		
		System.out.println("end the topicaciont list method");
		return mapping.findForward("topiclist");				
	}
	
	public ActionForward viewtopic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
        HttpSession session = request.getSession();
		
		System.out.println("start the  toplist method");
		
		String sortby = " createDate ";
		String desc = " desc "  ;
		
		PageForm pageform = new PageForm();
		PageController pc = new PageController();
		
		TopicManager topicmanager = ManagerFactory.createTopicManager();
		SubjectManager subjectmanager = ManagerFactory.createSubjectManager();		
		String subjectid_str = (String )request.getParameter("subjectid");
		if( subjectid_str == null )
		{
			Subject subject = (Subject)session.getAttribute(Constants.CURSUBJECT);
			subjectid_str = subject.getId().toString();
		
		}
		
		System.out.println("subjectid "+subjectid_str);
		
		Long subjectid_long = new Long(subjectid_str);		
		Subject subject = subjectmanager.getByID(subjectid_long);		
		pc.getPageMgr().setPageDAO( ( (PageBaseManager)topicmanager ).getPageDAO() );
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
		
		
		String whereclause = "c.subject.id = "+subject.getId();
		pc.getPager().getQueryInfo().setWhereClause(whereclause);
		List topiclist = pc.toAnyPage( 1 );
		pageform.setCurPage( 1 );
		pageform.setControllerName( "topicpc" );
		session.setAttribute(pageform.getControllerName(),pc);
		request.setAttribute("pageResults",topiclist);
		request.setAttribute("pageForm" , pageform);
		request.setAttribute("sortby" , sortby);
		request.setAttribute("desc" , "desc".equals(desc)?"asc":"desc");		
		
		session.removeAttribute(Constants.CURSUBJECT);
		session.setAttribute(Constants.CURSUBJECT,subject);
		
		System.out.println("end the topicaciont list method");
		return mapping.findForward("topiclist");				
		
	}
	
	
	
	public ActionForward newtopic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		
		if(isTokenValid(request))
		{
			resetToken(request);
		HttpSession session = request.getSession();
		TopicForm topicform = (TopicForm)form;
		
		UserManager usermanager = ManagerFactory.createUserManager();
		TopicManager topicmanager = ManagerFactory.createTopicManager();
		SubjectManager subjectmanager = ManagerFactory.createSubjectManager();
		
		Topic newtopic = new Topic();		
		Subject subject = (Subject)session.getAttribute(Constants.CURSUBJECT);
		User user = (User)session.getAttribute(Constants.USER_KEY);	
		
		User user_temp = usermanager.getByID(user.getId());
		Subject subject_temp = subjectmanager.getByID(subject.getId());
		
		
		
		if( ( user != null )&&(subject != null) )
		{			
			if(user.getStatus().equalsIgnoreCase("y"))
			{
			BeanUtils.copyProperties(newtopic,topicform);
			newtopic.setWriter(user_temp);
			newtopic.setSubject(subject_temp);
			newtopic.setLastmodiedtime(new Date(System.currentTimeMillis()));
			newtopic.setIflocked("N");
			newtopic.setLastmodiedtime(new Date(System.currentTimeMillis()));
			topicmanager.save(newtopic);
			}
			else
			{
				ActionMessages errors = new ActionErrors();
		 		errors.add("you are locked",new ActionMessage("errors.user.locked"));		
		 		saveErrors(request,errors);				
				return mapping.findForward("error");
			}
		}
		else
		{			//rhese code has  some problem needed more case dealed
			if(user == null)
				return mapping.findForward("logon");
			
			return mapping.findForward("error.newtopic");
		}	
		
		return mapping.findForward("addtopicsuccess");	
		}
		else
			return mapping.findForward("addtopicsuccess");
		
	}
	
	
	public ActionForward prenewtopic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		System.out.println("enter the prenewtopic method");
		
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
		return mapping.findForward("newtopic");
	
	}
	
	public ActionForward delete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		String topicid = (String)request.getParameter("topicid");
		TopicManager topicmanager = ManagerFactory.createTopicManager();
		
		
		if(topicid != null)
		{
			Topic temp_topic = topicmanager.getByID(new Long(topicid));
			if(temp_topic != null)
			{
				topicmanager.delete(temp_topic);
				return mapping.findForward("delete");
			}			
		}			
		return mapping.findForward("error");
	}
	
	
	
	
	

}
