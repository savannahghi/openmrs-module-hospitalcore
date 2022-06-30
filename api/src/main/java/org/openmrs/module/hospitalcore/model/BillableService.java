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

import org.openmrs.Concept;
import org.openmrs.module.hospitalcore.util.Money;


/**
 *
 */

public class BillableService implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private Integer serviceId;
    
    private String name;
    
    private String shortName;
    
    private BigDecimal price;
    
    private Integer conceptId;
    
    private Boolean disable = false;
    
    private Concept category;
	
    public Integer getServiceId() {
    	return serviceId;
    }

	
    public void setServiceId(Integer serviceId) {
    	this.serviceId = serviceId;
    }

	
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	
    public String getShortName() {
    	return shortName;
    }

	
    public void setShortName(String shortName) {
    	this.shortName = shortName;
    }

	
    public BigDecimal getPrice() {
    	return price;
    }

    public String getTextPrice(){
    	return new Money(this.price).toString();
    }
	
    public void setPrice(BigDecimal price) {
    	this.price = price;
    }

	
    public Integer getConceptId() {
    	return conceptId;
    }

	
    public void setConceptId(Integer conceptId) {
    	this.conceptId = conceptId;
    }

	
    public Boolean getDisable() {
    	return disable;
    }

	
    public void setDisable(Boolean disable) {
    	this.disable = disable;
    }


	public Concept getCategory() {
		return category;
	}


	public void setCategory(Concept category) {
		this.category = category;
	}
}
