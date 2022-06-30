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

import java.util.Date;

import org.openmrs.Patient;

public class IpdPatientVitalStatistics {

	private Integer id;
	private Patient patient;
	private IpdPatientAdmissionLog ipdPatientAdmissionLog;
	// vitalSatatistics Form
	private String bloodPressure;
	private String pulseRate;
	private String temperature;
	private String dietAdvised;
	private String note;
	private Integer creator;
	private Date createdOn;
	private String systolicBloodPressure;
	private String diastolicBloodPressure;

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

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog() {
		return ipdPatientAdmissionLog;
	}

	public void setIpdPatientAdmissionLog(
			IpdPatientAdmissionLog ipdPatientAdmissionLog) {
		this.ipdPatientAdmissionLog = ipdPatientAdmissionLog;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}
	public String getSystolicBloodPressure() {
		return systolicBloodPressure;
	}
	public String getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public void setSystolicBloodPressure(String systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}

	public void setDiastolicBloodPressure(String diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	public String getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(String pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDietAdvised() {
		return dietAdvised;
	}

	public void setDietAdvised(String dietAdvised) {
		this.dietAdvised = dietAdvised;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
