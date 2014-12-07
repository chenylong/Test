package com.bbs.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bbs.domain.model.Subject;
import com.bbs.domain.model.Topic;
import com.bbs.domain.service.ManagerFactory;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.SubjectManager;
import com.bbs.domain.service.TopicManager;
import com.bbs.util.Constants;
import com.bbs.web.page.PageController;
import com.bbs.web.struts.form.*;
import org.apache.commons.beanutils.BeanUtils;

public class SubjectAction extends BaseAction {
	
	public ActionForward subjectlist(ActionMapping mapping,ActionForm form,
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
	
	
	public ActionForward listbyadmin(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
	if (log.isDebugEnabled()) {
		log.debug("Entering 'add' method");		
	}	
	
	HttpSession session = request.getSession();
	
	System.out.println("start the  subjectlist method");
	
	String sortby = " createdate ";
	String desc = " desc "  ;
	
	PageForm pageform = new PageForm();
	PageController pc = new PageController();
	
	SubjectManager subjectmanager = ManagerFactory.createSubjectManager();		
	
	pc.getPageMgr().setPageDAO( ( (PageBaseManager)subjectmanager ).getPageDAO() );
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
	
	
	String whereclause = "";
	pc.getPager().getQueryInfo().setWhereClause(whereclause);
	List subjectlist = pc.toAnyPage( 1 );
	pageform.setCurPage( 1 );
	pageform.setControllerName( "subjectpc" );
	session.setAttribute(pageform.getControllerName(),pc);
	request.setAttribute("pageResults",subjectlist);
	request.setAttribute("pageForm" , pageform);
	request.setAttribute("sortby" , sortby);
	request.setAttribute("desc" , "desc".equals(desc)?"asc":"desc");		
		
	
	System.out.println("end the topicaciont list method");
	return mapping.findForward("listbyadmin");	
	}
	
	public ActionForward prenewsubject(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		saveToken(request);
		return mapping.findForward("addsubject");
	}
	
	
	public ActionForward newsubject(ActionMapping mapping,ActionForm form,
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
		SubjectForm subjectform = (SubjectForm)form;
		SubjectManager subjectmanager = ManagerFactory.createSubjectManager();
		
		Subject subject = new Subject();		
		BeanUtils.copyProperties(subject,subjectform);
		
		subjectmanager.save(subject);
		
		return mapping.findForward("addsubjectsuccess");
		}
		else
			return mapping.findForward("addsubjectsuccess");
		
	}
	
	
	
	
	public ActionForward delete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		String subjectid = (String)request.getParameter("subjectid");
		SubjectManager subjectmanager = ManagerFactory.createSubjectManager();
		
		
		if(subjectid != null)
		{
			Subject temp_subject = subjectmanager.getByID(new Long(subjectid));
			if(temp_subject != null)
			{
				subjectmanager.delete(temp_subject);
				return mapping.findForward("delete");
			}			
		}			
		return mapping.findForward("error");
	}
	
	
	

}
