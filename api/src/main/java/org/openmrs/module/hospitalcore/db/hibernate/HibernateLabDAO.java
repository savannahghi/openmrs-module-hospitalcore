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

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Order;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.LabDAO;
import org.openmrs.module.hospitalcore.model.Lab;
import org.openmrs.module.hospitalcore.model.LabTest;

public class HibernateLabDAO implements LabDAO {

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
	public List<Lab> getAllActivelab() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.eq("retired", false));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Lab> getAllLab() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		return criteria.list();
	}

	public Lab getLabById(Integer labId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.eq("labId", labId));
		return (Lab) criteria.uniqueResult();
	}

	public Lab getLabByName(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.eq("name", name));
		return (Lab) criteria.uniqueResult();
	}

	public LabTest getLabTestById(Integer labTestId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add(Restrictions.eq("labTestId", labTestId));
		return (LabTest) criteria.uniqueResult();
	}

	public LabTest getLabTestByOrder(Order order) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add(Restrictions.eq("order", order));
		return (LabTest) criteria.uniqueResult();
	}

	public LabTest getLabTestBySampleNumber(String sampleNumber)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add(Restrictions.eq("sampleNumber", sampleNumber));
		return (LabTest) criteria.uniqueResult();
	}

	public Lab saveLab(Lab lab) throws DAOException {
		return (Lab) sessionFactory.getCurrentSession().merge(lab);
	}

	public LabTest saveLabTest(LabTest labTest) throws DAOException {
		return (LabTest) sessionFactory.getCurrentSession().merge(labTest);
	}

	public void deleteLab(Lab lab) throws DAOException {
		sessionFactory.getCurrentSession().delete(lab);
	}

	public Lab getLabByRole(Role role) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.eq("role", role));
		return (Lab) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Lab> getLabByRoles(List<Role> roles) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Lab.class);
		criteria.add(Restrictions.in("role", roles));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<LabTest> getLatestLabTestByDate(Date today,Date nextDay, Lab lab)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LabTest.class);
		criteria.add(Restrictions.eq("lab", lab));
		criteria.add(Restrictions.ge("acceptDate", new java.sql.Date( today.getTime())));
		criteria.add(Restrictions.lt("acceptDate", new java.sql.Date( nextDay.getTime())));
		return criteria.list();
	}

	public void deleteLabTest(LabTest labtest) throws DAOException {
		sessionFactory.getCurrentSession().delete(labtest);
	}

	
}
