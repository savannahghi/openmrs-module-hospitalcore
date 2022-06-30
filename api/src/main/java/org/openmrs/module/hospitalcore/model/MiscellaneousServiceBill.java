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

public class MiscellaneousServiceBill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String liableName;
	private String description;
	private BigDecimal amount;
	private Date createdDate;
	private Integer creator;
	private Boolean voided = false;
	private Date voidedDate;
	private Boolean printed = false;
	private MiscellaneousService service;
	
	private Integer quantity;
	
	private Receipt receipt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLiableName() {
		return liableName;
	}

	public void setLiableName(String liableName) {
		this.liableName = liableName;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
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

	public MiscellaneousService getService() {
		return service;
	}

	public void setService(MiscellaneousService service) {
		this.service = service;
	}

	public Boolean getPrinted() {
		return printed;
	}

	public void setPrinted(Boolean printed) {
		this.printed = printed;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	
    public Integer getQuantity() {
    	return quantity;
    }

	
    public void setQuantity(Integer quantity) {
    	this.quantity = quantity;
    }
	
}
