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


/**
 *
 */
public class AmbulanceBillItem implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer ambulanceBillItemId;
    
    private String name;

    private Ambulance ambulance;
    
    private AmbulanceBill ambulanceBill;
    
    private Integer numberOfTrip;
    
    private BigDecimal amount;
    
    private Date createdDate;
	
	private Boolean voided = false;
	
	private Date voidedDate;
	
	// ghanshyam 07/07/2012 New Requirement #305: Additional details in Ambulance Bill
	
	private String patientName;
	
	//ghanshyam 1/08/2012 feedback of New Requirement #305: Additional details in Ambulance Bill changed receiptNumber from Integer to varchar
	
	private String receiptNumber;
	
    private String origin;
	 
	private String destination;

	
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	
    public Ambulance getAmbulance() {
    	return ambulance;
    }

	
    public void setAmbulance(Ambulance ambulance) {
    	this.ambulance = ambulance;
    }

	
    public AmbulanceBill getAmbulanceBill() {
    	return ambulanceBill;
    }

	
    public void setAmbulanceBill(AmbulanceBill ambulanceBill) {
    	this.ambulanceBill = ambulanceBill;
    }

	
    public Integer getNumberOfTrip() {
    	return numberOfTrip;
    }

	
    public void setNumberOfTrip(Integer numberOfTrip) {
    	this.numberOfTrip = numberOfTrip;
    }

	
    public BigDecimal getAmount() {
    	return amount;
    }

	
    public void setAmount(BigDecimal amount) {
    	this.amount = amount;
    }

	
    public Date getCreatedDate() {
    	return createdDate;
    }

	
    public void setCreatedDate(Date createdDate) {
    	this.createdDate = createdDate;
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


	
    public Integer getAmbulanceBillItemId() {
    	return ambulanceBillItemId;
    }


	
    public void setAmbulanceBillItemId(Integer ambulanceBillItemId) {
    	this.ambulanceBillItemId = ambulanceBillItemId;
    }


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getReceiptNumber() {
		return receiptNumber;
	}


	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}
    
}
