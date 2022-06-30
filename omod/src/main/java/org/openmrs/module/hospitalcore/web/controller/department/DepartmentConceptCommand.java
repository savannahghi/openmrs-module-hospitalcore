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

/**
 * <p> Class: DepartmentConceptCommand </p>
 * <p> Package: org.openmrs.module.hospitalcore.web.controller.department </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 4:59:18 PM </p>
 * <p> Update date: Apr 13, 2011 4:59:18 PM </p>
 **/
public class DepartmentConceptCommand {
	private Integer[] selectedDiagnosisList;
	private Integer[] selectedProcedureList;
	private Integer departmentId;
	public Integer[] getSelectedDiagnosisList() {
		return selectedDiagnosisList;
	}
	public void setSelectedDiagnosisList(Integer[] selectedDiagnosisList) {
		this.selectedDiagnosisList = selectedDiagnosisList;
	}
	public Integer[] getSelectedProcedureList() {
		return selectedProcedureList;
	}
	public void setSelectedProcedureList(Integer[] selectedProcedureList) {
		this.selectedProcedureList = selectedProcedureList;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
}
