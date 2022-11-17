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

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.model.*;

/**
*
*/
public interface InventoryCommonDAO {

	public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws DAOException;
	
	public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient,String date) throws DAOException;
	
	public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws DAOException;
	
	//ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
	public InventoryDrug getDrugByName(String name) throws DAOException;

	public List<PatientRegimen> getPatientRegimen(Patient patient, String tag, Integer cycle);

	public List<Concept> getDrugFrequency() throws DAOException;
	
	public InventoryDrugFormulation getDrugFormulationById(Integer id) throws DAOException;

	List<InventoryStoreDrugPatient> getAllIssueByDateRange(String startDate, String endDate);

	public List<Regimen> getRegimens (boolean voided);


    PatientRegimen createPatientRegimen(PatientRegimen patientRegimen);

    PatientRegimen updatePatientRegimen(PatientRegimen patientRegimen);

    void voidPatientRegimen(PatientRegimen patientRegimen);

    Regimen createRegimen(Regimen regimen);
    Regimen updateRegimen(Regimen regimen);
    void voidRegimen(Regimen regimen);

    Cycle createCycle(Cycle cycle);
    Cycle updateCycle(Cycle cycle);
}
