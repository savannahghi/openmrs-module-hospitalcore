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
import java.math.BigDecimal;
import java.util.Date;

public class PatientDrugHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private TriagePatientQueueLog triageLogId;
	private Integer patientId;
	private String currentMedication;
	private String medicationName;
	private String medicationPeriod;
	private String medicationReason;
	private String medicationRecord;
	private String sensitiveMedication;
	private String sensitiveMedicationName;
	private String sensitiveMedicationSymptom;
	private String invasiveContraception;
	private String invasiveContraceptionName; 
	private Date createdOn;
	
	public String getCurrentMedication() {
		return currentMedication;
	}

	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getMedicationPeriod() {
		return medicationPeriod;
	}

	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}

	public String getMedicationReason() {
		return medicationReason;
	}

	public void setMedicationReason(String medicationReason) {
		this.medicationReason = medicationReason;
	}

	public String getMedicationRecord() {
		return medicationRecord;
	}

	public void setMedicationRecord(String medicationRecord) {
		this.medicationRecord = medicationRecord;
	}

	public String getSensitiveMedication() {
		return sensitiveMedication;
	}

	public void setSensitiveMedication(String sensitiveMedication) {
		this.sensitiveMedication = sensitiveMedication;
	}

	public String getSensitiveMedicationName() {
		return sensitiveMedicationName;
	}

	public void setSensitiveMedicationName(String sensitiveMedicationName) {
		this.sensitiveMedicationName = sensitiveMedicationName;
	}

	public String getSensitiveMedicationSymptom() {
		return sensitiveMedicationSymptom;
	}

	public void setSensitiveMedicationSymptom(String sensitiveMedicationSymptom) {
		this.sensitiveMedicationSymptom = sensitiveMedicationSymptom;
	}

	public String getInvasiveContraception() {
		return invasiveContraception;
	}

	public void setInvasiveContraception(String invasiveContraception) {
		this.invasiveContraception = invasiveContraception;
	}

	public String getInvasiveContraceptionName() {
		return invasiveContraceptionName;
	}

	public void setInvasiveContraceptionName(String invasiveContraceptionName) {
		this.invasiveContraceptionName = invasiveContraceptionName;
	}

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

}
