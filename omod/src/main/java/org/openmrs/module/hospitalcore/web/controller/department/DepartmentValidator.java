/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

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

package org.openmrs.module.hospitalcore.web.controller.department;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;
import org.openmrs.module.hospitalcore.model.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p> Class: DepartmentValidator </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.department </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 2:07:16 PM </p>
 * <p> Update date: Apr 13, 2011 2:07:16 PM </p>
 **/
public class DepartmentValidator implements Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return Department.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	Department department = (Department) command;
    	
    	if( StringUtils.isBlank(department.getName())){
    		error.reject("hospitalcore.department.name.required");
    	}
    	PatientDashboardService inventoryService = Context.getService(PatientDashboardService.class);
    	Department department2 = inventoryService.getDepartmentByName(department.getName());
    	if(department.getId() != null){
    		if(department2 != null && department2.getId().intValue() != department.getId().intValue()){
    			error.reject("hospitalcore.department.name.existed");
    		}
    	}else{
    		if(department2 != null){
    	    		error.reject("hospitalcore.department.name.existed");
    		}
    	}
    	
    	
    }
}
