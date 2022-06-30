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

import org.openmrs.Concept;
import org.openmrs.User;


/**
 * 
 */
public class WardBedStrength implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer wardBedStrengthId;
    private Concept ward;
    private Integer bedStrength;
    private Date createdOn;
    private User createdBy;
    
    
    public Integer getWardBedStrengthId() {
		return wardBedStrengthId;
	}
	public void setWardBedStrengthId(Integer wardBedStrengthId) {
		this.wardBedStrengthId = wardBedStrengthId;
	}
	public Concept getWard() {
		return ward;
	}
	public void setWard(Concept ward) {
		this.ward = ward;
	}
	public Integer getBedStrength() {
		return bedStrength;
	}
	public void setBedStrength(Integer bedStrength) {
		this.bedStrength = bedStrength;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	
    
  }
