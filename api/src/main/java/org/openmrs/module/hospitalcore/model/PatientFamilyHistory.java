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

public class PatientFamilyHistory implements Serializable {

	
	public String getSiblingStatus() {
		return siblingStatus;
	}

	public void setSiblingStatus(String siblingStatus) {
		this.siblingStatus = siblingStatus;
	}

	public String getSiblingDeathCause() {
		return siblingDeathCause;
	}

	public void setSiblingDeathCause(String siblingDeathCause) {
		this.siblingDeathCause = siblingDeathCause;
	}

	public String getSiblingDeathAge() {
		return siblingDeathAge;
	}

	public void setSiblingDeathAge(String siblingDeathAge) {
		this.siblingDeathAge = siblingDeathAge;
	}

	public String getFamilyIllnessHistory() {
		return familyIllnessHistory;
	}

	public void setFamilyIllnessHistory(String familyIllnessHistory) {
		this.familyIllnessHistory = familyIllnessHistory;
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private TriagePatientQueueLog triageLogId;
	private Integer patientId;
	private String fatherStatus;
	private String fatherDeathCause;
	private String fatherDeathAge;
	private String motherStatus;
	private String motherDeathCause;
	private String motherDeathAge;
	private String siblingStatus;
	private String siblingDeathCause;
	private String siblingDeathAge;
	private String familyIllnessHistory;

	
	private Date createdOn;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TriagePatientQueueLog getTriageLogId() {
		return triageLogId;
	}

	public void setTriageLogId(TriagePatientQueueLog triageLogId) {
		this.triageLogId = triageLogId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getFatherStatus() {
		return fatherStatus;
	}

	public void setFatherStatus(String fatherStatus) {
		this.fatherStatus = fatherStatus;
	}

	public String getFatherDeathCause() {
		return fatherDeathCause;
	}

	public void setFatherDeathCause(String fatherDeathCause) {
		this.fatherDeathCause = fatherDeathCause;
	}

	public String getFatherDeathAge() {
		return fatherDeathAge;
	}

	public void setFatherDeathAge(String fatherDeathAge) {
		this.fatherDeathAge = fatherDeathAge;
	}

	public String getMotherStatus() {
		return motherStatus;
	}

	public void setMotherStatus(String motherStatus) {
		this.motherStatus = motherStatus;
	}

	public String getMotherDeathCause() {
		return motherDeathCause;
	}

	public void setMotherDeathCause(String motherDeathCause) {
		this.motherDeathCause = motherDeathCause;
	}

	public String getMotherDeathAge() {
		return motherDeathAge;
	}

	public void setMotherDeathAge(String motherDeathAge) {
		this.motherDeathAge = motherDeathAge;
	}

	
}
