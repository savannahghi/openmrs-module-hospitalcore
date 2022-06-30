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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.DmsCommonService;
import org.openmrs.module.hospitalcore.db.DmsCommonDAO;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;

public class DmsCommonServiceImpl extends BaseOpenmrsService implements
		DmsCommonService {

	private Log log = LogFactory.getLog(this.getClass());

	public DmsCommonServiceImpl() {
	}

	protected DmsCommonDAO dao;

	public DmsCommonDAO getDao() {
		return dao;
	}

	public void setDao(DmsCommonDAO dao) {
		this.dao = dao;
	}

	public ConceptName getOpdWardNameByConceptId(Concept con)
			throws APIException {
		return dao.getOpdWardNameByConceptId(con);
	}

	public List<DmsOpdUnit> getOpdActivatedIdList() {
		return dao.getOpdActivatedIdList();
	}
}
