/*
 * Created Tue Apr 11 10:04:33 CST 2006 by MyEclipse Hibernate Tool.
 */ 
package com.bbs.domain.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'subject' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Subject
    extends AbstractSubject
    implements Serializable
{
    /**
     * Simple constructor of Subject instances.
     */
    public Subject()
    {
    }

    /**
     * Constructor of Subject instances given a simple primary key.
     * @param id
     */
    public Subject(java.lang.Long id)
    {
        super(id);
    }
    public String toString()
    {
    	return this.getClass().getName()+"@"+this.getId();
    }
    /* Add customized code below */

}
