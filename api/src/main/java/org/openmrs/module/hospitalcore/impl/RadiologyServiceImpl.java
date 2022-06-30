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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Provider;
import org.openmrs.Role;
import org.openmrs.api.ProviderService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.BillingConstants;
import org.openmrs.module.hospitalcore.RadiologyService;
import org.openmrs.module.hospitalcore.concept.ConceptNode;
import org.openmrs.module.hospitalcore.concept.TestTree;
import org.openmrs.module.hospitalcore.db.RadiologyDAO;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;
import org.openmrs.module.hospitalcore.util.GlobalPropertyUtil;
import org.openmrs.module.hospitalcore.util.PatientUtils;
import org.openmrs.module.hospitalcore.util.RadiologyConstants;

public class RadiologyServiceImpl extends BaseOpenmrsService implements
		RadiologyService {

	public RadiologyServiceImpl() {
	}

	protected RadiologyDAO dao;

	public void setDao(RadiologyDAO dao) {
		this.dao = dao;
	}

	//
	// RADIOLOGY FORM
	//
	public RadiologyForm saveRadiologyForm(RadiologyForm form) {
		return dao.saveRadiologyForm(form);
	}

	public RadiologyForm getRadiologyFormById(Integer id) {
		return dao.getRadiologyFormById(id);
	}

	public List<RadiologyForm> getAllRadiologyForms() {
		return dao.getAllRadiologyForms();
	}

	public void deleteRadiologyForm(RadiologyForm form) {
		dao.deleteRadiologyForm(form);
	}

	public List<RadiologyForm> getRadiologyForms(String conceptName) {
		return dao.getRadiologyForms(conceptName);
	}

	public RadiologyForm getDefaultForm() {
		Integer formId = GlobalPropertyUtil.getInteger(
				RadiologyConstants.PROPERTY_FORM_DEFAULTXRAY, 0);
		RadiologyForm form = getRadiologyFormById(formId);
		return form;
	}

	//
	// RADIOLOGY DEPARTMENT
	//
	public RadiologyDepartment saveRadiologyDepartment(
			RadiologyDepartment department) {
		return dao.saveRadiologyDepartment(department);
	}

	public RadiologyDepartment getRadiologyDepartmentById(Integer id) {
		return dao.getRadiologyDepartmentById(id);
	}

	public void deleteRadiologyDepartment(RadiologyDepartment department) {
		dao.deleteRadiologyDepartment(department);
	}

	public RadiologyDepartment getCurrentRadiologyDepartment() {
		Set<Role> roles = Context.getAuthenticatedUser().getAllRoles();
		List<RadiologyDepartment> departments = new ArrayList<RadiologyDepartment>();
		for (Role role : roles) {
			RadiologyDepartment department = dao
					.getRadiologyDepartmentByRole(role);
			if (department != null) {
				departments.add(department);
			}
		}

		if (!departments.isEmpty()) {
			return departments.get(0);
		} else {
			return null;
		}
	}

	//
	// ORDER
	//
	public List<Order> getOrders(Date startDate, String phrase,
			Set<Concept> tests, int page) throws ParseException {

		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger(
				BillingConstants.GLOBAL_PROPRETY_RADIOLOGY_ORDER_TYPE, 4);
		OrderType orderType = Context.getOrderService().getOrderType(
				radiologyOrderTypeId);

		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase)) {
			patients = Context.getPatientService().getPatients(phrase);
		}
		List<Order> orders = dao.getOrders(startDate, orderType, tests,
				patients, page, GlobalPropertyUtil.getInteger(
						RadiologyConstants.PROPERTY_PAGESIZE, 20));
		return orders;
	}

	public Integer countOrders(Date startDate, String phrase, Set<Concept> tests)
			throws ParseException {

		Integer radiologyOrderTypeId = GlobalPropertyUtil.getInteger(
				BillingConstants.GLOBAL_PROPRETY_RADIOLOGY_ORDER_TYPE, 8);
		OrderType orderType = Context.getOrderService().getOrderType(
				radiologyOrderTypeId);

		List<Patient> patients = Context.getPatientService()
				.getPatients(phrase);
		return dao.countOrders(startDate, orderType, tests, patients);
	}

	//
	// RADIOLOGY TEST
	//
	public RadiologyTest saveRadiologyTest(RadiologyTest test) {
		return dao.saveRadiologyTest(test);
	}

	public RadiologyTest getRadiologyTestById(Integer id) {
		return dao.getRadiologyTestById(id);
	}

	public List<RadiologyTest> getAllRadiologyTests() {
		return dao.getAllRadiologyTests();
	}

	public void deleteRadiologyTest(RadiologyTest test) {
		dao.deleteRadiologyTest(test);
	}

	public Integer acceptTest(Order order) {
		RadiologyTest test = dao.getRadiologyTestByOrder(order);
		if (test == null) {
			test = new RadiologyTest();
			test.setOrder(order);
			test.setPatient(order.getPatient());
			test.setConcept(order.getConcept());
			test.setCreator(Context.getAuthenticatedUser());
			test.setDate(new Date());
			test.setStatus(RadiologyConstants.TEST_STATUS_ACCEPTED);
			List<RadiologyForm> forms = dao.getRadiologyForms(order
					.getConcept().getName().getName());
			if (CollectionUtils.isEmpty(forms)) {
				test.setForm(getDefaultForm());
			} else {
				test.setForm(forms.get(0));
			}

			RadiologyTest acceptedTest = dao.saveRadiologyTest(test);
			return acceptedTest.getId();
		}
		return -1;
	}

	public String unacceptTest(RadiologyTest test) {
		if (test != null) {
			if (test.getStatus().equalsIgnoreCase(
					RadiologyConstants.TEST_STATUS_ACCEPTED)) {
				dao.deleteRadiologyTest(test);
				return RadiologyConstants.UNACCEPT_TEST_RETURN_STATUS_SUCCESS;
			} else {
				// TODO: add more unaccept test return status here
			}
		} else {
			return RadiologyConstants.UNACCEPT_TEST_RETURN_STATUS_NOT_FOUND;
		}
		return null;
	}

	public String rescheduleTest(Order order, Date rescheduledDate) {

		if (order.getDateStopped() != null) {
			RadiologyTest test = getRadiologyTestByOrder(order);
			if (test != null) {
				if (test.getStatus().equalsIgnoreCase(
						RadiologyConstants.TEST_STATUS_ACCEPTED)) {
					order.setDateActivated(rescheduledDate);
					order.setChangedBy(Context.getAuthenticatedUser());
					order.setDateChanged(new Date());
					deleteRadiologyTest(test);
					Context.getOrderService().saveOrder(order, null);
					return RadiologyConstants.RESCHEDULE_TEST_RETURN_STATUS_SUCCESS;
				} else {
					// TODO: add more reschedule test return status here
					return test.getStatus();
				}
			} else {
				order.setDateActivated(rescheduledDate);
				order.setChangedBy(Context.getAuthenticatedUser());
				order.setDateChanged(new Date());
				Context.getOrderService().saveOrder(order, null);
				return RadiologyConstants.RESCHEDULE_TEST_RETURN_STATUS_SUCCESS;
			}
		}

		return RadiologyConstants.RESCHEDULE_TEST_RETURN_STATUS_ENTERED;
	}

	public RadiologyTest getRadiologyTestByOrder(Order order) {
		return dao.getRadiologyTestByOrder(order);
	}

	public List<RadiologyTest> getAcceptedRadiologyTests(Date date,
			String phrase, Set<Concept> allowableTests, int page)
			throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase)) {
			patients = Context.getPatientService().getPatients(phrase);
		}
		return dao.getRadiologyTests(date,
				RadiologyConstants.TEST_STATUS_ACCEPTED, allowableTests,
				patients, page, GlobalPropertyUtil.getInteger(
						RadiologyConstants.PROPERTY_PAGESIZE, 20));
	}

	public Integer countAcceptedRadiologyTests(Date date, String phrase,
			Set<Concept> allowableTests) throws ParseException {
		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase)) {
			patients = Context.getPatientService().getPatients(phrase);
		}
		return dao.countRadiologyTests(date,
				RadiologyConstants.TEST_STATUS_ACCEPTED, allowableTests,
				patients);
	}

	public List<RadiologyTest> getCompletedRadiologyTests(Date date,
			String phrase, Set<Concept> allowableTests, int page)
			throws ParseException {

		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase)) {
			patients = Context.getPatientService().getPatients(phrase);
		}

		List<RadiologyTest> tests = dao.getRadiologyTestsByDiscontinuedDate(
				date, allowableTests, patients, page, GlobalPropertyUtil
						.getInteger(RadiologyConstants.PROPERTY_PAGESIZE, 20));

		return tests;
	}

	public Integer countCompletedRadiologyTests(Date date, String phrase,
			Set<Concept> allowableTests) throws ParseException {

		List<Patient> patients = null;
		if (!StringUtils.isBlank(phrase)) {
			patients = Context.getPatientService().getPatients(phrase);
		}

		return dao.countRadiologyTestsByDiscontinuedDate(date, allowableTests,
				patients);

	}

	public List<RadiologyTest> getAllRadiologyTestsByDate(Date date,
			String phrase, Concept investigation) throws ParseException {
		List<RadiologyTest> tests = dao.getRadiologyTestsByDate(date);
		return filterRadiologyTests(tests, phrase, investigation);
	}

	private List<RadiologyTest> filterRadiologyTests(List<RadiologyTest> tests,
			String phrase, Concept investigation) {

		// patient filtered
		List<RadiologyTest> patientFilteredOrders = new ArrayList<RadiologyTest>();
		for (RadiologyTest test : tests) {
			Patient patient = test.getPatient();
			String fullname = PatientUtils.getFullName(patient).toLowerCase();
			String identifier = patient.getPatientIdentifier().getIdentifier()
					.toLowerCase();
			phrase = phrase.toLowerCase();
			if ((fullname.contains(phrase)) || (identifier.contains(phrase))) {
				patientFilteredOrders.add(test);
			}
		}

		// investigation filtered
		List<RadiologyTest> investigationFilteredOrders = new ArrayList<RadiologyTest>();
		if (investigation != null) {
			TestTree tree = new TestTree(investigation);
			Set<ConceptNode> nodes = tree.getRootNode().getChildNodes();
			/*
			for (RadiologyTest test : patientFilteredOrders) {
				ConceptNode node = new ConceptNode(test.getConcept());
				if (nodes.contains(node)) {
			*/ 
			List<ConceptNode> leafNodeList = new ArrayList<ConceptNode>();
			for(ConceptNode nod:nodes){
				Set<ConceptNode> leafNode =nod.getChildNodes();
				if(nod.getChildNodes().size()!=0){
				leafNodeList.addAll(leafNode);
				}
				else{
				leafNodeList.add(nod);
				}
			}
			for (RadiologyTest test : patientFilteredOrders) {
				ConceptNode node = new ConceptNode(test.getConcept());
				if (leafNodeList.contains(node)) {
					investigationFilteredOrders.add(test);
				}
			}
		} else {
			investigationFilteredOrders = patientFilteredOrders;
		}

		return investigationFilteredOrders;
	}

	public String completeTest(RadiologyTest test) {

		if (test.getStatus() != null) {
			if ((test.getStatus().equalsIgnoreCase(
					RadiologyConstants.TEST_STATUS_ACCEPTED) || (test
					.getStatus()
					.equalsIgnoreCase(RadiologyConstants.TEST_STATUS_COMPLETED)))) {
				Order order = test.getOrder();
				order.setCreator(Context.getAuthenticatedUser());
				Order revisedOrder = order.cloneForRevision();
                revisedOrder.setEncounter(test.getEncounter());
                revisedOrder.setOrderer(test.getOrder().getOrderer());
                revisedOrder.setAction(Order.Action.DISCONTINUE);
                revisedOrder.setAutoExpireDate(new Date());

                Context.getOrderService().saveOrder(revisedOrder, null);
                test.setStatus(RadiologyConstants.TEST_STATUS_COMPLETED);
				saveRadiologyTest(test);
				return RadiologyConstants.COMPLETE_TEST_RETURN_STATUS_SUCCESS;
			}
		}
		return RadiologyConstants.COMPLETE_TEST_RETURN_STATUS_NOT_ACCEPTED;
	}

	/*
	 * private int getEncounterAgainstFormNumber(RadiologyTest test) { int
	 * encounterAgainstForm = 0; if (test.getGivenForm() != null)
	 * encounterAgainstForm++; if (test.getNotGivenForm() != null)
	 * encounterAgainstForm++; if (test.getGivenEncounter() != null)
	 * encounterAgainstForm--; if (test.getNotGivenEncounter() != null)
	 * encounterAgainstForm--; return encounterAgainstForm; }
	 */

	public List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date date,
			Patient patient) throws ParseException {
		List<RadiologyTest> tests = dao.getRadiologyTestsByDateAndPatient(date,
				patient);
		return tests;
	}

	public void createConceptsForXrayDefaultForm() {
		dao.createConceptsForXrayDefaultForm();
	}

	/**
	 * Get radiology test by encounter
	 * 
	 * @param ecnounter
	 * @return
	 */
	public RadiologyTest getRadiologyTest(Encounter ecnounter) {
		return dao.getRadiologyTest(ecnounter);
	}

	/*
	 * RADIOLOGY TEMPLATE
	 */
	public RadiologyTemplate getRadiologyTemplate(Integer id) {
		return dao.getRadiologyTemplate(id);
	}

	public RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate template) {
		return dao.saveRadiologyTemplate(template);
	}

	public List<RadiologyTemplate> getAllRadiologyTemplates() {
		return dao.getAllRadiologyTemplates();
	}

	public void deleteRadiologyTemplate(RadiologyTemplate template) {
		dao.deleteRadiologyTemplate(template);
	}

	public List<RadiologyTemplate> getRadiologyTemplates(Concept concept) {
		return dao.getRadiologyTemplates(concept);
	}

	public List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return dao.getCompletedRadiologyTestsByPatient(patient);
	}

	private Provider getProvider(Person person) {
		Provider provider = null;
		ProviderService providerService = Context.getProviderService();
		List<Provider> providerList = new ArrayList<Provider>(providerService.getProvidersByPerson(person));
		if(providerList.size() > 0){
			provider = providerList.get(0);
		}
		return provider;
	}
}
