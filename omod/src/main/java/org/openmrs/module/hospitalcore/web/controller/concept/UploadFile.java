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


package org.openmrs.module.hospitalcore.web.controller.concept;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFile {
	private CommonsMultipartFile diagnosisFile;
	private CommonsMultipartFile synonymFile;
	private CommonsMultipartFile mappingFile;

	public CommonsMultipartFile getDiagnosisFile() {
		return diagnosisFile;
	}

	public void setDiagnosisFile(CommonsMultipartFile diagnosisFile) {
		this.diagnosisFile = diagnosisFile;
	}

	public CommonsMultipartFile getSynonymFile() {
		return synonymFile;
	}

	public void setSynonymFile(CommonsMultipartFile synonymFile) {
		this.synonymFile = synonymFile;
	}

	public CommonsMultipartFile getMappingFile() {
		return mappingFile;
	}

	public void setMappingFile(CommonsMultipartFile mappingFile) {
		this.mappingFile = mappingFile;
	}

}
