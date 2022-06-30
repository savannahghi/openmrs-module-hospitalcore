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

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class IndoorPatientServiceBill implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer indoorPatientServiceBillId;

    private Patient patient;

    private User creator;

    private BigDecimal amount;

    private BigDecimal actualAmount;

    private Date createdDate;

    private Set<IndoorPatientServiceBillItem> billItems;

    private Encounter encounter;

    private Integer selectedCategory;


    public Integer getIndoorPatientServiceBillId() {
        return indoorPatientServiceBillId;
    }

    public void setIndoorPatientServiceBillId(Integer indoorPatientServiceBillId) {
        this.indoorPatientServiceBillId = indoorPatientServiceBillId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void addBillItem(IndoorPatientServiceBillItem item) {
        if (billItems == null)
            billItems = new HashSet<IndoorPatientServiceBillItem>();
        billItems.add(item);
    }

    public Set<IndoorPatientServiceBillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(Set<IndoorPatientServiceBillItem> billItems) {
        this.billItems = billItems;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    /**
     * @return the selectedCategory
     */
    public Integer getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    public void setSelectedCategory(Integer selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

}
