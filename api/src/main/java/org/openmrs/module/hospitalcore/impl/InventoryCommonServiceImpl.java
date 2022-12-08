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

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.InventoryCommonService;
import org.openmrs.module.hospitalcore.db.InventoryCommonDAO;
import org.openmrs.module.hospitalcore.model.*;

/**
 *
 */
public class InventoryCommonServiceImpl extends BaseOpenmrsService implements InventoryCommonService {

    public InventoryCommonServiceImpl() {
    }

    protected InventoryCommonDAO dao;

    public void setDao(InventoryCommonDAO dao) {
        this.dao = dao;
    }

    public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(Patient patient) throws APIException {
        return dao.getAllIssueDateByPatientId(patient);
    }

    public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(Patient patient, String date) throws APIException {
        return dao.getDeatilOfInventoryStoreDrugPatient(patient, date);
    }

    public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(InventoryStoreDrugPatient isdpd) throws APIException {
        return dao.getDrugDetailOfPatient(isdpd);
    }

    //ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
    public InventoryDrug getDrugByName(String name) throws APIException {
        return dao.getDrugByName(name);
    }

    public List<Concept> getDrugFrequency() throws APIException {
        return dao.getDrugFrequency();
    }

    public List<Concept> getDrugRoute() throws APIException{
        return dao.getDrugRoute();
    }

    public InventoryDrugFormulation getDrugFormulationById(Integer id) throws APIException {
        return dao.getDrugFormulationById(id);
    }

    @Override
    public PatientRegimen createPatientRegimen(PatientRegimen patientRegimen) throws APIException {
        return dao.createPatientRegimen(patientRegimen);
    }

    @Override
    public PatientRegimen updatePatientRegimen(PatientRegimen patientRegimen) throws APIException {
        return dao.updatePatientRegimen(patientRegimen);
    }

    @Override
    public void voidPatientRegimen(PatientRegimen patientRegimen) throws APIException {
        dao.voidPatientRegimen(patientRegimen);
    }

    @Override
    public List<PatientRegimen> getPatientRegimen(String tag, Cycle cycle, boolean voided) {
        return dao.getPatientRegimen(tag, cycle, voided);
    }

    @Override
    public PatientRegimen getPatientRegimenById(Integer id) {
        return dao.getPatientRegimenById(id);
    }

    @Override
    public PatientRegimen getPatientRegimenByUuid(String uuid) {
        return dao.getPatientRegimenByUuid(uuid);
    }

    @Override
    public Regimen createRegimen(Regimen regimen) throws APIException {
        return dao.createRegimen(regimen);
    }

    @Override
    public Regimen updateRegimen(Regimen regimen) throws APIException {
        return dao.updateRegimen(regimen);
    }

    @Override
    public List<Regimen> getRegimens(Patient patient, RegimenType regimenType, boolean voided) {

        return dao.getRegimens(patient, regimenType, voided);
    }

    @Override
    public Regimen getRegimenById(Integer regimenId) {
        return dao.getRegimenById(regimenId);
    }

    @Override
    public void voidRegimen(Regimen regimen) throws APIException {
        dao.voidRegimen(regimen);

    }

    @Override
    public void createCycle(Cycle cycle) throws APIException {
        dao.createCycle(cycle);

    }

    @Override
    public void updateCycle(Cycle cycle) throws APIException {
        dao.updateCycle(cycle);

    }

    @Override
    public List<Cycle> getCycles(boolean voided) {
        return dao.getCycles(voided);
    }

    @Override
    public void voidCycle(Cycle cycle) throws APIException {
        dao.voidCycle(cycle);
    }

    @Override
    public Cycle getCycleById(Integer cycleId) {
        return dao.getCycleById(cycleId);
    }

    @Override
    public Cycle getCycleByUuid(String uuid) {
        return dao.getCycleByUuid(uuid);
    }


    @Override
    public List<InventoryStoreDrugPatient> getAllIssueByDateRange(String startDate, String endDate) throws APIException {
        return dao.getAllIssueByDateRange(startDate, endDate);
    }

    @Override
    public List<RegimenType> getRegimenTypes(boolean voided) {
        return dao.getRegimenTypes(voided);
    }

    @Override
    public void voidRegimenType(RegimenType regimenType) throws APIException {
        dao.voidRegimenType(regimenType);
    }

    @Override
    public RegimenType createRegimenType(RegimenType regimenType) {
        return dao.createRegimenType(regimenType);
    }

    @Override
    public RegimenType getRegimenTypeById(Integer id) {
        return dao.getRegimenTypeById(id);
    }

    @Override
    public RegimenType updateRegimenType(RegimenType regimenType) {
        return dao.updateRegimenType(regimenType);
    }


}
