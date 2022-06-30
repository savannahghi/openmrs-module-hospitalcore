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


package org.openmrs.module.hospitalcore.matcher;

import org.openmrs.Patient;

public class GenderMatcher implements Matcher<Patient> {
    private final String gender;
    public GenderMatcher(String gender) {
        this.gender = gender;
    }

    public boolean matches(Object obj) {
    	if(!gender.equalsIgnoreCase("any")){
    		if(((Patient)obj).getGender().equalsIgnoreCase(gender)){
        		return true;
        	}else{
        		return false;
        	}	
    	} else {
    		return true;
    	}
    	
    	
    }
}
