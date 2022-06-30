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
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Concept;

public class Question implements Serializable{
private Integer questionId;
private Symptom symptom;
private Examination examination;
private Concept questionConcept;


public Integer getQuestionId() {
	return questionId;
}
public void setQuestionId(Integer questionId) {
	this.questionId = questionId;
}
public Symptom getSymptom() {
	return symptom;
}
public void setSymptom(Symptom symptom) {
	this.symptom = symptom;
}
public Concept getQuestionConcept() {
	return questionConcept;
}
public void setQuestionConcept(Concept questionConcept) {
	this.questionConcept = questionConcept;
}
public Examination getExamination() {
	return examination;
}
public void setExamination(Examination examination) {
	this.examination = examination;
}

}
