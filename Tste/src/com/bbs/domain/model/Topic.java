/*
 * Created Wed Apr 19 17:08:08 CST 2006 by MyEclipse Hibernate Tool.
 */ 
package com.bbs.domain.model;

import java.io.Serializable;

/**
 * A class that represents a row in the 'topic' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Topic
    extends AbstractTopic
    implements Serializable
{
    /**
     * Simple constructor of Topic instances.
     */
    public Topic()
    {
    }

    /**
     * Constructor of Topic instances given a simple primary key.
     * @param id
     */
    public Topic(java.lang.Long id)
    {
        super(id);
    }
    public String toString()
    {
    	return this.getClass().getName()+"@"+this.getId();
    }

    /* Add customized code below */

}
