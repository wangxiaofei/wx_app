/**
 * ThreadAttributes.java
 * com.miaozhen.jasmine.utils
 *
 *
 *   ver     date        author
 * ──────────────────────────────────
 *      2012-9-14   xihe
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
*/

package com.app.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:ThreadAttributes
 * Function: TODO ADD FUNCTION
 * Reason:  TODO ADD REASON
 *
 * @author   xihe
 * @version 
 * @since    Ver 1.1
 * @Date  2012-9-14  上午10:30:28
 *
 */
public class ThreadAttributes {
	private static ThreadLocal<Map<String,Object>> threadAttribues = new ThreadLocal<Map<String,Object>>(){
		protected synchronized Map<String,Object> initialValue(){
			return new HashMap<String, Object>();
		}
	};
	
	public static Object getThreadAttribute(String name){
		return threadAttribues.get().get(name);
	}
	
	public static void setThreadAttribute(String name, Object value){
		threadAttribues.get().put(name, value);
	}
}

