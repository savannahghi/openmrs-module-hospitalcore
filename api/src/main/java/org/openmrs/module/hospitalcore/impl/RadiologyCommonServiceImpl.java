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

package org.openmrs.module.hospitalcore.impl;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.RadiologyCommonService;
import org.openmrs.module.hospitalcore.db.RadiologyCommonDAO;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public class RadiologyCommonServiceImpl extends BaseOpenmrsService implements RadiologyCommonService {

	public RadiologyCommonServiceImpl() {
	}
	
	protected RadiologyCommonDAO dao;

	public void setDao(RadiologyCommonDAO dao) {
		this.dao = dao;
	}
	
	public List<RadiologyTest> getAllTest(Patient patient) throws APIException {
		return dao.getAllTest(patient);
	}
	
	public ConceptAnswer getConceptAnswer(Concept concept) throws APIException {
		return dao.getConceptAnswer(concept);
	}
	
	public List<RadiologyTest> getAllTest(Patient patient,String date) throws APIException {
		return dao.getAllTest(patient,date);
	}
	
	public List<RadiologyTest> getAllTest(Patient patient,String date,Concept concept) throws APIException {
		return dao.getAllTest(patient,date,concept);
	}
	
	public List<RadiologyTest> getAllSubTest(Patient patient,String date,Concept concept) throws APIException {
		return dao.getAllSubTest(patient,date,concept);
	}
}
