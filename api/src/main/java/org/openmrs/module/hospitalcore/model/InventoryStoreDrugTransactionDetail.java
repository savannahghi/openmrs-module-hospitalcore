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
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.openmrs.Concept;
import org.openmrs.Encounter;

/**
 * <p> Class: InventoryStoreDrugTransaction </p>
 * <p> Package: org.openmrs.module.inventory.model </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Jan 5, 2011 1:28:02 PM </p>
 * <p> Update date: Jan 5, 2011 1:28:02 PM </p>
 **/
public class InventoryStoreDrugTransactionDetail implements  Serializable , Comparable<InventoryStoreDrugTransactionDetail>{

	 private static final long serialVersionUID = 1L;
	 private Integer id;
	 private InventoryStoreDrugTransaction transaction;
	 private InventoryDrug drug;
	 private InventoryDrugFormulation formulation;
	 private Integer quantity ;
	 private Integer currentQuantity ;
	 private Integer issueQuantity;
	 private BigDecimal unitPrice;
	 private BigDecimal totalPrice;
	 private BigDecimal VAT;
	 private BigDecimal costToPatient;
	 
	 private String batchNo;
	 private String companyName ;
	 private Date dateManufacture;
	 private Date dateExpiry;
	 private Date createdOn;
	 private String receiptFrom;	 
	 private long openingBalance;
	 private long closingBalance;
	 private String attribute;
	 private Integer reorderPoint;
	 private String patientType;
	 private Encounter encounter;
	 
	 private Concept frequency;
	 private Integer noOfDays;
	 private String comments;
	 private Integer flag;
	 
	 public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}


	 
	 public Integer getReorderPoint() {
		return reorderPoint;
	}

	public void setReorderPoint(Integer reorderPoint) {
		this.reorderPoint = reorderPoint;
	}



	private InventoryStoreDrugTransactionDetail parent;
	 private Set<InventoryStoreDrugTransactionDetail> subDetails;
	 
	 private Date receiptDate;
	 
	 
    public InventoryStoreDrugTransactionDetail() {
	 
    }
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public InventoryStoreDrugTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(InventoryStoreDrugTransaction transaction) {
		this.transaction = transaction;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public BigDecimal getCostToPatient() {
		return costToPatient;
	}

	public void setCostToPatient(BigDecimal costToPatient) {
		this.costToPatient = costToPatient;
	}

	public BigDecimal getVAT() {
		return VAT;
	}
	public void setVAT(BigDecimal vAT) {
		VAT = vAT;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyNameShort() {
		return StringUtils.isNotBlank(companyName) && companyName.length() > 10 ?companyName.substring(0, 7)+"..." : companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
//  Sagar Bele : Date - 22-01-2013 Issue Number 660 : [Inventory] Add receipt from field in Table and front end	
	public String getReceiptFrom() {
		return receiptFrom;
	}
	public void setReceiptFrom(String receiptFrom) {
		this.receiptFrom = receiptFrom;
	}

	public Date getDateManufacture() {
		return dateManufacture;
	}
	public void setDateManufacture(Date dateManufacture) {
		this.dateManufacture = dateManufacture;
	}
	public Date getDateExpiry() {
		return dateExpiry;
	}
	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(Integer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public InventoryDrug getDrug() {
		return drug;
	}
	public void setDrug(InventoryDrug drug) {
		this.drug = drug;
	}
	public InventoryDrugFormulation getFormulation() {
		return formulation;
	}
	public void setFormulation(InventoryDrugFormulation formulation) {
		this.formulation = formulation;
	}
	public InventoryStoreDrugTransactionDetail getParent() {
		return parent;
	}
	public void setParent(InventoryStoreDrugTransactionDetail parent) {
		this.parent = parent;
	}
	public Set<InventoryStoreDrugTransactionDetail> getSubDetails() {
		return subDetails;
	}
	public void setSubDetails(Set<InventoryStoreDrugTransactionDetail> subDetails) {
		this.subDetails = subDetails;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public long getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}
	public long getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(long closingBalance) {
		this.closingBalance = closingBalance;
	}
	public Integer getIssueQuantity() {
		return issueQuantity;
	}
	public void setIssueQuantity(Integer issueQuantity) {
		this.issueQuantity = issueQuantity;
	}
	public int getExpiryLessThan3Month(){
		Date currentDate = new Date();
		Date date3Month = DateUtils.addMonths(currentDate, 3);
		if(this.getTransaction().getTypeTransaction() == 1 && this.getCurrentQuantity() > 0 && this.dateExpiry.before(date3Month)){
			return 1;
		}
		return 0;
	}
	//03/07/2012: Kesavulu:sort Item Names  #300
	//10/7/2012: harsh #300 : deleted unused method
	 public int compareTo(InventoryStoreDrugTransactionDetail i) {
	 
	    return (this.drug).compareTo(i.drug);
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Concept getFrequency() {
		return frequency;
	}

	public void setFrequency(Concept frequency) {
		this.frequency = frequency;
	}

	public Integer getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	 
	
	 
}