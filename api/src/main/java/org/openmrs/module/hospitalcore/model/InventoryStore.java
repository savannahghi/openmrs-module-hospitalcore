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

import org.openmrs.Role;

public class InventoryStore implements  Serializable {

	
	 private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private Date createdOn;
	  private String createdBy;
	  private Role role;
	  private Boolean retired = false;
	  private String code;
	  private int isDrug;
	 // private InventoryStore parent;
	  private Set<InventoryStore> parentStores;
	  private Set<InventoryStore> subStores;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Boolean getRetired() {
		return retired;
	}

	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Set<InventoryStore> getParentStores() {
		return parentStores;
	}
	public void setParentStores(Set<InventoryStore> parentStores) {
		this.parentStores = parentStores;
	}
	public Set<InventoryStore> getSubStores() {
		return subStores;
	}
	public void setSubStores(Set<InventoryStore> subStores) {
		this.subStores = subStores;
	}
	public int getIsDrug() {
		return isDrug;
	}
	public String getIsDrugName() {
		
		return isDrug == 1? "Yes" : "No";
	}
	public void setIsDrug(int isDrug) {
		this.isDrug = isDrug;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	  
	  
	  
}
