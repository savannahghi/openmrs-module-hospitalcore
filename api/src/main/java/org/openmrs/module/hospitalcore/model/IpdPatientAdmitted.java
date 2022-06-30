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
import java.math.BigDecimal;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.module.hospitalcore.util.PatientUtils;

/**
 * <p> Class: IpdPatientAdmitted </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 17, 2011 12:46:16 PM </p>
 * <p> Update date: Mar 17, 2011 12:46:16 PM </p>
 **/
public class IpdPatientAdmitted implements Serializable{
	public final static String STATUS[] ={"transfer", "discharge from","discharge to","call"};
	public static final String STATUS_TRANSFER = "transfer";
	public static final String STATUS_DISCHARGE= "discharge";
	public static final String STATUS_CALL = "call";
	public static final String STATUS_ADMITTED = "admitted";
	
	public static final String OUTCOME_IMPROVED = "improved";
	public static final String OUTCOME_CURED = "cured";
	public static final String OUTCOME_ON_REQUEST = "discharge on request";
	public static final String OUTCOME_LAMA = "lama";
	public static final String OUTCOME_ABSCONDING = "absconding";
	public static final String OUTCOME_DEATH = "death";
	
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private Date admissionDate;
	private Patient patient; 
	private String patientIdentifier;
	private String patientName;
	private Date birthDate;
	private String patientAddress;
	private String bed;
	//ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
	private String comments;
	private String fatherName;
	// 30-6-2012 Marta - To handle relationship type #290
		private String relationshipType;	// son/daughter/wife of
	private String gender;
	private Concept admittedWard; //    :  the ipd ward concept name that patient is transferd from
	private User user; 			  // :     1 ) user who transferred  this patient
					   //2)  user discharged this patient
					   //	3) user called !
	private String  status ; //   : discharge, transfer, call (STRING ) 
	private String caste; // : String
	private BigDecimal monthlyIncome ; //:  String
	private String basicPay; // : String
	//private String bed_number : concept _id   ( N/A )
	private User ipdAdmittedUser; // User admitted in patient admission queue
	private IpdPatientAdmissionLog  patientAdmissionLog;
	private IpdPatientAdmittedLog patientAdmittedLogTransferFrom;//transfer_from_id : log   ( N/A )
	private String admissionOutCome;
	private int requestForDischargeStatus;// 0=not yet requested for discharge,1=requested for discharge;
	private int billingStatus;// 0=billing not done,1=billing done;
	private String chief;
	private String subChief;
	private String religion;
	private Integer absconded;
	private Date abscondedDate;

	public Date getAbscondedDate() {
		return abscondedDate;
	}

	public void setAbscondedDate(Date abscondedDate) {
		this.abscondedDate = abscondedDate;
	}

	public Integer getAbsconded() {
		return absconded;
	}
	public void setAbsconded(Integer absconded) {
		this.absconded = absconded;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getAge(){
		return PatientUtils.estimateAge(patient);
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(String basicPay) {
		this.basicPay = basicPay;
	}
	public IpdPatientAdmissionLog getPatientAdmissionLog() {
		return patientAdmissionLog;
	}
	public void setPatientAdmissionLog(IpdPatientAdmissionLog patientAdmissionLog) {
		this.patientAdmissionLog = patientAdmissionLog;
	}
	public String getPatientIdentifier() {
		return patientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	public User getIpdAdmittedUser() {
		return ipdAdmittedUser;
	}
	public void setIpdAdmittedUser(User ipdAdmittedUser) {
		this.ipdAdmittedUser = ipdAdmittedUser;
	}
	public Concept getAdmittedWard() {
		return admittedWard;
	}
	public void setAdmittedWard(Concept admittedWard) {
		this.admittedWard = admittedWard;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	//ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	// 30-6-2012 Marta - To handle relationship type #290
	public String getRelationshipType() {
			return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public IpdPatientAdmittedLog getPatientAdmittedLogTransferFrom() {
		return patientAdmittedLogTransferFrom;
	}
	public void setPatientAdmittedLogTransferFrom(
			IpdPatientAdmittedLog patientAdmittedLogTransferFrom) {
		this.patientAdmittedLogTransferFrom = patientAdmittedLogTransferFrom;
	}
	public String getAdmissionOutCome() {
		return admissionOutCome;
	}
	public void setAdmissionOutCome(String admissionOutCome) {
		this.admissionOutCome = admissionOutCome;
	}
	public int getRequestForDischargeStatus() {
		return requestForDischargeStatus;
	}
	public void setRequestForDischargeStatus(int requestForDischargeStatus) {
		this.requestForDischargeStatus = requestForDischargeStatus;
	}
	public int getBillingStatus() {
		return billingStatus;
	}
	public void setBillingStatus(int billingStatus) {
		this.billingStatus = billingStatus;
	}
	public String getChief() {
		return chief;
	}
	public void setChief(String chief) {
		this.chief = chief;
	}
	public String getSubChief() {
		return subChief;
	}
	public void setSubChief(String subChief) {
		this.subChief = subChief;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
}
