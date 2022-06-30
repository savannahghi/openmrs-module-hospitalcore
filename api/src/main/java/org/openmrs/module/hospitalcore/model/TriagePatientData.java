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

import org.openmrs.Patient;

public class TriagePatientData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Patient patient;
	private Integer id;
	private TriagePatientQueueLog triageLogId;
	private BigDecimal weight;
	private BigDecimal height;
	private BigDecimal BMI;
	private BigDecimal mua;
	private BigDecimal chest;
	private BigDecimal abdominal;
	public Patient getPatient(){
		return patient;
	}
	public void setPatient(Patient patient){
		this.patient = patient;
	}
	public BigDecimal getMua() {
		return mua;
	}

	public void setMua(BigDecimal mua) {
		this.mua = mua;
	}

	public BigDecimal getChest() {
		return chest;
	}

	public void setChest(BigDecimal chest) {
		this.chest = chest;
	}

	public BigDecimal getAbdominal() {
		return abdominal;
	}

	public void setAbdominal(BigDecimal abdominal) {
		this.abdominal = abdominal;
	}

	private BigDecimal temperature;
	private Integer systolic;
	private Integer daistolic;
	private Integer respiratoryRate;
	private Integer pulsRate;
	private String bloodGroup;
	private Date lastMenstrualDate;
	private String rhesusFactor;
	private String pitct;
	private Date createdOn;
	private Integer encounterOpd;
    private Double oxygenSaturation;

    private String  pregnancyStatus;
    private String travelHistory;

	public Integer getEncounterOpd() {
		return encounterOpd;
	}

	public void setEncounterOpd(Integer encounterOpd) {
		this.encounterOpd = encounterOpd;
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

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getDaistolic() {
		return daistolic;
	}

	public void setDaistolic(Integer daistolic) {
		this.daistolic = daistolic;
	}

	public Integer getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(Integer respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public Integer getPulsRate() {
		return pulsRate;
	}

	public void setPulsRate(Integer pulsRate) {
		this.pulsRate = pulsRate;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getLastMenstrualDate() {
		return lastMenstrualDate;
	}

	public void setLastMenstrualDate(Date lastMenstrualDate) {
		this.lastMenstrualDate = lastMenstrualDate;
	}

	public String getRhesusFactor() {
		return rhesusFactor;
	}

	public void setRhesusFactor(String rhesusFactor) {
		this.rhesusFactor = rhesusFactor;
	}

	public String getPitct() {
		return pitct;
	}

	public void setPitct(String pitct) {
		this.pitct = pitct;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public BigDecimal getBMI() {
		return BMI;
	}

	public void setBMI(BigDecimal BMI) {
		this.BMI = BMI;
	}


    public Double getOxygenSaturation() {
        return oxygenSaturation;
    }

	public String getPregnancyStatus() {
		return pregnancyStatus;
	}

	public void setPregnancyStatus(String pregnancyStatus) {
		this.pregnancyStatus = pregnancyStatus;
	}

	public String getTravelHistory() {
		return travelHistory;
	}

	public void setTravelHistory(String travelHistory) {
		this.travelHistory = travelHistory;
	}

	public void setOxygenSaturation(Double oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }
}
