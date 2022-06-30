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

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;

public interface IpdDAO {
	
	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException;
	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws DAOException;
	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException;
	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admitted) throws DAOException;
	
	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws DAOException;
	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws DAOException;
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws DAOException;
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog opdLog) throws DAOException;
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter encounter) throws DAOException;
	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws DAOException;
	
	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws DAOException;
	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws DAOException;
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId,String status,Integer min, Integer max)
	throws DAOException;
	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws DAOException;
	public List<IpdPatientAdmission> getAllIndoorPatient() throws DAOException;
        
        // 24/11/2014 to Work with size selctor for IPDQueue
	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String searchKey,int page,int pgSize) throws DAOException;
	
        public int countGetAllIndoorPatientFromAdmissionLog(String searchKey,int page) throws DAOException;
        
	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws DAOException;
	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws DAOException;
	
	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws DAOException;
	
	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws DAOException;
	
	public IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog ipdPatientAdmissionLog) throws DAOException;

	public List<IpdPatientAdmitted> getAllIpdAdmittedPatientByWardId(Integer wardId)
			throws DAOException;
	public WardBedStrength getWardBedStrengthByWardId(Integer wardId) throws DAOException;
	public void saveWardBedStrength(WardBedStrength wardBedStrength) throws DAOException;

	
	//ghanshyam 10-june-2013 New Requirement #1847 Capture Vital statistics for admitted patient in ipd
	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics vitalStatistics) throws DAOException;
	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer patientId,Integer patientAdmissionLogId) throws DAOException;
	public List<Concept> getDiet() throws DAOException;
	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter encounter) throws DAOException;

	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate,String wardId, String status) throws DAOException;
	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate,String wardId, String status) throws DAOException;
	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId) throws DAOException;

	public IpdPatientAdmission getIpdPatientAdmissionByPatient(Patient patient) throws DAOException;
	public List<IpdPatientAdmitted> getBedAvailability(Concept wardId,String bedNo);

	
}
