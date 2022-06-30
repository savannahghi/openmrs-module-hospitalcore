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
public class TenderBill implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private Integer tenderBillId;
	
	private BigDecimal amount;
	
	private String description;
	
	private Boolean printed = false;
	
	private Date createdDate;
	
	private Boolean voided = false;
	
	private Date voidedDate;
	
	private Company company;
	
	private User creator;
	
	private Receipt receipt;
	
	private Set<TenderBillItem> billItems;
	
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
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTenderBillId() {
		return tenderBillId;
	}
	
	public void setTenderBillId(Integer tenderBillId) {
		this.tenderBillId = tenderBillId;
	}
	
	public Set<TenderBillItem> getBillItems() {
		return billItems;
	}
	
	public void setBillItems(Set<TenderBillItem> billItems) {
		this.billItems = billItems;
	}
	
	public void addBillItem(TenderBillItem billItem) {
		if (billItems == null)
			billItems = new HashSet<TenderBillItem>();
		billItems.add(billItem);
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
