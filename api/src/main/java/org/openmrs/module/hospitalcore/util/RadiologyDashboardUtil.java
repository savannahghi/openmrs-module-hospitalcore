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

import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class RadiologyDashboardUtil {

	 //ghanshyam 6-august-2013 code review bug
	//private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	private static Map<Concept, String> conceptNames = new HashMap<Concept, String>();
	private static Concept xrayConcept = null;
	private static Set<Concept> xrayConcepts = null;

	/**
	 * Generate list of test models using tests
	 * 
	 * @param tests
	 * @return
	 */
	public static List<TestModel> generateModelsFromTests(
			List<RadiologyTest> tests, Map<Concept, Set<Concept>> testTreeMap) {

		List<TestModel> models = new ArrayList<TestModel>();
		for (RadiologyTest test : tests) {
			TestModel tm = generateModel(test, testTreeMap);
			models.add(tm);
		}
		return models;
	}


	/**
	 * Generate a single test model
	 * 
	 * @param test
	 * @return
	 */
	public static TestModel generateModel(RadiologyTest test,
			Map<Concept, Set<Concept>> testTreeMap) {
		return generateModel(test.getOrder(), test, testTreeMap);
	}

	/*
	 * REFACTORING
	 */
	private static TestModel generateModel(Order order, RadiologyTest test,
			Map<Concept, Set<Concept>> testTreeMap) {

		//ghanshyam 6-august-2013 code review bug
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		TestModel tm = new TestModel();
		tm.setStartDate(sdf.format(order.getDateActivated()));
		tm.setPatientIdentifier(order.getPatient().getPatientIdentifier()
				.getIdentifier());
		tm.setPatientName(PatientUtils.getFullName(order.getPatient()));
		tm.setGender(order.getPatient().getGender());
		tm.setAge(PatientUtils.estimateAge(order.getPatient()));
		tm.setTestName(order.getConcept().getName().getName());
		tm.setOrderId(order.getOrderId());

		// if the test is an x-ray test, then turn on this flag
		if (getXrayConcepts(testTreeMap).contains(order.getConcept())) {
			tm.setXray(true);
		} else {
			tm.setXray(false);
		}

		if (test != null) {
			tm.setStatus(test.getStatus());
			tm.setTestId(test.getId());
			if (test.getForm() != null) {
				tm.setGivenFormId(test.getForm().getId());
			}
			if (test.getEncounter() != null) {
				tm.setGivenEncounterId(test.getEncounter()
						.getEncounterId());
			}			
			tm.setAcceptedDate(test.getDate().toString());
		} else {
			tm.setStatus(null);
		}

		// get investigation from test tree map
		if (testTreeMap != null) {
			for (Concept investigationConcept : testTreeMap.keySet()) {
				Set<Concept> set = testTreeMap.get(investigationConcept);
				if (set.contains(order.getConcept())) {
					tm.setInvestigation(getConceptName(investigationConcept));
				}

			}
		}

		return tm;
	}

	/**
	 * Search for concept using name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Concept searchConcept(String name) {
		Concept concept = Context.getConceptService().getConcept(name);
		if (concept != null) {
			return concept;
		} else {
			List<Concept> cws = Context.getConceptService().getConceptsByName(
					name, new Locale("en"), false);
			if (!cws.isEmpty())
				return cws.get(0);
		}
		return null;
	}

	private static Concept getXrayConcept(Map<Concept, Set<Concept>> testTreeMap) {
		if (xrayConcept != null) {
			return xrayConcept;
		} else {
			for (Concept concept : testTreeMap.keySet()) {
				Concept investigation = Context.getConceptService().getConcept(
						concept.getConceptId());
				if (investigation.getName().getName().toLowerCase()
						.contains("x-ray")) {
					xrayConcept = investigation;
					return xrayConcept;
				}
			}
		}
		return null;
	}

	private static Set<Concept> getXrayConcepts(
			Map<Concept, Set<Concept>> testTreeMap) {
		if (xrayConcepts != null) {
			return xrayConcepts;
		} else {
			Concept xrayConcept = getXrayConcept(testTreeMap);
			xrayConcepts = testTreeMap.get(xrayConcept);
			return xrayConcepts;
		}
	}

	/**
	 * Get name of a detached by hibernate session concept
	 * 
	 * @param searchConcept
	 * @return
	 */
	public static String getConceptName(Concept searchConcept) {
		if (conceptNames.containsKey(searchConcept)) {
			return conceptNames.get(searchConcept);
		} else {
			Concept concept = Context.getConceptService().getConcept(
					searchConcept.getConceptId());
			conceptNames.put(searchConcept, concept.getName().getName());
			return conceptNames.get(searchConcept);
		}
	}
}
