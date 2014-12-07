package com.bbs.web.struts.form;

import org.apache.struts.action.*;
import javax.servlet.http.*;;

public class UserinfoForm extends BaseForm {
	
	   
	private Long id;	
	
    private String username;    
    private String pwd;
    private String confirmpwd;   
    private String nickname;     
    private String sex;    
    private String birthday;   
    private String email;   
    private String phone;   
    private String description;
    
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getConfirmpwd() {
		return confirmpwd;
	}
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public ActionErrors validate(ActionMapping mapping,
				HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		
		System.out.println("userForm    "+username+pwd);
		if((username == null)||(username.length()<1)||(username.length()>15) )
			errors.add("username",new ActionMessage("bbs.error.username"));
		if((pwd == null )||(pwd.length()<1)||(pwd.length()>15))
			errors.add("pwd",new ActionMessage("bbs.error.pwd"));
		if(!confirmpwd.equals(pwd))
			errors.add("confirmpwd",new ActionMessage("bbs.error.confirmpwd"));
		
		return errors;
	}
    
    
   

    
   


}
