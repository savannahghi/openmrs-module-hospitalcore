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

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.RadiologyCoreService;
import org.openmrs.module.hospitalcore.db.RadiologyCoreDAO;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;

public class RadiologyCoreServiceImpl extends BaseOpenmrsService implements
		RadiologyCoreService {	

	protected RadiologyCoreDAO dao;

	public void setDao(RadiologyCoreDAO dao) {
		this.dao = dao;
	}

	//
	// RADIOLOGY DEPARTMENT
	//
	public List<RadiologyDepartment> getAllRadiologyDepartments() {
		return dao.getAllRadiologyDepartments();
	}
}
