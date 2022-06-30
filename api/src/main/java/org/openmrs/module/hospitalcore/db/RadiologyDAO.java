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

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;

public interface RadiologyDAO {

	/**
	 * Save radiology form
	 * 
	 * @param form
	 * @return
	 */
	public RadiologyForm saveRadiologyForm(RadiologyForm form);

	/**
	 * Get radiology form by id
	 * 
	 * @param id
	 * @return
	 */
	public RadiologyForm getRadiologyFormById(Integer id);

	/**
	 * Get radiology form be concept name
	 * 
	 * @param conceptName
	 * @return
	 */
	public List<RadiologyForm> getRadiologyForms(String conceptName);

	/**
	 * Get all radiology form
	 * 
	 * @return
	 */
	public List<RadiologyForm> getAllRadiologyForms();

	/**
	 * Delete radiology form
	 * 
	 * @param form
	 */
	public void deleteRadiologyForm(RadiologyForm form);

	/**
	 * Save radiology department
	 * 
	 * @param department
	 * @return
	 */
	public RadiologyDepartment saveRadiologyDepartment(
			RadiologyDepartment department);

	/**
	 * Get radiology department by id
	 * 
	 * @param id
	 * @return
	 */
	public RadiologyDepartment getRadiologyDepartmentById(Integer id);

	/**
	 * Delete a radiology department
	 * 
	 * @param department
	 */
	public void deleteRadiologyDepartment(RadiologyDepartment department);

	/**
	 * Get radiology department by role
	 * 
	 * @param role
	 * @return
	 */
	public RadiologyDepartment getRadiologyDepartmentByRole(Role role);

	/**
	 * Find orders
	 * 
	 * @param orderStartDate
	 * @param orderType
	 * @param tests
	 * @param patients
	 * @param page
	 * @param pageSize
	 *            TODO
	 * @return
	 * @throws ParseException
	 */
	public List<Order> getOrders(Date orderStartDate, OrderType orderType,
			Set<Concept> tests, List<Patient> patients, int page, int pageSize)
			throws ParseException;

	/**
	 * Count the number of found orders
	 * 
	 * @param orderStartDate
	 * @param orderType
	 * @param tests
	 * @param patients
	 *
	 * @return
	 * @throws ParseException
	 */
	public Integer countOrders(Date orderStartDate, OrderType orderType,
			Set<Concept> tests, List<Patient> patients) throws ParseException;

	/**
	 * Save radiology test
	 * 
	 * @param test
	 * @return
	 */
	public RadiologyTest saveRadiologyTest(RadiologyTest test);

	/**
	 * Get radiology test by id
	 * 
	 * @param id
	 * @return
	 */
	public RadiologyTest getRadiologyTestById(Integer id);

	/**
	 * Delete a radiology test *
	 * 
	 * @param test
	 */
	public void deleteRadiologyTest(RadiologyTest test);

	/**
	 * Get all radiology tests
	 * 
	 * @return
	 */
	public List<RadiologyTest> getAllRadiologyTests();

	/**
	 * Get radiology test by order
	 * 
	 * @param order
	 * @return
	 */
	public RadiologyTest getRadiologyTestByOrder(Order order);

	/**
	 * Get radiology tests by date and status
	 * 
	 * @param date
	 * @param status
	 * @return
	 * @throws ParseException
	 */
	public List<RadiologyTest> getRadiologyTestsByDateAndStatus(Date date,
			String status) throws ParseException;

	/**
	 * Get radiology tests
	 * 
	 * @param date
	 * @param status
	 * @param concepts
	 * @param page
	 *            TODO
	 * @param pageSize
	 *            TODO
	 * @return
	 * @throws ParseException
	 */
	public List<RadiologyTest> getRadiologyTests(Date date, String status,
			Set<Concept> concepts, List<Patient> patients, int page,
			int pageSize) throws ParseException;

	/**
	 * Get radiology tets by date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public abstract List<RadiologyTest> getRadiologyTestsByDate(Date date)
			throws ParseException;

	/**
	 * Get radiology tests by discontinued date
	 * 
	 * @param date
	 * @param concepts 
	 * @param patients 
	 * @param page 
	 * @param pageSize
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<RadiologyTest> getRadiologyTestsByDiscontinuedDate(Date date,
			Set<Concept> concepts, List<Patient> patients, int page,
			int pageSize) throws ParseException;

	/**
	 * Count radiology tests by discontinued date
	 * @param date
	 * @param concepts
	 * @param patients
	 * @return
	 * @throws ParseException
	 */
	public Integer countRadiologyTestsByDiscontinuedDate(Date date,
			Set<Concept> concepts, List<Patient> patients)
			throws ParseException;

	/**
	 * Get radiology test by date and patient
	 * 
	 * @param date
	 * @param patient
	 * @return
	 * @throws ParseException
	 */
	public List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date date,
			Patient patient) throws ParseException;
	
	/**
	 * Get radiology test by encounter
	 * @param encounter
	 * @return
	 */
	public RadiologyTest getRadiologyTest(Encounter encounter);

	/**
	 * Create concepts for xray default form
	 */
	public void createConceptsForXrayDefaultForm();

	/**
	 * 
	 * @param date
	 * @param status
	 * @param concepts
	 * @param patients
	 * @return
	 * @throws ParseException
	 */
	public Integer countRadiologyTests(Date date, String status,
			Set<Concept> concepts, List<Patient> patients)
			throws ParseException;
	
	/**
	 * Get Radiology Template by id
	 * @param id
	 * @return
	 */
	public RadiologyTemplate getRadiologyTemplate(Integer id);
	
	/**
	 * Save Radiology Template
	 * @param template
	 * @return
	 */
	public RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate template);
	
	/**
	 * Get all Radiology templates
	 * @return
	 */
	public List<RadiologyTemplate> getAllRadiologyTemplates();
	
	/**
	 * Delete radiology template
	 * @param template
	 */
	public void deleteRadiologyTemplate(RadiologyTemplate template);
	
	/**
	 * get Radiology Template by concept
	 * @param concept
	 * @return
	 */
	public List<RadiologyTemplate> getRadiologyTemplates(Concept concept);

	public List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient patient);
}
