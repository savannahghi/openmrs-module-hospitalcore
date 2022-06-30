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

package org.openmrs.module.hospitalcore;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;
import org.springframework.transaction.annotation.Transactional;

/**
*
*/
@Transactional
public interface DmsCommonService extends OpenmrsService {

	public ConceptName getOpdWardNameByConceptId(Concept con)
			throws APIException;

	public List<DmsOpdUnit> getOpdActivatedIdList();
}
