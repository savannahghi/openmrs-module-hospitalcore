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

public class RadiologyConstants {
	
	public static final String MODULE_ID = "radiology";
	public static final String PROPERTY_PREFIX = MODULE_ID + ".form.prefix";
	public static final String TEST_STATUS_ACCEPTED = "accepted";
	public static final String TEST_STATUS_COMPLETED = "completed";
	public static final String UNACCEPT_TEST_RETURN_STATUS_SUCCESS = "success";
	public static final String UNACCEPT_TEST_RETURN_STATUS_NOT_FOUND = "not found";
	public static final String UNACCEPT_TEST_RETURN_STATUS_PRINTED = "printed";
	public static final String RESCHEDULE_TEST_RETURN_STATUS_SUCCESS = "success";
	public static final String RESCHEDULE_TEST_RETURN_STATUS_PRINTED = "printed";
	public static final String RESCHEDULE_TEST_RETURN_STATUS_ENTERED = "entered";
	public static final String COMPLETE_TEST_RETURN_STATUS_SUCCESS = "success";
	public static final String COMPLETE_TEST_RETURN_STATUS_NOT_ACCEPTED = "not accepted";
	public static final String PROPERTY_PRINTWORKLIST_PRINTALLTEST = MODULE_ID + ".printWorkList.printAllTest";
	public static final String PROPERTY_PRINTWORKLIST_TESTOPTION_DISPLAY = MODULE_ID + ".printWorkList.testOption.display";
	public static final String PROPERTY_PRINTWORKLIST_PATIENTSEARCHBOX_DISPLAY = MODULE_ID + ".printWorkList.patientSearchBox.display";
	public static final String PROPERTY_RADIOLOGY_ENCOUNTER = MODULE_ID + ".radiologyEncounterType";
	public static final String PROPERTY_MAINTAINCODE = MODULE_ID + ".maintainCode";	
	public static final String SESSION_TEST_TREE_MAP = MODULE_ID + ".testTreeMap";	
	public static final String DEFAULT_XRAY_FORM_REPORT_STATUS = "RADIOLOGY XRAY DEFAULT FORM REPORT STATUS";
	public static final String DEFAULT_XRAY_FORM_FILM_GIVEN = "RADIOLOGY XRAY DEFAULT FORM FILM GIVEN";
	public static final String DEFAULT_XRAY_FORM_FILM_NOT_GIVEN = "RADIOLOGY XRAY DEFAULT FORM FILM NOT GIVEN";
	public static final String DEFAULT_XRAY_FORM_NOTE = "RADIOLOGY XRAY DEFAULT FORM NOTE";
	public static final String PROPERTY_FORM_DEFAULTXRAY = MODULE_ID + ".form.defaultXRay";
	public static final String PROPERTY_PAGESIZE = MODULE_ID + ".pagesize";	
	public static final String PROPERTY_TEMPLATE_DEFAULT = MODULE_ID + ".template.default";
	public static final String ULTRASOUND_ANTENATAL_CONCEPT_NAME = "ULTRASOUND ANTENATAL";
}
