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


package org.openmrs.module.hospitalcore.util;

public class PatientDashboardConstants {
	//ghanshyam 27/06/2012 tag MS_SHOULD_BE_FINAL code MODULE_ID,CONCEPT_CLASS_NAME_DIAGNOSIS,CONCEPT_CLASS_NAME_PROCEDURE
	public static final String MODULE_ID = "patientdashboard";
	public static final String CONCEPT_CLASS_NAME_SYMPTOM = "Symptom";
	public static final String CONCEPT_CLASS_NAME_SYMPTOM_FINDINGS = "Symptom/Finding";
	public static final String CONCEPT_CLASS_NAME_DIAGNOSIS = "Diagnosis";
	public static final String CONCEPT_CLASS_NAME_PROCEDURE = "Procedure";
	public static final String CONCEPT_CLASS_NAME_EXAMINATION = "EXAMINATION";
	//ghanshyam 1-june-2013 New Requirement #1633 User must be able to send investigation orders from dashboard to billing
	public static final String CONCEPT_CLASS_NAME_INVESTIGATION = "Test";
	//ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
	public static final String CONCEPT_CLASS_NAME_DRUG = "Drug";
	public static String PROPERTY_OPDWARD = MODULE_ID + ".OPDRootConcept";
	
	public static String PROPERTY_IPDWARD = MODULE_ID + ".IPDRootConcept";
	public static String PROPERTY_HOSPITAL = MODULE_ID + ".externalHospitalConcept";
    public static String PROPERTY_HISTORY_OF_PRESENT_ILLNESS = MODULE_ID + ".historyOfPresentIllness";
	public static String PROPERTY_SYMPTOM = MODULE_ID + ".symptomConcept";
	public static String PROPERTY_EXAMINATION = MODULE_ID + ".examinationConcept";
	public static String PROPERTY_PHYSICAL_EXAMINATION = MODULE_ID + ".physicalexaminationConcept";
	public static String PROPERTY_PROVISIONAL_DIAGNOSIS = MODULE_ID + ".provisionalDiagnosisConcept";
	public static String PROPERTY_POST_FOR_PROCEDURE = MODULE_ID + ".postForProcedureConcept";
	//ghanshyam 1-june-2013 New Requirement #1633 User must be able to send investigation orders from dashboard to billing
	public static String PROPERTY_FOR_INVESTIGATION = MODULE_ID + ".investigationConcept";
	//ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
	public static String PROPERTY_FOR_DRUG = MODULE_ID + ".drugConcept";
	public static String PROPERTY_INTERNAL_REFERRAL = MODULE_ID + ".internalReferralConcept";
	public static String PROPERTY_EXTERNAL_REFERRAL = MODULE_ID +  ".externalReferralConcept";
	public static String PROPERTY_VISIT_OUTCOME = MODULE_ID + ".visitOutcomeConcept";
	public static String PROPERTY_OPD_ENCOUTNER_TYPE = MODULE_ID + ".opdEncounterType";
	public static String PROPERTY_TRIAGE_ENCOUTNER_TYPE = MODULE_ID + ".triageEncounterType";
	public static String PROPERTY_LAB_ENCOUTNER_TYPE = MODULE_ID + ".labEncounterType";
	public static String PROPERTY_INIT_CONCEPT = MODULE_ID + ".initNeededConcept";
	//signs
    public static  String PROPERTY_SIGNS_CONCEPT = MODULE_ID +".signsConcept";
    //differential diagnosis
    public static String PROPERTY_DIFFERENTIAL_DIAGNOSIS= MODULE_ID +".differentialDiagnosis";
    //Working diagnosis
    public static String PROPERTY_WORKING_DIAGNOSIS= MODULE_ID +".workingDiagnosis";
	//UnderLined Condition
	public static String PROPERTY_UNDERLINED_CONDITION = MODULE_ID +".underLinedCondition";
	
}
