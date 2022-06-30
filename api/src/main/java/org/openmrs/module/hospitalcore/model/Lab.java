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
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.OrderType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Role;

public class Lab implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 716116064672914345L;
	private Integer labId;
	private String name;
	private String description;
	private OrderType labOrderType;
	private EncounterType labTestEncounterType;
	private PatientIdentifierType patientIdentifierType;
	private Set<Concept> investigationsToDisplay;
	private Set<Concept> confidentialTestsToDisplay;
	private Integer rescheduleperiod= 7;
	private Boolean retired = false;
	private Role role;
	private Date retiredDate ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OrderType getLabOrderType() {
		return labOrderType;
	}
	public void setLabOrderType(OrderType labOrderType) {
		this.labOrderType = labOrderType;
	}
	public EncounterType getLabTestEncounterType() {
		return labTestEncounterType;
	}
	public void setLabTestEncounterType(EncounterType labTestEncounterType) {
		this.labTestEncounterType = labTestEncounterType;
	}
	public PatientIdentifierType getPatientIdentifierType() {
		return patientIdentifierType;
	}
	public void setPatientIdentifierType(PatientIdentifierType patientIdentifierType) {
		this.patientIdentifierType = patientIdentifierType;
	}
	public Set<Concept> getInvestigationsToDisplay() {
		return investigationsToDisplay;
	}
	public void setInvestigationsToDisplay(Set<Concept> investigationsToDisplay) {
		this.investigationsToDisplay = investigationsToDisplay;
	}
	public Set<Concept> getConfidentialTestsToDisplay() {
		return confidentialTestsToDisplay;
	}
	public void setConfidentialTestsToDisplay(
			Set<Concept> confidentialTestsToDisplay) {
		this.confidentialTestsToDisplay = confidentialTestsToDisplay;
	}
	public Integer getRescheduleperiod() {
		return rescheduleperiod;
	}
	public void setRescheduleperiod(Integer rescheduleperiod) {
		this.rescheduleperiod = rescheduleperiod;
	}
	public Integer getLabId() {
		return labId;
	}
	public void setLabId(Integer labId) {
		this.labId = labId;
	}
	public Boolean getRetired() {
		return retired;
	}
	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	public Date getRetiredDate() {
		return retiredDate;
	}
	public void setRetiredDate(Date retiredDate) {
		this.retiredDate = retiredDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
