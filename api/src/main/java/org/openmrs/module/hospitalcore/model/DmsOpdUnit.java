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

import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.User;

public class DmsOpdUnit {
	private Integer id;
	private Integer unitNo;
	private Concept opdConceptId;
	private String opdWorkingDay;
	private String startTime;
	private String endTime;
	private Date unitActiveDate;
	private Date unitDeactiveDate;
	private Integer creator;
	
	// setter and getter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(Integer unitNo) {
		this.unitNo = unitNo;
	}
	public Concept getOpdConceptId() {
		return opdConceptId;
	}
	public void setOpdConceptId(Concept opdConceptId) {
		this.opdConceptId = opdConceptId;
	}
	public String getOpdWorkingDay() {
		return opdWorkingDay;
	}
	public void setOpdWorkingDay(String opdWorkingDay) {
		this.opdWorkingDay = opdWorkingDay;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Date getUnitActiveDate() {
		return unitActiveDate;
	}
	public void setUnitActiveDate(Date unitActiveDate) {
		this.unitActiveDate = unitActiveDate;
	}
	public Date getUnitDeactiveDate() {
		return unitDeactiveDate;
	}
	public void setUnitDeactiveDate(Date unitDeactiveDate) {
		this.unitDeactiveDate = unitDeactiveDate;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
}
