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

import org.openmrs.Concept;
import org.openmrs.Obs;

public class ObsUtils {

	public static String getValueAsString(Obs obs){
		
		if(obs.getConcept()!=null){
			Concept concept = obs.getConcept();
			if(concept.getDatatype().getName().equalsIgnoreCase("Text")){
				return obs.getValueText();
			} else if(concept.getDatatype().getName().equalsIgnoreCase("Numeric")){
				return obs.getValueNumeric().toString();
			} else if(concept.getDatatype().getName().equalsIgnoreCase("Coded")){
				return obs.getValueCoded().getConceptId().toString();
			}			
		}
		return null;
	}
}
