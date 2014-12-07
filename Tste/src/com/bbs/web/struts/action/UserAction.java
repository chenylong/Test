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



import com.bbs.util.*;
import com.bbs.domain.model.Subject;
import com.bbs.domain.model.Topic;
import com.bbs.domain.model.User;
import com.bbs.domain.service.ManagerFactory;
import com.bbs.domain.service.PageBaseManager;
import com.bbs.domain.service.SubjectManager;
import com.bbs.domain.service.TopicManager;
import com.bbs.domain.service.UserManager;
import com.bbs.web.page.PageController;
import com.bbs.web.struts.form.*;
import com.bbs.domain.service.UserExsitsException;


import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import java.util.Date;
import java.util.List;

public class UserAction extends BaseAction {
	
	
	
	
	public ActionForward register(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,
				HttpServletResponse response)
	throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		if(isTokenValid(request))
		{
			resetToken(request);	
		
		System.out.println("register");
		HttpSession session = request.getSession();
		UserinfoForm  userform = (UserinfoForm)form;	
		
		UserManager  manager = ManagerFactory.createUserManager();	
		User user = new User();		
		if(userform.getAction() != null && "edit".equalsIgnoreCase(userform.getAction()))
		{
			System.out.println("userid "+userform.getId());
			user = manager.getByID(userform.getId());	
			
		}
		else
		{		
		    user  = manager.getUserByName(userform.getUsername());		
		 	if(user != null)
		 	{
		 		ActionMessages errors = new ActionErrors();
		 		errors.add("username.exit",new ActionMessage("errors.username.exist",
					userform.getUsername()));
		 			saveErrors(request,errors);			
			    return (new ActionForward(mapping.getInput()));		
		 			
		 	}
		 	user = new User();
		 	user.setRole("member");
		 	user.setStatus("y");		
		}
		
		BeanUtils.copyProperties(user,userform);
		
		manager.saveUser(user);
		session.removeAttribute(Constants.USER_KEY);
		session.setAttribute(Constants.USER_KEY,user);
		
	    
	    String forward = (String)session.getAttribute(Constants.FORWARD);
	    if(forward != null)
	    {
	    	session.removeValue(Constants.FORWARD);  
	    	response.sendRedirect(forward);
	    }
	    return mapping.findForward("registersuccess");
		}
		else
			return mapping.findForward("registersuccess");
			
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		
		System.out.println("enter the edit method");
		HttpSession session = request.getSession();
	//	String userid = request.getParameter("userid");		
		User usertemp = (User)session.getAttribute(Constants.USER_KEY);		
		String userid = usertemp.getId().toString(); 
		
		
		UserinfoForm userinfoform = (UserinfoForm) form;
		try {
			Session s = HibernateUtil.currentSession();
			User user = (User) s.load(User.class, Long.valueOf(userid));

			if (user != null) {
				BeanUtils.copyProperties(userinfoform, user);				
			}
			System.out.println("edit method  "+userinfoform.getId());
			userinfoform.setAction("edit");

		} catch (HibernateException e) {
			log.fatal(e);
			return null;
		}
		updateFormBean(mapping, request, form);
		return mapping.findForward("edituser");
	}

	
	
	
			
	public ActionForward logon(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
	if (log.isDebugEnabled()) {
		log.debug("Entering 'add' method");
	}
	HttpSession session = request.getSession();
	LogonForm  userform = (LogonForm)form;	
	
	System.out.println(userform.getUsername());
	System.out.println(userform.getRole());
	
	UserManager  manager = ManagerFactory.createUserManager();	
	User  user  = manager.getUserByName(userform.getUsername());
	if(user == null || !user.getPwd().equals(userform.getPwd()))
	{			
		
		ActionMessages errors = new ActionErrors();
		errors.add("error.logon",new ActionMessage("errors.logon",
				userform.getUsername()));
 			saveErrors(request,errors);	 			
 		return mapping.findForward("logout");
	 //   return (new ActionForward(mapping.getInput()));	
		
	}
	session.removeAttribute(Constants.USER_KEY);
	session.setAttribute(Constants.USER_KEY,user);	
	
	if(userform.getRole().equals( "member"))
	{
    	return mapping.findForward("success");
	}
	else if(userform.getRole().equals( "admin"))
	{
		return mapping.findForward("adminlogon");
	}
	
		ActionMessages errors = new ActionErrors();
		errors.add("unknownerror",new ActionMessage("bbs.error.unknownerror"));
		saveErrors(request,errors);
		return mapping.findForward("error");
	}	
	
	
	public ActionForward listuser(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
		}
		HttpSession session = request.getSession();
		
		System.out.println("start the  toplist method");
		
		String sortby = " registerdate ";
		String desc = " desc "  ;
		
		PageForm pageform = new PageForm();
		PageController pc = new PageController();
		
		UserManager usermanager = ManagerFactory.createUserManager();				
		pc.getPageMgr().setPageDAO( ( (PageBaseManager)usermanager ).getPageDAO() );
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
		
		
		String whereclause = "c.role = "+"\'member\'";
		pc.getPager().getQueryInfo().setWhereClause(whereclause);
		List userlist = pc.toAnyPage( 1 );
		pageform.setCurPage( 1 );
		pageform.setControllerName( "userpc" );
		session.setAttribute(pageform.getControllerName(),pc);
		request.setAttribute("pageResults",userlist);
		request.setAttribute("pageForm" , pageform);
		request.setAttribute("sortby" , sortby);
		request.setAttribute("desc" , "desc".equals(desc)?"asc":"desc");		
		
		
		
		System.out.println("end the topicaciont list method");
		return mapping.findForward("userlist");				
	}
	
	
	public ActionForward delete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		String userid = (String)request.getParameter("userid");
		UserManager usermanager = ManagerFactory.createUserManager();
		
		
		if(userid != null)
		{
			User temp_user = usermanager.getByID(new Long(userid));
			if(temp_user != null)
			{
				usermanager.delete(temp_user);
				return mapping.findForward("delete");
			}			
		}			
		return mapping.findForward("error");
	}
	
	
	
	public ActionForward preregister(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.USER_KEY);
		saveToken(request);
		return mapping.findForward("register");	
	
	}
	
	public ActionForward lock(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		String userid = (String)request.getParameter("userid");
		UserManager usermanager = ManagerFactory.createUserManager();
		
		if(userid != null)
		{
			User user_temp = usermanager.getByID(new Long(userid));
			if(user_temp != null)
			{
				if(user_temp.getStatus().equalsIgnoreCase("y"))
					user_temp.setStatus("n");
				else
					user_temp.setStatus("y");
				usermanager.saveUser(user_temp);
				return mapping.findForward("list");
			}
			
		}	
		
		return mapping.findForward("error");
	}
	
	public ActionForward logout(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		session.invalidate();
		return mapping.findForward("logout");		
	}
	
	
	public ActionForward viewuser(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'add' method");
			}
		HttpSession session = request.getSession();
		String userid_str = request.getParameter("userid");
		UserManager usermanager = ManagerFactory.createUserManager();
		User user_temp = new User();
		user_temp = usermanager.getByID(new Long(userid_str));
		if((user_temp != null)||(user_temp.getId() != null))
		{
			request.setAttribute(Constants.VIEWUSER,user_temp);
			return mapping.findForward("viewusersuccess");
		}
		
		ActionMessages errors = new ActionErrors();
		errors.add("unknownerror",new ActionMessage("bbs.error.unknownerror"));
		saveErrors(request,errors);
		return mapping.findForward("error");
	
	}

}
