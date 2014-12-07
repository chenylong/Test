/*
 * Created Mon Apr 17 20:18:26 CST 2006 by MyEclipse Hibernate Tool.
 */ 
package com.bbs.domain.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'response' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Response
    extends AbstractResponse
    implements Serializable
{
    /**
     * Simple constructor of Response instances.
     */
    public Response()
    {
    }

    /**
     * Constructor of Response instances given a simple primary key.
     * @param id
     */
    public Response(java.lang.Long id)
    {
        super(id);
    }
    
    public String toString()
    {
    	return this.getClass().getName()+"@"+this.getId();
    }

    /* Add customized code below */

}
