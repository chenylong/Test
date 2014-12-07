package com.bbs.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bbs.web.page.*;
import com.bbs.web.struts.form.PageForm;

public class PageAction extends BaseAction {
	
	public ActionForward next(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
		}
		
		HttpSession session = request.getSession();
		PageForm pageForm = (PageForm)form;
		PageController pc = (PageController)session.getAttribute( pageForm.getControllerName());
		List pageResults = pc.toNext() ;

		pageForm.setCurPage( pc.getPager().getCurPage());
		updateFormBean(mapping, request, form);
		
		request.setAttribute("pageResults" , pageResults );
		
		return mapping.findForward("success");
	}
	
	public ActionForward previous(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
		}
		
		HttpSession session = request.getSession();
		PageForm pageForm = (PageForm)form;
		PageController pc = (PageController)session.getAttribute( pageForm.getControllerName());
		List pageResults = pc.toPrevious() ;
		
		
		pageForm.setCurPage( pc.getPager().getCurPage());
		updateFormBean(mapping, request, form);
		
		request.setAttribute("pageResults" , pageResults );

		return mapping.findForward("success");
	}
	
	public ActionForward first(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
		}
		
		HttpSession session = request.getSession();
		PageForm pageForm = (PageForm)form;
		PageController pc = (PageController)session.getAttribute(pageForm.getControllerName());
		List pageResults =pc.toFirst() ;
		
		
		pageForm.setCurPage( pc.getPager().getCurPage());
		updateFormBean(mapping, request, form);
		
		request.setAttribute("pageResults" , pageResults );

		return mapping.findForward("success");
	}
	
	public ActionForward last(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
		}
		
		HttpSession session = request.getSession();
		PageForm pageForm = (PageForm)form;
		PageController pc = (PageController)session.getAttribute(pageForm.getControllerName());
		List pageResults = pc.toLast() ;
				
		pageForm.setCurPage( pc.getPager().getCurPage());
		updateFormBean(mapping, request, form);
		
		request.setAttribute("pageResults" , pageResults );

		return mapping.findForward("success");
	}
	
	public ActionForward goPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'next' method");
		}
		PageForm pageForm = (PageForm)form;
		HttpSession session = request.getSession();
		
		PageController pc = (PageController)session.getAttribute(pageForm.getControllerName());
		List pageResults = pc.toAnyPage( pageForm.getCurPage()); ;
		//pageForm.setControllerName( )
		
		//pageForm.setCurPage( pc.getPager().getCurPage());
		updateFormBean(mapping, request, form);
		
		request.setAttribute("pageResults" , pageResults );

		return mapping.findForward("success");
	}
}
