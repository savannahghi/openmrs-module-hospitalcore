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

public class Tender implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int tenderId;
	private int number;
	private String name;
	private String description;
	private Date openingDate;
	private Date closingDate;
	private Date createdDate;
	private Boolean retired=false;
	private Date retiredDate ;
	private BigDecimal price ;
	public int getTenderId() {
		return tenderId;
	}
	public void setTenderId(int tenderId) {
		this.tenderId = tenderId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
    public BigDecimal getPrice() {
    	return price;
    }
    public void setPrice(BigDecimal price) {
    	this.price = price;
    }
	
    public Date getCreatedDate() {
    	return createdDate;
    }
	
    public void setCreatedDate(Date createdDate) {
    	this.createdDate = createdDate;
    }
	
    public Boolean getRetired() {
    	return retired;
    }
	
    public void setRetired(Boolean retired) {
    	this.retired = retired;
    }
	
    public Date getRetiredDate() {
    	return retiredDate;
    }
	
    public void setRetiredDate(Date retiredDate) {
    	this.retiredDate = retiredDate;
    }
}
