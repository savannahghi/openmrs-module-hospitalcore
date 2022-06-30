/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.hospitalcore.util;

import org.apache.commons.lang.StringUtils;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;

public class GlobalPropertyUtil {
	
	/**
	 * Get boolean value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(String globalPropertyName, Boolean defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		Boolean result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			result = Boolean.parseBoolean(value);
		}
		return result;
	}
	
	/**
	 * Get String value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String globalPropertyName, String defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		String result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			result = value;
		}
		return result;
	}
	
	/**
	 * Get Integer value from a specific global property. Unless the global property is found, the defaultValue will be returned. 
	 * @param globalPropertyName
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInteger(String globalPropertyName, Integer defaultValue){
		String value = Context.getAdministrationService().getGlobalProperty(
				globalPropertyName);
		
		Integer result = defaultValue;
		
		if (!StringUtils.isBlank(value)) {
			try {
				result = Integer.parseInt(value);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * Set string value for a specific global property
	 * @param globalPropertyName
	 * @param value
	 */
	public static void setString(String globalPropertyName, String value){		
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(globalPropertyName);		
		if(gp!=null){			
			gp.setPropertyValue(value);
			Context.getAdministrationService().saveGlobalProperty(gp);			
		}
	}
	
	/**
	 * Save a new global property unless it exists
	 * 
	 * @param name
	 * @param description
	 * @param value
	 */
	public static void saveGlobalProperty(String name, String description,
			Object value) {
		GlobalProperty gp = Context.getAdministrationService()
				.getGlobalPropertyObject(name);
		if (gp == null) {
			gp = new GlobalProperty();
		}
		gp.setProperty(name);
		gp.setDescription(description);
		gp.setPropertyValue(value.toString());
		Context.getAdministrationService().saveGlobalProperty(gp);
	}
}
