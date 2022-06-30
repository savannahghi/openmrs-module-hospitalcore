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
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

/**
 *
 */
public class PatientServiceBill implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private Integer patientServiceBillId;
	
	private Patient patient;
	
	private User creator;
	
	private BigDecimal amount;
	
	private BigDecimal actualAmount;
	
	private Boolean printed = false;
	
	private Boolean voided = false;
	
	private BigDecimal rebateAmount;
	
	private String categoryNumber;
	
	private String patientCategory;
	
	private Integer admittedDays;

	private String patientSubCategory;
	
	public String getPatientSubCategory() {
		return patientSubCategory;
	}

	public void setPatientSubCategory(String patientSubCategory) {
		this.patientSubCategory = patientSubCategory;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public String getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public Integer getAdmittedDays() {
		return admittedDays;
	}

	public void setAdmittedDays(Integer admittedDays) {
		this.admittedDays = admittedDays;
	}

	private Date voidedDate;
	
	private Date createdDate;
	
	private String description;
	
	private Receipt receipt;
	
	private BigDecimal waiverAmount;
	

	
	
	//ghanshyam 3-june-2013 New Requirement #1632 Orders from dashboard must be appear in billing queue.User must be able to generate bills from this queue
	private Integer freeBill;//0=paidBill,1=freeBill,2=mixedBill
	
	//ghanshyam 25-feb-2013 New Requirement #966[Billing]Add Paid Bill/Add Free Bill for Bangladesh module(added 'comment' property)
	private String comment;
	
	private String paymentMode;
	
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	private Set<PatientServiceBillItem> billItems;
	
    private Encounter encounter;
	
	private int dischargeStatus;
	
	public Integer getPatientServiceBillId() {
		return patientServiceBillId;
	}
	
	public void setPatientServiceBillId(Integer patientServiceBillId) {
		this.patientServiceBillId = patientServiceBillId;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Boolean getPrinted() {
		return printed;
	}
	
	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}
	
	public Boolean getVoided() {
		return voided;
	}
	
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	public Date getVoidedDate() {
		return voidedDate;
	}
	
	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addBillItem(PatientServiceBillItem item) {
		if (billItems == null)
			billItems = new HashSet<PatientServiceBillItem>();
		billItems.add(item);
	}
	
	public Set<PatientServiceBillItem> getBillItems() {
		return billItems;
	}
	
	public void setBillItems(Set<PatientServiceBillItem> billItems) {
		this.billItems = billItems;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public Receipt getReceipt() {
		return receipt;
	}
	
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	//ghanshyam 3-june-2013 New Requirement #1632 Orders from dashboard must be appear in billing queue.User must be able to generate bills from this queue
	public Integer getFreeBill() {
		return freeBill;
	}

	public void setFreeBill(Integer freeBill) {
		this.freeBill = freeBill;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getWaiverAmount() {
		return waiverAmount;
	}

	public void setWaiverAmount(BigDecimal waiverAmount) {
		this.waiverAmount = waiverAmount;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public int getDischargeStatus() {
		return dischargeStatus;
	}

	public void setDischargeStatus(int dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
}
