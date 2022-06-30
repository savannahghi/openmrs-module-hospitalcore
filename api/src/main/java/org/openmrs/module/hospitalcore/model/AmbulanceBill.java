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

import org.openmrs.User;

/**
 *
 */
public class AmbulanceBill implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private Integer ambulanceBillId;
	
	private Ambulance ambulance;
	
	private String name;
	
	private Driver driver;
	
	private String description;
	
	private BigDecimal amount;
	
	private Date createdDate;
	
	private Boolean voided = false;
	
	private Boolean printed = false;
	
	private Date voidedDate;
	
	private User creator;
	
	private Integer numberOfTrip;
	
	private Set<AmbulanceBillItem> billItems;
	
	private Receipt receipt;
	
	public Ambulance getAmbulance() {
		return ambulance;
	}
	
	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	
	public Integer getNumberOfTrip() {
		return numberOfTrip;
	}
	
	public void setNumberOfTrip(Integer numberOfTrip) {
		this.numberOfTrip = numberOfTrip;
	}
	
	public Integer getAmbulanceBillId() {
		return ambulanceBillId;
	}
	
	public void setAmbulanceBillId(Integer ambulanceBillId) {
		this.ambulanceBillId = ambulanceBillId;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addBillItem(AmbulanceBillItem billItem) {
		if (billItems == null)
			billItems = new HashSet<AmbulanceBillItem>();
		billItems.add(billItem);
	}
	
	public Set<AmbulanceBillItem> getBillItems() {
		return billItems;
	}
	
	public void setBillItems(Set<AmbulanceBillItem> billItems) {
		this.billItems = billItems;
	}

	
    public Boolean getPrinted() {
    	return printed;
    }

	
    public void setPrinted(Boolean printed) {
    	this.printed = printed;
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
}
