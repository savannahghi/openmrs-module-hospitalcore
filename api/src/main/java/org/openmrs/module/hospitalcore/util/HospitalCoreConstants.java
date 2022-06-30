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

/**
 * <p> Class: HospitalCoreConstants </p>
 * <p> Package: org.openmrs.module.hospitalcore.util </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Mar 24, 2011 1:41:13 PM </p>
 * <p> Update date: Mar 24, 2011 1:41:13 PM </p>
 **/
public class HospitalCoreConstants {
	public static final String MODULE_ID = "hospitalcore";
	public static final String PROPERTY_OBSGROUP = MODULE_ID + ".obsGroup";
	public static final String PROPERTY_MEDICAL_EXAMINATION = MODULE_ID + ".medicalExamination";
	public static final String PROPERTY_IPDENCOUNTER = MODULE_ID + ".ipdEncounter";
	public static final String PROPERTY_IDENTIFIER_PREFIX = MODULE_ID + ".identifier_prefix";
	public static final String PROPERTY_HOSPITAL_NAME = MODULE_ID + ".hospitalName";
	
	public static final String CONCEPT_DATATYPE_CODED = "Coded";
	public static final String CONCEPT_DATATYPE_NA = "N/A";
	public static final String CONCEPT_CLASS_QUESTION = "Question";
	public static final String CONCEPT_CLASS_MISC = "Misc";
	public static final String CONCEPT_ADMISSION_OUTCOME = "Admission outcome";
	
	public static final Integer LABTEST_STATUS_NEW = 0;
	public static final Integer LABTEST_STATUS_ACCEPTED = 1;
	public static final Integer LABTEST_STATUS_PRINTED = 2;
	public static final Integer NEXT_OF_KIN_PERSON_ATTRIBUTE_ID = 8;
}
