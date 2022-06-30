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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.RadiologyCommonDAO;
import org.openmrs.module.hospitalcore.model.RadiologyTest;

public class HibernateRadiologyCommonDAO implements RadiologyCommonDAO {

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	SimpleDateFormat formatterExt = new SimpleDateFormat("dd/MM/yyyy");

	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;

	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getAllTest(Patient patient)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class);
		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.eq("status", "completed"));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public ConceptAnswer getConceptAnswer(Concept concept) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				ConceptAnswer.class);
		criteria.add(Restrictions.eq("answerConcept", concept));
		criteria.add(Restrictions.ne("concept", Context.getConceptService().getConcept("SPECIAL RADIOLOGY TESTS")));
		criteria.add(Restrictions.ne("concept", Context.getConceptService().getConcept("ROUTINE RADIOLOGY TESTS")));
		return (ConceptAnswer) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getAllTest(Patient patient, String date)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class, "radiologytest");
		criteria.add(Restrictions.eq("patient", patient));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add(Restrictions.and(
					Restrictions.ge("radiologytest.date",
							formatter.parse(startFromDate)),
					Restrictions.le("radiologytest.date",
							formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add(Restrictions.eq("status", "completed"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getAllTest(Patient patient, String date,
			Concept concept) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class, "radiologytest");
		criteria.add(Restrictions.eq("patient", patient));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add(Restrictions.and(
					Restrictions.ge("radiologytest.date",
							formatter.parse(startFromDate)),
					Restrictions.le("radiologytest.date",
							formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add(Restrictions.eq("status", "completed"));
		Collection<ConceptAnswer> conanss = concept.getAnswers();
		ArrayList al = new ArrayList();
		for (ConceptAnswer conans : conanss) {
			al.add(conans.getAnswerConcept());
		}
		criteria.add(Restrictions.in("concept", al));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RadiologyTest> getAllSubTest(Patient patient, String date,
			Concept concept) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				RadiologyTest.class, "radiologytest");
		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.eq("concept", concept));
		String dat = date;
		String startFromDate = dat + " 00:00:00";
		String endFromDate = dat + " 23:59:59";
		try {
			criteria.add(Restrictions.and(
					Restrictions.ge("radiologytest.date",
							formatter.parse(startFromDate)),
					Restrictions.le("radiologytest.date",
							formatter.parse(endFromDate))));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error convert date: " + e.toString());
			e.printStackTrace();
		}
		criteria.add(Restrictions.eq("status", "completed"));
		return criteria.list();
	}

}
