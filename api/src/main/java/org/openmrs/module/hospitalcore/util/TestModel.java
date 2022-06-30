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

package org.openmrs.module.hospitalcore.util;

import java.util.Comparator;
//ghanshyam 04/07/2012 New Requirement #274
public class TestModel implements Comparator<TestModel>, Comparable<TestModel>{

	private String startDate;
	private String patientIdentifier;
	private String patientName;
	private String gender;
	private String testName;
	private Integer orderId;
	private String status;
	private Integer testId;
	private Integer givenFormId;
	private Integer notGivenFormId;
	private String acceptedDate;
	private String investigation;
	private Integer givenEncounterId;
	private Integer notGivenEncounterId;
	private Boolean xray;
	private String age;
	//ghanshyam 04/07/2012 New Requirement #274
	public TestModel(){
	   }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPatientIdentifier() {
		return patientIdentifier;
	}

	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getGivenFormId() {
		return givenFormId;
	}

	public void setGivenFormId(Integer formId) {
		this.givenFormId = formId;
	}

	public String getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public Integer getGivenEncounterId() {
		return givenEncounterId;
	}

	public void setGivenEncounterId(Integer encounterId) {
		this.givenEncounterId = encounterId;
	}

	public Integer getNotGivenFormId() {
		return notGivenFormId;
	}

	public void setNotGivenFormId(Integer notGivenFormId) {
		this.notGivenFormId = notGivenFormId;
	}

	public Integer getNotGivenEncounterId() {
		return notGivenEncounterId;
	}

	public void setNotGivenEncounterId(Integer notGivenEncounterId) {
		this.notGivenEncounterId = notGivenEncounterId;
	}

	public Boolean getXray() {
		return xray;
	}

	public void setXray(Boolean xray) {
		this.xray = xray;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	//ghanshyam 04/07/2012 New Requirement #274
	
	 // Overriding the compareTo method
	   public int compareTo(TestModel t){
	      return (this.patientName).compareTo(t.patientName);
	   }
	   
	   // Overriding the compare method
	public int compare(TestModel t, TestModel t1) {
		return 0;
	}

	   }
