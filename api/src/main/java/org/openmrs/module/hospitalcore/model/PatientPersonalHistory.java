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

public class PatientPersonalHistory implements Serializable {

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getSmokeItem() {
		return smokeItem;
	}

	public void setSmokeItem(String smokeItem) {
		this.smokeItem = smokeItem;
	}

	public String getSmokeAverage() {
		return smokeAverage;
	}

	public void setSmokeAverage(String smokeAverage) {
		this.smokeAverage = smokeAverage;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getAlcoholItem() {
		return alcoholItem;
	}

	public void setAlcoholItem(String alcoholItem) {
		this.alcoholItem = alcoholItem;
	}

	public String getAlcoholAverage() {
		return alcoholAverage;
	}

	public void setAlcoholAverage(String alcoholAverage) {
		this.alcoholAverage = alcoholAverage;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getDrugItem() {
		return drugItem;
	}

	public void setDrugItem(String drugItem) {
		this.drugItem = drugItem;
	}

	public String getDrugAverage() {
		return drugAverage;
	}

	public void setDrugAverage(String drugAverage) {
		this.drugAverage = drugAverage;
	}

	public String getHivStatus() {
		return hivStatus;
	}

	public void setHivStatus(String hivStatus) {
		this.hivStatus = hivStatus;
	}

	public String getExposedHiv() {
		return exposedHiv;
	}

	public void setExposedHiv(String exposedHiv) {
		this.exposedHiv = exposedHiv;
	}

	public String getExposedHivFactor() {
		return exposedHivFactor;
	}

	public void setExposedHivFactor(String exposedHivFactor) {
		this.exposedHivFactor = exposedHivFactor;
	}

	public String getFamilyHelp() {
		return familyHelp;
	}

	public void setFamilyHelp(String familyHelp) {
		this.familyHelp = familyHelp;
	}

	public String getOtherHelp() {
		return otherHelp;
	}

	public void setOtherHelp(String otherHelp) {
		this.otherHelp = otherHelp;
	}

	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	private static final long serialVersionUID = 1L;
	private Integer id;
	private TriagePatientQueueLog triageLogId;
	private Integer patientId;
	private Date createdOn;
	private String smoke;
	private String smokeItem;
	private String smokeAverage;
	private String alcohol;
	private String alcoholItem;
	private String alcoholAverage;
	private String drug;
	private String drugItem;
	private String drugAverage;
	private String hivStatus;
	private String exposedHiv;
	private String exposedHivFactor;
	private String familyHelp;
	private String otherHelp;
	private String incomeSource;

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
