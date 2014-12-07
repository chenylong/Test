/*
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 *
 * Created Mon Apr 17 20:18:26 CST 2006 by MyEclipse Hibernate Tool.
 */
package com.bbs.domain.model;

import java.io.Serializable;

/**
 * A class that represents a row in the response table. 
 * You can customize the behavior of this class by editing the class, {@link Response()}.
 * WARNING: DO NOT EDIT THIS FILE. This is a generated file that is synchronized
 * by MyEclipse Hibernate tool integration.
 */
public abstract class AbstractResponse 
    extends com.bbs.domain.model.BaseObject
    implements Serializable
{
    /** The cached hash code value for this instance.  Settting to 0 triggers re-calculation. */
    private int hashValue = 0;

    /** The composite primary key value. */
    private java.lang.Long id;

    /** The value of the simple content property. */
    private java.lang.String content;

    /** The value of the simple topicid property. */

    /** The value of the simple writer property. */

    /** The value of the simple createdate property. */
    private java.util.Date createdate;

    /** The value of the simple lastmodified property. */
    private java.util.Date lastmodified;

    private Topic topic;
    private User writer;
    
    /**
     * Simple constructor of AbstractResponse instances.
     */
    public AbstractResponse()
    {
    }

    /**
     * Constructor of AbstractResponse instances given a simple primary key.
     * @param id
     */
    public AbstractResponse(java.lang.Long id)
    {
        this.setId(id);
    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.Long
     */
    public java.lang.Long getId()
    {
        return id;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param id
     */
    public void setId(java.lang.Long id)
    {
        this.hashValue = 0;
        this.id = id;
    }

    /**
     * Return the value of the content column.
     * @return java.lang.String
     */
    public java.lang.String getContent()
    {
        return this.content;
    }

    /**
     * Set the value of the content column.
     * @param content
     */
    public void setContent(java.lang.String content)
    {
        this.content = content;
    }

    /**
     * Return the value of the topicid column.
     * @return java.lang.Long
     */
   
    /**
     * Return the value of the createDate column.
     * @return java.util.Date
     */
    public java.util.Date getCreatedate()
    {
        return this.createdate;
    }

    /**
     * Set the value of the createDate column.
     * @param createdate
     */
    public void setCreatedate(java.util.Date createdate)
    {
        this.createdate = createdate;
    }

    /**
     * Return the value of the lastmodified column.
     * @return java.util.Date
     */
    public java.util.Date getLastmodified()
    {
        return this.lastmodified;
    }

    /**
     * Set the value of the lastmodified column.
     * @param lastmodified
     */
    public void setLastmodified(java.util.Date lastmodified)
    {
        this.lastmodified = lastmodified;
    }

    /**
     * Implementation of the equals comparison on the basis of equality of the primary key values.
     * @param rhs
     * @return boolean
     */
    public boolean equals(Object rhs)
    {
        if (rhs == null)
            return false;
        if (! (rhs instanceof Response))
            return false;
        Response that = (Response) rhs;
        if (this.getId() == null || that.getId() == null)
            return false;
        return (this.getId().equals(that.getId()));
    }

    /**
     * Implementation of the hashCode method conforming to the Bloch pattern with
     * the exception of array properties (these are very unlikely primary key types).
     * @return int
     */
    public int hashCode()
    {
        if (this.hashValue == 0)
        {
            int result = 17;
            int idValue = this.getId() == null ? 0 : this.getId().hashCode();
            result = result * 37 + idValue;
            this.hashValue = result;
        }
        return this.hashValue;
    }

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}
}