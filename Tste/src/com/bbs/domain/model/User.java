/*
 * Created Mon Apr 10 21:02:56 CST 2006 by MyEclipse Hibernate Tool.
 */ 
package com.bbs.domain.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'user' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class User
    extends AbstractUser
    implements Serializable
{
    /**
     * Simple constructor of User instances.
     */
    public User()
    {
    }

    /**
     * Constructor of User instances given a simple primary key.
     * @param id
     */
    public User(java.lang.Long id)
    {
        super(id);
    }
    
    public String toString()
    {
    	return this.getClass().getName()+"@"+this.getId();
    }

    /* Add customized code below */

}
