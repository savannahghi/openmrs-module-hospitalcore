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


package org.openmrs.module.hospitalcore.db;

import java.util.Date;
import java.util.List;

import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;

public interface LabDAO {
	/**
	 * LAB
	 */
	public Lab saveLab(Lab lab) throws DAOException;
	
	public Lab getLabByName(String name) throws DAOException;
	
	public List<Lab> getAllLab() throws DAOException;
	
	public List<Lab> getAllActivelab() throws DAOException;
	
	public Lab getLabByRole(Role role) throws DAOException;
	
	public List<Lab> getLabByRoles(List<Role> roles) throws DAOException;
	
	public Lab getLabById(Integer labId) throws DAOException;
	
	public void deleteLab(Lab lab) throws DAOException;
	
	
	/**
	 * LAB TEST
	 */
	
	public LabTest saveLabTest(LabTest labTest) throws DAOException;
	
	public LabTest getLabTestById(Integer labTestId) throws DAOException;
	
	public LabTest getLabTestByOrder(Order order) throws DAOException;
	
	public LabTest getLabTestBySampleNumber(String sampleNumber) throws DAOException;
	
	public List<LabTest> getLatestLabTestByDate(Date today,Date nextDay, Lab lab ) throws DAOException;
	
	public void deleteLabTest(LabTest labtest) throws DAOException;
}
