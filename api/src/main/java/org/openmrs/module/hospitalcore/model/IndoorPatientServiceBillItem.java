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
import java.util.Date;

import org.openmrs.Order;

/**
 *
 */
public class IndoorPatientServiceBillItem implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private Integer indoorPatientServiceBillItemId;
	
	private BillableService service;
	
	private IndoorPatientServiceBill indoorPatientServiceBill;
	
	private BigDecimal unitPrice;
	
	private BigDecimal amount;
	
	private BigDecimal actualAmount;
	
	private Integer quantity;
	
	private String name;
	
	private Date createdDate;
	
	private Order order ;
        
        private Boolean voided = false;
        
        private Date voidedDate;
        
        private String voidedby;
	
	private String orderType;
	public Integer getIndoorPatientServiceBillItemId() {
		return indoorPatientServiceBillItemId;
	}

	public void setIndoorPatientServiceBillItemId(
			Integer indoorPatientServiceBillItemId) {
		this.indoorPatientServiceBillItemId = indoorPatientServiceBillItemId;
	}
	
	public BillableService getService() {
		return service;
	}

	public void setService(BillableService service) {
		this.service = service;
	}
	
	public IndoorPatientServiceBill getIndoorPatientServiceBill() {
		return indoorPatientServiceBill;
	}

	public void setIndoorPatientServiceBill(
			IndoorPatientServiceBill indoorPatientServiceBill) {
		this.indoorPatientServiceBill = indoorPatientServiceBill;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
        
        public Boolean getVoided() {
		return voided;
	}
	
	public void setVoided(Boolean voided) {
		this.voided = voided;
	}
	
	public Date getVoidedDate() {
		return voidedDate;
	}
	
	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}
        
        public String getVoidedby() {
		return voidedby;
	}
	
	public void setVoidedby(String voidedby) {
		this.voidedby = voidedby;
	}
}
