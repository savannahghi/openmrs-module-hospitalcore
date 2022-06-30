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
import java.util.Date;


/**
 *
 */
public class Company implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer companyId;

    private String name;
    
    private String address;
    
    private String description;
    
    private String phone;
    
    private Date createdDate;
    
    private Boolean retired = false;
    
    private Date retiredDate;

	
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	
    public String getAddress() {
    	return address;
    }

	
    public void setAddress(String address) {
    	this.address = address;
    }

	
    public String getDescription() {
    	return description;
    }

	
    public void setDescription(String description) {
    	this.description = description;
    }

	
    public String getPhone() {
    	return phone;
    }

	
    public void setPhone(String phone) {
    	this.phone = phone;
    }


	
    public Integer getCompanyId() {
    	return companyId;
    }


	
    public void setCompanyId(Integer companyId) {
    	this.companyId = companyId;
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
