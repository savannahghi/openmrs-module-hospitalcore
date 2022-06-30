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

package org.openmrs.module.hospitalcore.db.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.db.RadiologyDAO;
import org.openmrs.module.hospitalcore.form.RadiologyForm;
import org.openmrs.module.hospitalcore.model.RadiologyDepartment;
import org.openmrs.module.hospitalcore.model.RadiologyTest;
import org.openmrs.module.hospitalcore.template.RadiologyTemplate;
import org.openmrs.module.hospitalcore.util.RadiologyConstants;
import org.springframework.transaction.annotation.Transactional;

public class HibernateRadiologyDAO implements RadiologyDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//
	// RADIOLOGY FORM
	//
	public RadiologyForm saveRadiologyForm(RadiologyForm form) {
		return (RadiologyForm) sessionFactory.getCurrentSession().merge(form);
	}

	public RadiologyForm getRadiologyFormById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyForm.class);
		criteria.add(Restrictions.eq("id", id));
		return (RadiologyForm) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyForm> getRadiologyForms(String conceptName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyForm.class);
		criteria.add(Restrictions.eq("conceptName", conceptName));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyForm> getAllRadiologyForms() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyForm.class);
		return criteria.list();
	}

	public void deleteRadiologyForm(RadiologyForm form) {
		sessionFactory.getCurrentSession().delete(form);
	}

	//
	// RADIOLOGY DEPARTMENT
	//
	public RadiologyDepartment saveRadiologyDepartment(
			RadiologyDepartment department) {
		return (RadiologyDepartment) sessionFactory.getCurrentSession().merge(
				department);
	}

	public RadiologyDepartment getRadiologyDepartmentById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyDepartment.class);
		criteria.add(Restrictions.eq("id", id));
		return (RadiologyDepartment) criteria.uniqueResult();
	}

	public void deleteRadiologyDepartment(RadiologyDepartment department) {
		sessionFactory.getCurrentSession().delete(department);
	}

	public RadiologyDepartment getRadiologyDepartmentByRole(Role role) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyDepartment.class);
		criteria.add(Restrictions.eq("role", role));
		return (RadiologyDepartment) criteria.uniqueResult();
	}

	//
	// ORDER
	//
	public Integer countOrders(Date orderStartDate, OrderType orderType,
			Set<Concept> tests, List<Patient> patients) throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(orderStartDate) + " 00:00:00";
		String endDate = sdf.format(orderStartDate) + " 23:59:59";
		criteria.add(Restrictions.eq("orderType", orderType));
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("startDate",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.eq("discontinued", false));
		criteria.add(Restrictions.in("concept", tests));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getOrders(Date orderStartDate, OrderType orderType,
			Set<Concept> tests, List<Patient> patients, int page, int pageSize)
			throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Order.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(orderStartDate != null){
			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
			String startDate = sdf.format(orderStartDate) + " 00:00:00";
			String endDate = sdf.format(orderStartDate) + " 23:59:59";
			criteria.add((Restrictions.ge("dateActivated",dateTimeFormatter.parse(startDate))));
			criteria.add((Restrictions.le("dateActivated",dateTimeFormatter.parse(endDate))));
		}

		if(orderType != null){
			criteria.add(Restrictions.eq("orderType", orderType));
		}

		if(!CollectionUtils.isEmpty(tests)){
			criteria.add(Restrictions.in("concept", tests));
			//ghanshyam-kesav 16-08-2012 Bug #323 [BILLING] When a bill with a lab\radiology order is edited the order is re-sent
		}
		criteria.add(Restrictions.isNull("dateVoided"));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		criteria.addOrder(org.hibernate.criterion.Order.asc("dateActivated"));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	//
	// RADIOLOGY TEST
	//
	public RadiologyTest saveRadiologyTest(RadiologyTest test) {
		return (RadiologyTest) sessionFactory.getCurrentSession().merge(test);
	}

	public RadiologyTest getRadiologyTestById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		criteria.add(Restrictions.eq("id", id));
		return (RadiologyTest) criteria.uniqueResult();
	}

	public void deleteRadiologyTest(RadiologyTest test) {
		sessionFactory.getCurrentSession().delete(test);
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getAllRadiologyTests() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		return criteria.list();
	}

	public RadiologyTest getRadiologyTestByOrder(Order order) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		criteria.add(Restrictions.eq("order", order));
		return (RadiologyTest) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getRadiologyTestsByDateAndStatus(Date date,
			String status) throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.eq("status", status));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getRadiologyTests(Date date, String status,
			Set<Concept> concepts, List<Patient> patients, int page,
			int pageSize) throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date != null){
			String startDate = sdf.format(date) + " 00:00:00";
			String endDate = sdf.format(date) + " 23:59:59";

			SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			criteria.add((Restrictions.ge("date",dateTimeFormatter.parse(startDate))));
			criteria.add((Restrictions.le("date",dateTimeFormatter.parse(endDate))));
		}

		if(!StringUtils.isEmpty(status)){
			criteria.add(Restrictions.eq("status", status));
		}
		if(!CollectionUtils.isEmpty(concepts)){
			criteria.add(Restrictions.in("concept", concepts));
		}

		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public Integer countRadiologyTests(Date date, String status,
			Set<Concept> concepts, List<Patient> patients)
			throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.eq("status", status));
		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getRadiologyTestsByDate(Date date)
			throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		criteria.add(Expression.between("date",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getRadiologyTestsByDiscontinuedDate(Date date,
			Set<Concept> concepts, List<Patient> patients, int page,
			int pageSize) throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("dateStopped",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));

		if(concepts.size() > 0){
			criteria.add(Restrictions.in("concept", concepts));
		}
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		int firstResult = (page - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);

		return criteria.list();
	}

	public Integer countRadiologyTestsByDiscontinuedDate(Date date,
			Set<Concept> concepts, List<Patient> patients)
			throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("dateStopped",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));

		criteria.add(Restrictions.in("concept", concepts));
		if (!CollectionUtils.isEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		Number rs = (Number) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getRadiologyTestsByDateAndPatient(Date date,
			Patient patient) throws ParseException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		Criteria orderCriteria = criteria.createCriteria("order");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(date) + " 00:00:00";
		String endDate = sdf.format(date) + " 23:59:59";

		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		orderCriteria.add(Expression.between("dateStopped",
				dateTimeFormatter.parse(startDate),
				dateTimeFormatter.parse(endDate)));
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public void createConceptsForXrayDefaultForm() {
		HospitalCoreService hcs = (HospitalCoreService) Context
				.getService(HospitalCoreService.class);
		hcs.insertConcept("Coded", "Question",
				RadiologyConstants.DEFAULT_XRAY_FORM_REPORT_STATUS, "",
				"Radiology default x-ray form report status");
		hcs.insertConcept("Text", "Misc",
				RadiologyConstants.DEFAULT_XRAY_FORM_NOTE, "",
				"Radiology default x-ray form report note");
		hcs.insertConcept("N/A", "Misc",
				RadiologyConstants.DEFAULT_XRAY_FORM_FILM_GIVEN, "",
				"Radiology default x-ray form film given");
		hcs.insertConcept("N/A", "Misc",
				RadiologyConstants.DEFAULT_XRAY_FORM_FILM_NOT_GIVEN, "",
				"Radiology default x-ray form film not given");
	}

	public RadiologyTest getRadiologyTest(Encounter ecnounter) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		criteria.add(Restrictions.eq("encounter", ecnounter));
		return (RadiologyTest) criteria.uniqueResult();
	}

	/*
	 * RADIOLOGY TEMPLATE
	 */
	@SuppressWarnings("unchecked")
	public List<RadiologyTemplate> getAllRadiologyTemplates() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTemplate.class);
		return criteria.list();
	}

	public RadiologyTemplate getRadiologyTemplate(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTemplate.class);
		criteria.add(Restrictions.eq("id", id));
		return (RadiologyTemplate) criteria.uniqueResult();
	}

	public RadiologyTemplate saveRadiologyTemplate(RadiologyTemplate template) {
		return (RadiologyTemplate) sessionFactory.getCurrentSession().merge(
				template);
	}

	public void deleteRadiologyTemplate(RadiologyTemplate template) {
		sessionFactory.getCurrentSession().delete(template);
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTemplate> getRadiologyTemplates(Concept concept) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTemplate.class);
		Criteria testCriteria = criteria.createCriteria("tests");
		testCriteria.add(Restrictions.eq("conceptId", concept.getConceptId()));
		return criteria.list();
	}

	public List<RadiologyTest> getCompletedRadiologyTestsByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		criteria.add(Restrictions.eq("patient", patient));
		return criteria.list();
	}
}
