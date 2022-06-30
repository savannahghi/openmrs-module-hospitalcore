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
import org.openmrs.PersonAttribute;
import org.openmrs.api.context.Context;

public class RelativeNameMatcher implements Matcher<Patient> {
	private String[] parts;

	public RelativeNameMatcher(String relativeName) {
		relativeName = relativeName.toLowerCase();
		relativeName = relativeName.replaceAll("'", " ");
		parts = relativeName.split(" ");
	}

	public boolean matches(Object obj) {
		Patient patient = (Patient) obj;
		PersonAttribute pat = patient.getAttribute(Context.getPersonService()
				.getPersonAttributeTypeByName("Father/Husband Name"));
		String relativeName = pat.getValue().toLowerCase();
		for (String part : parts) {
			if (relativeName.contains(part))
				return true;
		}
		return false;
	}
}
