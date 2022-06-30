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
import org.openmrs.Encounter;
import org.openmrs.User;

public class Symptom implements Serializable{
private Integer symptomId;
private Encounter encounter;
private Concept symptomConcept;
private Date createdDate;
private User creator;
public Integer getSymptomId() {
	return symptomId;
}
public void setSymptomId(Integer symptomId) {
	this.symptomId = symptomId;
}
public Encounter getEncounter() {
	return encounter;
}
public void setEncounter(Encounter encounter) {
	this.encounter = encounter;
}
public Concept getSymptomConcept() {
	return symptomConcept;
}
public void setSymptomConcept(Concept symptomConcept) {
	this.symptomConcept = symptomConcept;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public User getCreator() {
	return creator;
}
public void setCreator(User creator) {
	this.creator = creator;
}

}
