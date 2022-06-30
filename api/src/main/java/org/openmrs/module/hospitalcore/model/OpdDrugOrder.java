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
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

public class OpdDrugOrder {
	private static final long serialVersionUID = 1L;
	private Integer opdDrugOrderId;
	private Patient patient;
	private Encounter encounter;
	private InventoryDrug inventoryDrug;
	private InventoryDrugFormulation inventoryDrugFormulation;
	private Concept frequency;
	private Integer noOfDays;
	private String comments;
	private String dosage;
	private Concept dosageUnit;
	private User creator;
	private Date createdOn;
	private int orderStatus; //0=drug order from opd not yet processed,1=drug order from opd processed 
	private int cancelStatus; //0=not yet canceled,1=canceled
	private String referralWardName;
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Concept getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(Concept dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public Integer getOpdDrugOrderId() {
		return opdDrugOrderId;
	}
	public void setOpdDrugOrderId(Integer opdDrugOrderId) {
		this.opdDrugOrderId = opdDrugOrderId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public InventoryDrug getInventoryDrug() {
		return inventoryDrug;
	}
	public void setInventoryDrug(InventoryDrug inventoryDrug) {
		this.inventoryDrug = inventoryDrug;
	}
	public InventoryDrugFormulation getInventoryDrugFormulation() {
		return inventoryDrugFormulation;
	}
	public void setInventoryDrugFormulation(
			InventoryDrugFormulation inventoryDrugFormulation) {
		this.inventoryDrugFormulation = inventoryDrugFormulation;
	}
	public Concept getFrequency() {
		return frequency;
	}
	public void setFrequency(Concept frequency) {
		this.frequency = frequency;
	}
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(int cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getReferralWardName() {
		return referralWardName;
	}
	public void setReferralWardName(String referralWardName) {
		this.referralWardName = referralWardName;
	}
}
