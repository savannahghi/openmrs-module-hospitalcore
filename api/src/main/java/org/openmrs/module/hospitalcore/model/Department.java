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

import org.openmrs.Concept;

/**
 * <p> Class: Department </p>
 * <p> Package: org.openmrs.module.hospitalcore.model </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Apr 13, 2011 12:30:44 PM </p>
 * <p> Update date: Apr 13, 2011 12:30:44 PM </p>
 **/
public class Department implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date createdOn;
	private String createdBy;
	private Set<Concept> wards;
	private Boolean retired =false;
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
	public Set<Concept> getWards() {
		return wards;
	}
	public void setWards(Set<Concept> wards) {
		this.wards = wards;
	}
	public Boolean getRetired() {
		return retired;
	}
	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	
	
}
