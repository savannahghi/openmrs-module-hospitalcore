/**
 * Copyright 2013 Society for Health Information Systems Programmes, India (HISP India)
 * /*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * <p>
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * <p>
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.hospitalcore;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.hospitalcore.model.*;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional
public interface InventoryCommonService extends OpenmrsService {

    public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws APIException;

    public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient, String date) throws APIException;

    public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws APIException;

    //ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
    public InventoryDrug getDrugByName(String name) throws APIException;

    public List<Concept> getDrugFrequency() throws APIException;

    public InventoryDrugFormulation getDrugFormulationById(Integer id) throws APIException;
    //patientRegimen
    public PatientRegimen createPatientRegimen(PatientRegimen patientRegimen) throws APIException;

    public PatientRegimen updatePatientRegimen(PatientRegimen patientRegimen) throws APIException;

    public void voidPatientRegimen(PatientRegimen patientRegimen) throws APIException;

    List<PatientRegimen> getPatientRegimen(String tag, Cycle cycle, boolean voided);

    //regimen
    public Regimen createRegimen(Regimen regimen) throws APIException;

    public Regimen updateRegimen(Regimen regimen) throws APIException;

    List<Regimen> getRegimens(Patient patient, RegimenType regimenType, boolean voided);

    public void voidRegimen(Regimen regimen) throws APIException;

    //cycle
    public void createCycle(Cycle cycle) throws APIException;

    public void updateCycle(Cycle cycle) throws APIException;

    List<Cycle> getCycles(boolean voided);

    void voidCycle(Cycle cycle) throws APIException;

    /*
    Fetch a given cycle by its ID
     */
    Cycle getCycleById(Integer cycleId);

    //get all issueings(orders whose fee has been paid for at cashpoint) on a  given date
    public List<InventoryStoreDrugPatient> getAllIssueByDateRange(String startDate, String endDate) throws APIException;
    //get transactionDetails for each of the iss apiuings done => getDrugDetailOfPatient


    //    Regimen Type
    List<RegimenType> getRegimenTypes(boolean voided);

    void voidRegimenType(RegimenType regimenType) throws APIException;

    RegimenType createRegimenType(RegimenType regimenType);
    RegimenType getRegimenTypeById(Integer id);

    RegimenType updateRegimenType(RegimenType regimenType);

}
