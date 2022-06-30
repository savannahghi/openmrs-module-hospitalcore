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

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.EhrHospitalWaiver;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientCategoryDetails;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;

public interface HospitalCoreDAO {

	public List<Obs> listObsGroup(Integer personId, Integer conceptId, Integer min, Integer max) throws DAOException;
	public Obs getObsGroupCurrentDate(Integer personId, Integer conceptId) throws DAOException;	
	public Integer buildConcepts(List<ConceptModel> conceptModels);
	public List<Patient> searchPatient(String nameOrIdentifier,String gender, int age , int rangeAge, String date, int rangeDay,String relativeName) throws DAOException;
	public List<Patient> searchPatient(String nameOrIdentifier,String gender, int age , int rangeAge, String lastDayOfVisit,
                                       int lastVisit,String relativeName,String maritalStatus,String phoneNumber,
                                       String nationalId,String fileNumber) throws DAOException;
	
	/**
	 * Search patients
	 * @param hql
	 * @return
	 */
	public List<Patient> searchPatient(String hql);
	
	/**
	 * Get patient search result count
	 * @param hql
	 * @return
	 */
	public BigInteger getPatientSearchResultCount(String hql);
	
	/** 
	 * Get all attributes of an patient
	 * @param patientId
	 * @return
	 */
	public List<PersonAttribute> getPersonAttributes(Integer patientId);
	
	/**
	 * Get last visit encounter
	 * @param patient
	 * @param types
	 * @return
	 */
	public Encounter getLastVisitEncounter(Patient patient, List<EncounterType> types);
	
	/**
	 * Save core form
	 * @param form
	 * @return
	 */
	public CoreForm saveCoreForm(CoreForm form);

	/**
	 * Get core form by id
	 * @param id
	 * @return
	 */
	public CoreForm getCoreForm(Integer id);

	/**
	 * Get core forms by name
	 * @param conceptName
	 * @return
	 */
	public List<CoreForm> getCoreForms(String conceptName);

	/**
	 * Get all core forms
	 * @return
	 */
	public List<CoreForm> getCoreForms();

	/**
	 * Delete core form
	 * @param form
	 */
	public void deleteCoreForm(CoreForm form);
	
	/**
	 * Save patientSearch
	 * @param patientSearch
	 * @return
	 */
	public PatientSearch savePatientSearch(PatientSearch patientSearch);
	
	/**
	 * 
	 * Auto generated method comment
	 * 
	 * @param patientID
	 * @return
	 */
	public java.util.Date getLastVisitTime (Patient patientID);
	
	//ghanshyam 3-june-2013 New Requirement #1632 Orders from dashboard must be appear in billing queue.User must be able to generate bills from this queue
	public PatientSearch getPatientByPatientId(int patientId);
	public PatientSearch getPatient(int patientID);
	public List<Obs> getObsByEncounterAndConcept(Encounter encounter,Concept concept);
	public PersonAddress getPersonAddress(Person person);
	public OpdTestOrder getOpdTestOrder(Integer opdOrderId);
	public PersonAttributeType getPersonAttributeTypeByName(String attributeName) throws DAOException;
	public Obs getObs(Person person,Encounter encounter) throws DAOException;
	public String getPatientType(Patient patientId) throws DAOException;
	public List<Obs> getObsInstanceForDiagnosis(Encounter encounter,Concept concept) throws DAOException;
	public List<OpdTestOrder> getAllOpdOrdersByDateRange(boolean today,String fromDate,String toDate);
	public List<PatientServiceBillItem> getAllPatientServiceBillItemsByDate(boolean today,String fromDate,String toDate);

	public PatientCategoryDetails savePatientCategoryDetails(PatientCategoryDetails patientCategoryDetails) throws DAOException;
	public PatientCategoryDetails getPatientCategoryDetailsById(Integer patientDetailsId) throws DAOException;
	public PatientCategoryDetails getPatientCategoryDetailsByPatient(Patient patient) throws DAOException;
	public List<PatientCategoryDetails> getAllPatientCategoryDetails(String property, String value, Date startDate, Date endDate) throws DAOException;

	//provide several interfaces to handle departmental services
	public EhrDepartment saveDepartment(EhrDepartment ehrDepartment) throws DAOException;
	public EhrDepartment getDepartmentById(Integer departmentId) throws DAOException;
	public List<EhrDepartment> getAllDepartment() throws DAOException;
	public EhrDepartment getDepartmentByName(String departmentName) throws DAOException;

	//provide mechanisms to filter payments
	public List<PatientServiceBillItem> getPatientServiceBillByDepartment(EhrDepartment ehrDepartment, Date startDate, Date endDate) throws DAOException;
	public EhrHospitalWaiver saveEhrHospitalWaiver(EhrHospitalWaiver ehrHospitalWaiver) throws DAOException;
	public EhrHospitalWaiver getEhrHospitalWaiverById(Integer waiverId) throws DAOException;
	public List<EhrHospitalWaiver> getAllEhrHospitalWaiver(Date startDate, Date endDate) throws DAOException;
}
