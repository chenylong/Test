/*
 * @(#)CodeHelper.java $Revision$ $Date$
 *
 * $Id$
 *
 * OLAT - Online Learning and Training
 *
 * This software is protected by the OLAT software license.
 * Use is subject to license terms.
 * See LICENSE.TXT in this distribution for details.
 *
 * Copyright (c) 2003 OLAT Zentrum, University of Zurich, Switzerland
 * All rights reserved.
 */
package com.bbs.util;



/**
 * enclosing_type Description:<br>
 * 
 *
 * @version $Revision$ $Date$
 * @author Felix Jost
 */
public class CodeHelper {

	static long timeuniqueId;
	static long ramid;

	static {
		ramid = 100;
		timeuniqueId = System.currentTimeMillis() * 64;
	}
	
	/**
	 * PLEASE use only if REALLY needed.<br />
	 * 
	 * Best effort is taken to make it "globally unique" without any persisting media by instantiating it by System.currentTimeMilis * 64, so that
	 * after a restart of the vm, the counter advances 64000 units per second which should be enough that that value is
	 * never exceeded by the usage of that ID (100 concurrent users which can consume 640 unique id per each second, and: even if exceeded, after a restart of a vm
	 * (assumed time at least 10secs), a loss of 10*64000 = 640000 can be caught up
	 * <br />
	 * <br />
	 * if you just need a counter which is unique within the virtual machine, 
	 * but does not need to be unique if 
	 * the sessions are persisted and the vm is restarted, then use @see getRAMUniqueID()
	 * 
	 * returns a unique id; even if the system is restarted.
	 * 
	 * @return long
	 */
	public static long getForeverUniqueID() {
		return timeuniqueId++;
	}
	
	/**
	 * a simple counter which is garanteed to be unique ONLY within one instance of a virtual machine.
	 * Best effort is taken to make it "globally unique" by instantiating it by System.currentTimeMilis * 64, so that
	 * after a restart of the vm, the counter advances 64000 units per second which should be enough that that value is
	 * never exceeded by the usage of that ID
	 * 
	 * @return RAM unique ID
	 */
	public static long getRAMUniqueID() {
		return ramid++;
	}

}
