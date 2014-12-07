package com.bbs.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import org.hibernate.Hibernate;

import com.bbs.util.StringUtil;
import java.sql.Clob;

public class ClobConvertor implements Converter {

	public Object convert(Class type, Object value) {
		// TODO Auto-generated method stub
		if (value == null) {
            return null;
        }else if(type==String.class ){
        	return clob2String(type , value);
        }else if(type==java.sql.Clob.class){
        	return string2Clob(type , value);
        }
		throw new ConversionException("Could not convert " +
                value.getClass().getName() + " to " +
                type.getName());
	}
	
	protected Object clob2String(Class type,Object value){
		if ( value==null){
			return null;
		}
		if(value instanceof Clob){
			return StringUtil.clob2String((Clob)value);
		}
		return value.toString();
	}
	
	protected Object string2Clob(Class type,Object value){
		if(value instanceof String){
			if (StringUtils.isEmpty(value.toString())) {
                return null;
            }
			return Hibernate.createClob((String)value);
		}
		 throw new ConversionException("Could not convert " +
                 value.getClass().getName() + " to " +
                 type.getName());
	}

}
