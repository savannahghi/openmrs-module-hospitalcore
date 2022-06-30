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


package org.openmrs.module.hospitalcore;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=false)
public interface IpdService extends OpenmrsService{
	
	public IpdPatientAdmission saveIpdPatientAdmission(IpdPatientAdmission admission) throws APIException;
	
	public void removeIpdPatientAdmission(IpdPatientAdmission admission) throws APIException;
	
	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(IpdPatientAdmissionLog admissionLog) throws APIException;
	
	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException;
	
	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted) throws APIException;
	
	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(IpdPatientAdmittedLog admitted) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(OpdPatientQueueLog opdLog) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter encounter) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmission getIpdPatientAdmission(Integer id) throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(Integer patientId, Integer admissionWardId,String status,Integer min, Integer max)
			throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmission> getAllIpdPatientAdmission() throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmission> getAllIndoorPatient() throws APIException;
	
        // 24/11/2014 to Work with size selctor for IPDQueue
	@Transactional(readOnly = true)
	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(String searchKey,int page,int pgSize) throws APIException;
	
        @Transactional(readOnly = true)
	public int countGetAllIndoorPatientFromAdmissionLog(String searchKey,int page) throws APIException;
        
	public IpdPatientAdmitted transfer(Integer id, Integer wardId, Integer doctorId, String bed) throws APIException;
	
	//ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
	public IpdPatientAdmitted transfer(Integer id, Integer wardId, Integer doctorId, String bed,String comments) throws APIException;
	
	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId) throws APIException;
	
	// Kesavulu loka 24/06/2013 # 1926 One text filed for otherInstructions.
	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId, String otherInstructions) throws APIException;
	
	@Transactional(readOnly = true)
	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(Integer patientId) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmitted getAdmittedByAdmissionLogId(IpdPatientAdmissionLog ipdPatientAdmissionLog) throws APIException;
	
	public void saveWardBedStrength(WardBedStrength wardBedStrength) throws APIException;
	public WardBedStrength getWardBedStrengthByWardId(Integer wardId) throws APIException;

	//ghanshyam 10-june-2013 New Requirement #1847 Capture Vital statistics for admitted patient in ipd
	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(IpdPatientVitalStatistics vitalStatistics) throws APIException;
	public List<Concept> getDiet() throws APIException;
	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(Integer patientId,Integer patientAdmissionLogId) throws APIException;
	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(Encounter encounter) throws APIException;

	@Transactional(readOnly = true)
	public List<IpdPatientAdmission> searchIpdPatientAdmission(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException;
	@Transactional(readOnly = true)
	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(String patientSearch, ArrayList<Integer> userIds, String fromDate, String toDate, String wardId, String status) throws APIException;
	
	@Transactional(readOnly = true)
	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId) throws APIException;

	@Transactional(readOnly = true)
	public IpdPatientAdmission getIpdPatientAdmissionByPatient(Patient patient) throws APIException;

	public List<IpdPatientAdmitted> getBedAvailability(Concept wardId,String bedNo);


}
