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

import org.openmrs.Concept;

public class Answer implements Serializable{
private Integer answerId;
private Question question;
private Question quest;
public Question getQuest() {
	return quest;
}
public void setQuest(Question quest) {
	this.quest = quest;
}
private Concept answerConcept;
private String freeText;
public Integer getAnswerId() {
	return answerId;
}
public void setAnswerId(Integer answerId) {
	this.answerId = answerId;
}
public Question getQuestion() {
	return question;
}
public void setQuestion(Question question) {
	this.question = question;
}
public Concept getAnswerConcept() {
	return answerConcept;
}
public void setAnswerConcept(Concept answerConcept) {
	this.answerConcept = answerConcept;
}
public String getFreeText() {
	return freeText;
}
public void setFreeText(String freeText) {
	this.freeText = freeText;
}

}
