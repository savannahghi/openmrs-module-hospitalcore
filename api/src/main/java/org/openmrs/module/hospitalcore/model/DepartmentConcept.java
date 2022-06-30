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



package org.openmrs.module.hospitalcore.model;

import java.io.Serializable;
import java.util.Date;

import org.openmrs.Concept;

/**
 * <p> Class: DepartmentConcept </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p> 
 * <p> Author: Nguyen manh chuyen </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 12:51:19 PM </p>
 * <p> Update date: Apr 13, 2011 12:51:19 PM </p>
 **/
public class DepartmentConcept implements Serializable {
	//ghanshyam 1-june-2013 New Requirement #1633 User must be able to send investigation orders from dashboard to billing
	public static final int[] TYPES = {1,2,3,4,5,6,7,8,9}; // 1= Diagnosis, 2=Procedure, 3=Investigation ,4=Symptom,5=Examination ,6=UnderLined Condition,7=Signs,8=Differential Diagniosis,9=Working Diagnosis
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer typeConcept;
	private Department department;
	private Concept concept;
	private Date createdOn;
	private String createdBy;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTypeConcept() {
		return typeConcept;
	}
	public void setTypeConcept(Integer typeConcept) {
		this.typeConcept = typeConcept;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
