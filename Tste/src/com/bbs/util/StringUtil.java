package com.bbs.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;
import java.util.StringTokenizer;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * String Utility Class This is used to encode passwords programmatically
 *
 * <p>
 * <a h
 * ref="StringUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StringUtil {
    //~ Static fields/initializers =============================================

    private final static Log log = LogFactory.getLog(StringUtil.class);

    //~ Methods ================================================================

    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials
     * string is returned
     *
     * @param password Password or other credentials to use in authenticating
     *        this username
     * @param algorithm Algorithm used to do the digest
     *
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("Exception: " + e);

            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords
     * as cookies.
     *
     * This is weak encoding in that anyone can use the decodeString
     * routine to reverse the encoding.
     *
     * @param str
     * @return String
     */
    public static String encodeString(String str)  {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     * @return String
     */
    public static String decodeString(String str) {
        sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
        try {
            return new String(dec.decodeBuffer(str));
        } catch (IOException io) {
        	throw new RuntimeException(io.getMessage(), io.getCause());
        }
    }
    
    public static String truncate(String source, int len, String delim) {
		if (source == null) return "";
		int start, stop , byteLen;
		int alen = source.getBytes().length;
		//len = len / 2;
		if (len > 0) {
			if (alen <= len) return source;
			start = stop = byteLen = 0; //TODO effizienter
			while ( byteLen <= len){
				if ( source.substring(stop,stop+1).getBytes().length == 1){
					byteLen += 1 ;
				}else{
					byteLen += 2 ;
				}
				stop++;
			}
			StringBuffer sb = new StringBuffer(source.substring(start, stop-1));
			if (alen > len) sb.append(delim);
			return sb.toString();
		}
		return source;
	}
    
    public static String truncate( String str ){
    	return truncate(str , 20 , "...");
    }
    
    public static String clob2String(java.sql.Clob clob){
    	java.io.Reader in = null ;
    	try{
    		in = clob.getCharacterStream();
    	}catch(Exception e){
    		
    	}
    	if (in == null) return null;
    	StringBuffer sb = new StringBuffer(2048);
    	try {
    	int i = in.read();
    	while (i != -1) {
    	sb.append((char) i);
    	i = in.read();
    	}
    	in.close();
    	} catch (Exception e) {
    	}
    	return sb.toString();

    }
    
    /**
	 * Encodes a string for inserting to database table
	 *
	 * @param inputString input string
	 * @return encoded string
	 */
	public static String escape(String inputString) {
        if (inputString == null)
            return null;

		StringBuffer buf = new StringBuffer((int) (inputString.length() * 1.1));
		int stringLength = inputString.length();

		for ( int i = 0; i < stringLength; ++i ) {
			char c = inputString.charAt(i);

			switch ( c ) {
			/*
				case 0: /* Must be escaped for 'mysql' 
					buf.append('\\');
					buf.append('0');

					break;
					
				case '\n':  Must be escaped for logs 
					buf.append('\\');
					buf.append('n');

					break;

				case '\r':
					buf.append('\\');
					buf.append('r');

					break;
					*/

				case '\'':
					buf.append("&#39;");
					//buf.append('\'');

					break;

				

				default:
					buf.append(c);
			}
		}
		return buf.toString();
	}
	
	public static String escape1(String inputString) {
        if (inputString == null)
            return null;

		StringBuffer buf = new StringBuffer((int) (inputString.length() * 1.1));
		int stringLength = inputString.length();

		for ( int i = 0; i < stringLength; ++i ) {
			char c = inputString.charAt(i);

			switch ( c ) {
			/*
				case 0: /* Must be escaped for 'mysql' 
					buf.append('\\');
					buf.append('0');

					break;
					
				case '\n':  Must be escaped for logs 
					buf.append('\\');
					buf.append('n');

					break;

				case '\r':
					buf.append('\\');
					buf.append('r');

					break;
					*/
			case '\\':
				buf.append('\\');
				buf.append('\\');

				break;
			case '\'':
					buf.append("&#39;");
					//buf.append('\'');

					break;

				

				default:
					buf.append(c);
			}
		}
		return buf.toString();
	}

	/**
	 * Replaces all HTML-sensitive characters with their entity equivalents
	 * @param html String to encode
	 * @return string with all HTML-sensitive characters replaced with their entity equivalents
	 */
	public static String htmlEncode(String html) {
		if ( html == null ) {
			return null;
		}
		StringBuffer buf = new StringBuffer((int) (html.length() * 1.1));
		int stringLength = html.length();

		for ( int i = 0; i < stringLength; ++i ) {
			char c = html.charAt(i);

			switch ( c ) {
				case '\'':
					buf.append("&#39;");

					break;

				case '"':
					buf.append("&quot;");

					break;

				case '<':
					buf.append("&lt;");

					break;

				case '>':
					buf.append("&gt;");

					break;

				case '&':
					buf.append("&amp;");
				case ' ':
					buf.append("&nbsp;");
				case '\r':
					buf.append("<br>");
				case '\n':
					buf.append("<br>");
					break;
				default:
					buf.append(c);
			}
		}
		return buf.toString();
	}
	
	public static String htmlDeEncode(String html) {
		if ( html == null ) {
			return null;
		}
		StringBuffer buf = new StringBuffer((int) (html.length() * 1.1));
		int stringLength = html.length();

		for ( int i = 0; i < stringLength; ++i ) {
			char c = html.charAt(i);

			switch ( c ) {
				case '\'':
					buf.append("&#39;");

					break;

				case '"':
					buf.append("&quot;");

					break;

				case '<':
					buf.append("&lt;");

					break;

				case '>':
					buf.append("&gt;");

					break;

				case '&':
					buf.append("&amp;");
				case ' ':
					buf.append("&nbsp;");
				case '\r':
					buf.append("<br>");
				case '\n':
					buf.append("<br>");
					break;
				default:
					buf.append(c);
			}
		}
		return buf.toString();
	}
	
	public static String filter(String value) { 

		if (value == null) 
		return "&nbsp;"; 

		StringBuffer result = new StringBuffer(); 
		for (int i = 0; i < value.length(); i++) { 
		char ch = value.charAt(i); 
		if (ch == '<') 
		result.append("&lt;"); 
		else if (ch == '>') 
		result.append("&gt;"); 
		else if (ch == '&') 
		result.append("&amp;"); 
		else if (ch == '"') 
		result.append("&quot;"); 
		else if (ch == '\r') 
		result.append("<BR>"); 
		else if (ch == '\n') { 
		if (i > 0 && value.charAt(i - 1) == '\r') { 

		} else { 
		result.append("<BR>"); 
		} 
		} else if (ch == '\t') 
		result.append("&nbsp;&nbsp;&nbsp;&nbsp"); 
		else if (ch == ' ') 
		result.append("&nbsp;"); 
		else 
		result.append(ch); 
		} 
		return (result.toString()); 
		} 


	/**
	 * Replaces all HTML-sensitive characters with their entity equivalents in all string values in specified model
	 * @param model Map of pairs <code>variable -&gt; value</code>.
	 * @return Map with encoded string values
	 */
	public static Map htmlEncodeModel(Map model) {
		Map result = new HashMap(model.size());
		for ( Iterator i = model.entrySet().iterator(); i.hasNext(); ) {
			Map.Entry entry = (Map.Entry) i.next();
			String key = (String) entry.getKey();
			Object value = (Object) entry.getValue();
			if ( value instanceof String ) {
				result.put(key, htmlEncode((String) value));
			} else {
				result.put(key, value);
			}
		}
		return result;
	}

    /**
     * Replaces all EL (end of line) characters with HTML BR tag
     *
     * @param html
     * @return string with
     */
    public static String elToBr(String html) {
        if ( html == null ) {
            return null;
        }
        StringBuffer buf = new StringBuffer((int) (html.length() * 1.1));
        int stringLength = html.length();

        for ( int i = 0; i < stringLength; ++i ) {
            char c = html.charAt(i);

            switch ( c ) {
                case '\n':
                    buf.append("<br />");

                    break;
                default:
                    buf.append(c);
            }
        }
        return buf.toString();
    }
    
    public static String autoBr(String html,String token,int len){
    	if( html == null){
    		return "&nbsp;";
    	}
    	if(html.length()<=len){
    		return html;
    	}
    	boolean bSuccess = false;
    	StringBuffer results = new StringBuffer();
    	StringTokenizer htmlToken = new StringTokenizer(html,token);
    	while(!bSuccess&&htmlToken.hasMoreElements()){
    		String str = (String)htmlToken.nextElement();
    		results.append(str);
    		results.append(".<br>");
    		if(html.length()-str.length()<len){
    			bSuccess = true;
    		}
    	}
    	
    	return results.toString();
    }
    
    public static String autoBrEmail(String html,String token,int len){
    	if( html == null){
    		return "&nbsp;";
    	}
    	if(html.length()<=len){
    		return html;
    	}
    	int index = html.indexOf("@");
    	StringBuffer results = new StringBuffer();
    	results.append(autoBr(html.substring(0,index),token,len));
    	results.append("@<br>");
    	results.append(autoBr(html.substring(index+1,html.length()),token,len));
    	
    	return results.toString();
    }
}
