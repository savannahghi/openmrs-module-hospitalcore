/**
 *  Copyright 2010 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of Hospital-core module.
 *
 *  Hospital-core module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  Hospital-core module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Hospital-core module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/



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
import org.openmrs.module.hospitalcore.util.PatientUtils;

/**
 * 
 * <p> Class: IpdPatientAdmission </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:22:35 PM </p>
 * <p> Update date: Mar 17, 2011 12:22:35 PM </p>
 *
 */
public class IpdPatientAdmission implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date admissionDate;
	private Patient patient;
	private String patientName;
	private String patientIdentifier;
	private Date birthDate;
	private String gender;
	private Concept admissionWard ; //: ipd ward concept id that patient is admitted to.
	private String status ; //: String  : admitted  /  canceled
	private User opdAmittedUser;
	private OpdPatientQueueLog opdLog;
	private int acceptStatus; //0=not accepted, 1=accepted
	private int initialDepositStatus; //0=initial deposit not done, 1=initial deposit done
	private Encounter ipdEncounter;
	
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getPatientCategory()
	{
		return PatientUtils.getPatientCategory(patient);
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Concept getAdmissionWard() {
		return admissionWard;
	}
	public void setAdmissionWard(Concept admissionWard) {
		this.admissionWard = admissionWard;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OpdPatientQueueLog getOpdLog() {
		return opdLog;
	}
	public void setOpdLog(OpdPatientQueueLog opdLog) {
		this.opdLog = opdLog;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public User getOpdAmittedUser() {
		return opdAmittedUser;
	}
	public void setOpdAmittedUser(User opdAmittedUser) {
		this.opdAmittedUser = opdAmittedUser;
	}
	public String getAge(){
		return PatientUtils.estimateAge(patient);
	}
	public int getAcceptStatus() {
		return acceptStatus;
	}
	public void setAcceptStatus(int acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	public Encounter getIpdEncounter() {
		return ipdEncounter;
	}
	public void setIpdEncounter(Encounter ipdEncounter) {
		this.ipdEncounter = ipdEncounter;
	}
	public int getInitialDepositStatus() {
		return initialDepositStatus;
	}
	public void setInitialDepositStatus(int initialDepositStatus) {
		this.initialDepositStatus = initialDepositStatus;
	}
	@Override
	public String toString() {
		return "IpdPatientAdmission [id=" + id + ", admissionDate="
				+ admissionDate + ", patient=" + patient + ", patientName="
				+ patientName + ", patientIdentifier=" + patientIdentifier
				+ ", birthDate=" + birthDate + ", gender=" + gender
				+ ", admissionWard=" + admissionWard + ", status=" + status
				+ ", opdAmittedUser=" + opdAmittedUser + ", opdLog=" + opdLog
				+ ", acceptStatus=" + acceptStatus
				+ ", ipdEncounter=" + ipdEncounter + "]";
	}
	
	
	
}
