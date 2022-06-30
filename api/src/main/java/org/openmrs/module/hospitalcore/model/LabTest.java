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
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.User;

public class LabTest implements Serializable {
	
	private static final long serialVersionUID = 552808832594082704L;

	private Integer labTestId;
	private Lab lab;
	private Date acceptDate;
	private String sampleNumber;
	private Order order;
	private int labTestStatus;
	private Patient patient;
	private Concept concept;
	private User creator;
	private String status;
	private Encounter encounter;

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getSampleNumber() {
		return sampleNumber;
	}

	public void setSampleNumber(String sampleNumber) {
		this.sampleNumber = sampleNumber;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getLabTestId() {
		return labTestId;
	}

	public void setLabTestId(Integer labTestId) {
		this.labTestId = labTestId;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public int getLabTestStatus() {
		return labTestStatus;
	}

	public void setLabTestStatus(int labTestStatus) {
		this.labTestStatus = labTestStatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}	
}
