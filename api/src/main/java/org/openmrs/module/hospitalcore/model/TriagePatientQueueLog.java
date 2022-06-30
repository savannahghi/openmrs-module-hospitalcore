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
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class TriagePatientQueueLog implements  Serializable {

	
	private static final long serialVersionUID = 1L;
	 private Integer id;
	 private Patient patient;
	 private String patientName;
	 private String patientIdentifier;
	 private Date birthDate;
	 private String sex;
	 //referal type
	 private String  referralConceptName;
	 private Concept referralConcept;
	 //Location
	 private Concept triageConcept;
	 private String triageConceptName;
	 private String status;
	 private User user;
	 private Date createdOn;
	 private Encounter encounter;
	 private String category;
	 private String visitStatus;
	 
	@Override
	public String toString() {
		return "TriagePatientQueueLog [id=" + id + ", patient=" + patient
				+ ", patientName=" + patientName + ", birthDate=" + birthDate + ", sex="
				+ sex + ", referralConceptName=" + referralConceptName
				+ ", referralConcept=" + referralConcept + ", opdConcept="
				+ triageConcept + ", triageConceptName=" + triageConceptName
				+ ", status=" + status + ", user=" + user + ", createdOn="
				+ createdOn + " ,category=" + ",visitStatus=" + visitStatus + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getReferralConceptName() {
		return referralConceptName;
	}
	public void setReferralConceptName(String referralConceptName) {
		this.referralConceptName = referralConceptName;
	}
	public Concept getReferralConcept() {
		return referralConcept;
	}
	public void setReferralConcept(Concept referralConcept) {
		this.referralConcept = referralConcept;
	}
	public Concept getTriageConcept() {
		return triageConcept;
	}
	public void setTriageConcept(Concept triageConcept) {
		this.triageConcept = triageConcept;
	}
	public String getTriageConceptName() {
		return triageConceptName;
	}
	public void setTriageConceptName(String triageConceptName) {
		this.triageConceptName = triageConceptName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVisitStatus() {
		return visitStatus;
	}
	public void setVisitStatus(String visitStatus) {
		this.visitStatus = visitStatus;
	}
	  
}
