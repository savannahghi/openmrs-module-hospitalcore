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
public class TenderBillItem implements Serializable{

	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer tenderBillItemId;
    
    private String name;

	private BigDecimal amount;
	
	private BigDecimal unitPrice;
	
	private int quantity;
    
    private Date createdDate;
    
    private Boolean voided=false;
    
    private Date voidedDate;
    
    private Tender tender;
    
    private TenderBill tenderBill;

	
    public BigDecimal getAmount() {
    	return amount;
    }

	
    public void setAmount(BigDecimal amount) {
    	this.amount = amount;
    }

	
    public BigDecimal getUnitPrice() {
    	return unitPrice;
    }

	
    public void setUnitPrice(BigDecimal unitPrice) {
    	this.unitPrice = unitPrice;
    }

	
    public int getQuantity() {
    	return quantity;
    }

	
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
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

	
    public Tender getTender() {
    	return tender;
    }

	
    public void setTender(Tender tender) {
    	this.tender = tender;
    }

	
    public TenderBill getTenderBill() {
    	return tenderBill;
    }

	
    public void setTenderBill(TenderBill tenderBill) {
    	this.tenderBill = tenderBill;
    }


	
    public Integer getTenderBillItemId() {
    	return tenderBillItemId;
    }


	
    public void setTenderBillItemId(Integer tenderBillItemId) {
    	this.tenderBillItemId = tenderBillItemId;
    }


	
    public String getName() {
    	return name;
    }


	
    public void setName(String name) {
    	this.name = name;
    }

}
