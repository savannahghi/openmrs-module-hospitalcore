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
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Drug;
import org.openmrs.module.hospitalcore.util.ActionValue;

public class InventoryDrug  implements  Serializable {

	 private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private InventoryDrugUnit unit;
	  private InventoryDrugCategory category;
	  private Set<InventoryDrugFormulation> formulations;
	  private Date createdOn;
	  private String createdBy;
	  private Drug drugCore;
	  private int attribute;
	  private Integer reorderQty;
	  private Integer consumption;

	public Integer getVoided() {
		return voided;
	}

	public void setVoided(Integer voided) {
		this.voided = voided;
	}

	private Integer voided;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getNameShort() {
		return StringUtils.isNotBlank(name) && name.length() > 30 ?name.substring(0,30)+"..." : name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InventoryDrugUnit getUnit() {
		return unit;
	}
	public void setUnit(InventoryDrugUnit unit) {
		this.unit = unit;
	}
	public InventoryDrugCategory getCategory() {
		return category;
	}
	public void setCategory(InventoryDrugCategory category) {
		this.category = category;
	}
	public Set<InventoryDrugFormulation> getFormulations() {
		return formulations;
	}
	public void setFormulations(Set<InventoryDrugFormulation> formulations) {
		this.formulations = formulations;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getAttribute() {
		return attribute;
	}
	public String getAttributeName(){
		return ActionValue.getDrugAttribute(attribute);
	}
	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}
	public Integer getConsumption() {
		return consumption;
	}
	public void setConsumption(Integer consumption) {
		this.consumption = consumption;
	}
	public Drug getDrugCore() {
		return drugCore;
	}
	public void setDrugCore(Drug drugCore) {
		this.drugCore = drugCore;
	}
	public Integer getReorderQty() {
		return reorderQty;
	}
	public void setReorderQty(Integer reorderQty) {
		this.reorderQty = reorderQty;
	}
	//03/07/2012: Kesavulu:sort Item Names  #300
	public int compareTo(InventoryDrug drug) {
	    
	    return (this.name).compareTo(drug.name);
    }
	  
	  
	
	  
	  
	  
}
