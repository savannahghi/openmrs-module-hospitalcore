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

package org.openmrs.module.hospitalcore.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.IpdService;
import org.openmrs.module.hospitalcore.db.IpdDAO;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;
import org.openmrs.module.hospitalcore.util.HospitalCoreConstants;

public class IpdServiceImpl extends BaseOpenmrsService implements IpdService {
	public IpdServiceImpl() {
	}

	protected IpdDAO dao;

	public void setDao(IpdDAO dao) {
		this.dao = dao;
	}

	public List<IpdPatientAdmission> getAllIpdPatientAdmission()
			throws APIException {
		return dao.getAllIpdPatientAdmission();
	}

	public List<IpdPatientAdmission> getAllIndoorPatient() throws APIException {
		return dao.getAllIndoorPatient();
	}
        
        // 24/11/2014 to Work with size selctor for IPDQueue
	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog( String searchKey,int page, int pgSize)
			throws APIException {
		return dao.getAllIndoorPatientFromAdmissionLog(searchKey,page,pgSize);
	}
        
        // 24/11/2014 to Work with size selctor for IPDQueue
	public int countGetAllIndoorPatientFromAdmissionLog( String searchKey,int page)
			throws APIException {
		return dao.countGetAllIndoorPatientFromAdmissionLog(searchKey, page);
	}
        

        
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(
			Integer patientId, Integer admissionWardId, String status,
			Integer min, Integer max) throws APIException {
		// TODO Auto-generated method stub
		return dao.listIpdPatientAdmissionLog(patientId, admissionWardId,
				status, min, max);
	}

	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted()
			throws APIException {
		return dao.getAllIpdPatientAdmitted();
	}

	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog()
			throws APIException {
		return dao.getAllIpdPatientAdmittedLog();
	}

	public IpdPatientAdmission getIpdPatientAdmission(Integer id)
			throws APIException {
		return dao.getIpdPatientAdmission(id);
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id)
			throws APIException {
		return dao.getIpdPatientAdmissionLog(id);
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(
			OpdPatientQueueLog opdLog) throws APIException {
		return dao.getIpdPatientAdmissionLog(opdLog);
	}
	
	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(
			Encounter encounter) throws APIException {
		return dao.getIpdPatientAdmissionLog(encounter);
	}

	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id)
			throws APIException {
		return dao.getIpdPatientAdmitted(id);
	}

	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id)
			throws APIException {
		return dao.getIpdPatientAdmittedLog(id);
	}

	public IpdPatientAdmission saveIpdPatientAdmission(
			IpdPatientAdmission admission) throws APIException {
		return dao.saveIpdPatientAdmission(admission);
	}

	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(
			IpdPatientAdmissionLog admissionLog) throws APIException {
		return dao.saveIpdPatientAdmissionLog(admissionLog);
	}

	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted)
			throws APIException {
		return dao.saveIpdPatientAdmitted(admitted);
	}

	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(
			IpdPatientAdmittedLog admitted) throws APIException {
		return dao.saveIpdPatientAdmittedLog(admitted);
	}

	public void removeIpdPatientAdmission(IpdPatientAdmission admission)
			throws APIException {
		dao.removeIpdPatientAdmission(admission);

	}

	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted)
			throws APIException {
		dao.removeIpdPatientAdmitted(admitted);
	}

	public IpdPatientAdmitted transfer(Integer id, Integer wardId,
			Integer doctorId, String bed) throws APIException {

		IpdPatientAdmitted from = getIpdPatientAdmitted(id);
		if (from == null)
			throw new APIException("Can not found IpdPatientAdmitted with id :"
					+ id);

		Concept ward = Context.getConceptService().getConcept(wardId);
		if (ward == null)
			throw new APIException("Can not find IPD Ward with id : " + wardId);

		User user = Context.getUserService().getUser(doctorId);
		if (user == null)
			throw new APIException("Can not find Doctor with user id :"
					+ doctorId);

		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(from.getAdmittedWard());
		log.setBasicPay(from.getBasicPay());
		log.setBed(from.getBed());
		log.setBirthDate(from.getBirthDate());
		log.setCaste(from.getCaste());
		log.setFatherName(from.getFatherName());
		log.setGender(from.getGender());
		log.setIpdAdmittedUser(from.getIpdAdmittedUser());
		log.setMonthlyIncome(from.getMonthlyIncome());
		log.setPatient(from.getPatient());
		log.setPatientAdmittedLogTransferFrom(from
				.getPatientAdmittedLogTransferFrom());
		log.setPatientAddress(from.getPatientAddress());
		log.setPatientIdentifier(from.getPatientIdentifier());
		log.setPatientAdmissionLog(from.getPatientAdmissionLog());
		log.setPatientName(from.getPatientName());
		log.setUser(Context.getAuthenticatedUser());
		log.setStatus(IpdPatientAdmitted.STATUS_TRANSFER);
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			removeIpdPatientAdmitted(from);
		}

		IpdPatientAdmitted to = new IpdPatientAdmitted();
		to.setAdmissionDate(new Date());
		to.setAdmittedWard(ward);
		to.setBasicPay(from.getBasicPay());
		to.setBed(bed);
		to.setBirthDate(from.getBirthDate());
		to.setCaste(from.getCaste());
		to.setFatherName(from.getFatherName());
		to.setGender(from.getGender());
		to.setUser(Context.getAuthenticatedUser());
		to.setIpdAdmittedUser(user);
		to.setMonthlyIncome(from.getMonthlyIncome());
		to.setPatient(from.getPatient());
		to.setPatientAddress(from.getPatientAddress());
		to.setPatientIdentifier(from.getPatientIdentifier());
		to.setPatientAdmissionLog(from.getPatientAdmissionLog());
		to.setPatientName(from.getPatientName());
		to.setStatus(IpdPatientAdmitted.STATUS_ADMITTED);
		to.setPatientAdmissionLog(log.getPatientAdmissionLog());
		to.setPatientAdmittedLogTransferFrom(log);
		to = saveIpdPatientAdmitted(to);

		return to;
	}

	// ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
	public IpdPatientAdmitted transfer(Integer id, Integer wardId,
			Integer doctorId, String bed, String comments) throws APIException {

		IpdPatientAdmitted from = getIpdPatientAdmitted(id);
		if (from == null)
			throw new APIException("Can not found IpdPatientAdmitted with id :"
					+ id);

		Concept ward = Context.getConceptService().getConcept(wardId);
		if (ward == null)
			throw new APIException("Can not find IPD Ward with id : " + wardId);

		User user = Context.getUserService().getUser(doctorId);
		if (user == null)
			throw new APIException("Can not find Doctor with user id :"
					+ doctorId);

		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(from.getAdmittedWard());
		log.setBasicPay(from.getBasicPay());
		log.setBed(from.getBed());
		// ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
		log.setComments(comments);
		log.setBirthDate(from.getBirthDate());
		log.setCaste(from.getCaste());
		log.setFatherName(from.getFatherName());
		log.setGender(from.getGender());
		log.setIpdAdmittedUser(from.getIpdAdmittedUser());
		log.setMonthlyIncome(from.getMonthlyIncome());
		log.setPatient(from.getPatient());
		log.setPatientAdmittedLogTransferFrom(from
				.getPatientAdmittedLogTransferFrom());
		log.setPatientAddress(from.getPatientAddress());
		log.setPatientIdentifier(from.getPatientIdentifier());
		log.setPatientAdmissionLog(from.getPatientAdmissionLog());
		log.setPatientName(from.getPatientName());
		log.setUser(Context.getAuthenticatedUser());
		log.setStatus(IpdPatientAdmitted.STATUS_TRANSFER);
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			removeIpdPatientAdmitted(from);
		}

		IpdPatientAdmitted to = new IpdPatientAdmitted();
		to.setAdmissionDate(new Date());
		to.setAdmittedWard(ward);
		to.setBasicPay(from.getBasicPay());
		to.setBed(bed);
		// ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
		to.setComments(comments);
		to.setBirthDate(from.getBirthDate());
		to.setCaste(from.getCaste());
		to.setFatherName(from.getFatherName());
		to.setGender(from.getGender());
		to.setUser(Context.getAuthenticatedUser());
		to.setIpdAdmittedUser(user);
		to.setMonthlyIncome(from.getMonthlyIncome());
		to.setPatient(from.getPatient());
		to.setPatientAddress(from.getPatientAddress());
		to.setPatientIdentifier(from.getPatientIdentifier());
		to.setPatientAdmissionLog(from.getPatientAdmissionLog());
		to.setPatientName(from.getPatientName());
		to.setStatus(IpdPatientAdmitted.STATUS_ADMITTED);
		to.setPatientAdmissionLog(log.getPatientAdmissionLog());
		to.setPatientAdmittedLogTransferFrom(log);
		to = saveIpdPatientAdmitted(to);

		return to;
	}

	public IpdPatientAdmittedLog discharge(Integer id, Integer outComeConceptId)
			throws APIException {

		Concept outComeConcept = Context.getConceptService().getConcept(
				outComeConceptId);
		IpdPatientAdmitted admitted = getIpdPatientAdmitted(id);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(admitted.getAdmittedWard());
		log.setBasicPay(admitted.getBasicPay());
		log.setBed(admitted.getBed());
		log.setBirthDate(admitted.getBirthDate());
		log.setCaste(admitted.getCaste());
		log.setFatherName(admitted.getFatherName());
		log.setUser(Context.getAuthenticatedUser());
		log.setGender(admitted.getGender());
		log.setIpdAdmittedUser(admitted.getIpdAdmittedUser());
		log.setMonthlyIncome(admitted.getMonthlyIncome());
		log.setPatient(admitted.getPatient());
		log.setPatientAddress(admitted.getPatientAddress());
		log.setPatientIdentifier(admitted.getPatientIdentifier());
		log.setPatientAdmissionLog(admitted.getPatientAdmissionLog());
		log.setPatientName(admitted.getPatientName());
		log.setPatientAdmittedLogTransferFrom(admitted
				.getPatientAdmittedLogTransferFrom());
		log.setStatus(IpdPatientAdmitted.STATUS_DISCHARGE);
		log.setAdmissionOutCome(outComeConcept.getName().getName());

		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			// CHUYEN set status of admissionLog = discharge
			IpdPatientAdmissionLog admissionLog = admitted
					.getPatientAdmissionLog();
			admissionLog.setStatus(IpdPatientAdmitted.STATUS_DISCHARGE);
			saveIpdPatientAdmissionLog(admissionLog);
			removeIpdPatientAdmitted(admitted);

			// save discharge info to encounter
			Concept conVisitOutCome = Context.getConceptService().getConcept(
					HospitalCoreConstants.CONCEPT_ADMISSION_OUTCOME);
			Location location = new Location(1);

			Encounter ipdEncounter = admissionLog.getIpdEncounter();

			Obs dischargeObs = new Obs();

			dischargeObs.setConcept(conVisitOutCome);
			dischargeObs.setValueCoded(outComeConcept);
			dischargeObs.setCreator(Context.getAuthenticatedUser());
			dischargeObs.setObsDatetime(new Date());
			dischargeObs.setLocation(location);
			dischargeObs.setDateCreated(new Date());
			dischargeObs.setPerson(ipdEncounter.getPatient());
			dischargeObs.setEncounter(ipdEncounter);
			dischargeObs = Context.getObsService().saveObs(dischargeObs,
					"update obs dischargeObs if need");
			ipdEncounter.addObs(dischargeObs);
			Context.getEncounterService().saveEncounter(ipdEncounter);

		}

		return log;
	}

	// Kesavulu loka 24/06/2013 # 1926 One text filed for otherInstructions.
	public IpdPatientAdmittedLog discharge(Integer id,
			Integer outComeConceptId, String otherInstructions)
			throws APIException {

		Concept outComeConcept = Context.getConceptService().getConcept(
				outComeConceptId);
		IpdPatientAdmitted admitted = getIpdPatientAdmitted(id);
		IpdPatientAdmittedLog log = new IpdPatientAdmittedLog();
		log.setAdmissionDate(new Date());
		log.setAdmittedWard(admitted.getAdmittedWard());
		log.setBasicPay(admitted.getBasicPay());
		log.setBed(admitted.getBed());
		// ghanshyam 11-july-2013 feedback # 1724 Introducing bed availability
		log.setComments(admitted.getComments());
		log.setBirthDate(admitted.getBirthDate());
		log.setCaste(admitted.getCaste());
		log.setFatherName(admitted.getFatherName());
		log.setUser(Context.getAuthenticatedUser());
		log.setGender(admitted.getGender());
		log.setIpdAdmittedUser(admitted.getIpdAdmittedUser());
		log.setMonthlyIncome(admitted.getMonthlyIncome());
		log.setPatient(admitted.getPatient());
		log.setPatientAddress(admitted.getPatientAddress());
		log.setPatientIdentifier(admitted.getPatientIdentifier());
		log.setPatientAdmissionLog(admitted.getPatientAdmissionLog());
		log.setPatientName(admitted.getPatientName());
		log.setPatientAdmittedLogTransferFrom(admitted
				.getPatientAdmittedLogTransferFrom());
		log.setStatus(IpdPatientAdmitted.STATUS_DISCHARGE);
		log.setAdmissionOutCome(outComeConcept.getName().getName());
		log.setOtherInstructions(otherInstructions);
		log.setChief(admitted.getChief());
		log.setSubChief(admitted.getSubChief());
		log.setReligion(admitted.getReligion());
		log.setAbscondedDate(admitted.getAbscondedDate());	
		log = saveIpdPatientAdmittedLog(log);
		if (log.getId() != null) {
			// CHUYEN set status of admissionLog = discharge
			IpdPatientAdmissionLog admissionLog = admitted
					.getPatientAdmissionLog();
			admissionLog.setStatus(IpdPatientAdmitted.STATUS_DISCHARGE);
			saveIpdPatientAdmissionLog(admissionLog);
			removeIpdPatientAdmitted(admitted);

			// save discharge info to encounter
			Concept conVisitOutCome = Context.getConceptService().getConcept(
					HospitalCoreConstants.CONCEPT_ADMISSION_OUTCOME);
			Location location = new Location(1);

			Encounter ipdEncounter = admissionLog.getIpdEncounter();

			Obs dischargeObs = new Obs();

			dischargeObs.setConcept(conVisitOutCome);
			dischargeObs.setValueCoded(outComeConcept);
			dischargeObs.setCreator(Context.getAuthenticatedUser());
			dischargeObs.setObsDatetime(new Date());
			dischargeObs.setLocation(location);
			dischargeObs.setDateCreated(new Date());
			dischargeObs.setPerson(ipdEncounter.getPatient());
			dischargeObs.setEncounter(ipdEncounter);
			dischargeObs = Context.getObsService().saveObs(dischargeObs,
					"update obs dischargeObs if need");
			ipdEncounter.addObs(dischargeObs);
			Context.getEncounterService().saveEncounter(ipdEncounter);

		}

		return log;
	}

	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(
			Integer patientId) throws APIException {
		return dao.listAdmittedLogByPatientId(patientId);
	}

	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId)
			throws APIException {
		return dao.getAdmittedByPatientId(patientId);
	}

	public IpdPatientAdmitted getAdmittedByAdmissionLogId(
			IpdPatientAdmissionLog ipdPatientAdmissionLog) throws APIException {
		return dao.getAdmittedByAdmissionLogId(ipdPatientAdmissionLog);
	}

	// ghanshyam 10-june-2013 New Requirement #1847 Capture Vital statistics for
	// admitted patient in ipd
	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(
			IpdPatientVitalStatistics vitalStatistics) throws APIException {
		return dao.saveIpdPatientVitalStatistics(vitalStatistics);
	}

	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(
			Integer patientId, Integer patientAdmissionLogId)
			throws APIException {
		return dao.getIpdPatientVitalStatistics(patientId,
				patientAdmissionLogId);
	}

	public List<Concept> getDiet() throws APIException {
		return dao.getDiet();
	}

	public void saveWardBedStrength(WardBedStrength wardBedStrength)
			throws APIException {

		dao.saveWardBedStrength(wardBedStrength);

	}
	public IpdPatientAdmission getIpdPatientAdmissionByPatient(Patient patient) throws APIException {
		return dao.getIpdPatientAdmissionByPatient(patient);
	}
	public WardBedStrength getWardBedStrengthByWardId(Integer wardId)
			throws APIException {

		return dao.getWardBedStrengthByWardId(wardId);
	}

	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(
			Encounter encounter) throws APIException {

		return dao.getIpdPatientAdmissionByEncounter(encounter);
	}

	
	public List<IpdPatientAdmission> searchIpdPatientAdmission(
			String patientSearch, ArrayList<Integer> userIds, String fromDate,
			String toDate, String wardId, String status)
			throws APIException {
		return dao.searchIpdPatientAdmission(patientSearch, userIds, fromDate,
				toDate, wardId, status);
	}
	
	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(
			String patientSearch, ArrayList<Integer> userIds, String fromDate,
			String toDate, String wardId, String status)
			throws APIException {
		return dao.searchIpdPatientAdmitted(patientSearch, userIds, fromDate,
				toDate, wardId, status);
	}
	
	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId) throws APIException {

		return dao.getIpdPatientAdmissionByPatientId(patientId);
	}
	public List<IpdPatientAdmitted> getBedAvailability(Concept wardId,String bedNo) throws APIException {
		return dao.getBedAvailability(wardId,bedNo);
	}
	
}
