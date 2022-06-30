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
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.IpdDAO;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmission;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmissionLog;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmitted;
import org.openmrs.module.hospitalcore.model.IpdPatientAdmittedLog;
import org.openmrs.module.hospitalcore.model.IpdPatientVitalStatistics;
import org.openmrs.module.hospitalcore.model.OpdPatientQueueLog;
import org.openmrs.module.hospitalcore.model.WardBedStrength;

public class HibernateIpdDAO implements IpdDAO {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
	public List<IpdPatientAdmission> getAllIpdPatientAdmission()
			throws DAOException {
		return sessionFactory.getCurrentSession()
				.createCriteria(IpdPatientAdmission.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmission> getAllIndoorPatient() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class);
		// criteria.add(Restrictions.eq("acceptStatus", 1));
		return criteria.list();
	}

	// 24/11/2014 to Work with size selctor for IPDQueue
	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmissionLog> getAllIndoorPatientFromAdmissionLog(
			String searchKey, int page, int pgSize) throws DAOException {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); String
		 * startDate = sdf.format(date) + " 00:00:00"; String endDate =
		 * sdf.format(date) + " 23:59:59";
		 * 
		 * String hql = "select ipal from IpdPatientAdmissionLog ipal " +
		 * "where ipal.indoorStatus=1 and ipal.status like 'admitted' " +
		 * "and ipal.billingStatus=0 and ipal.admissionDate between '"+
		 * startDate+ "' AND '"+ endDate + "'	" +
		 * "and (ipal.patientIdentifier LIKE '%" + searchKey +
		 * "%' OR ipal.patientName LIKE '" + searchKey + "%')" ;
		 */
		String hql = "select ipal from IpdPatientAdmissionLog ipal "
				+ "where ipal.indoorStatus=1 and ipal.status like 'admitted' "
				+ "and (ipal.patientIdentifier LIKE '%" + searchKey
				+ "%' OR ipal.patientName LIKE '" + searchKey + "%')";

		int firstResult = (page - 1) * pgSize;
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql).setFirstResult(firstResult)
				.setMaxResults(pgSize);
		List<IpdPatientAdmissionLog> list = q.list();
		return list;

	}

	// 24/11/2014 to Work with size selctor for IPDQueue
	@SuppressWarnings("unchecked")
	public int countGetAllIndoorPatientFromAdmissionLog(String searchKey,
			int page) throws DAOException {
		String hql = "select ipal from IpdPatientAdmissionLog ipal "
				+ "where ipal.indoorStatus=1 and ipal.status like 'admitted' "
				+ "and (ipal.patientIdentifier LIKE '%" + searchKey
				+ "%' OR ipal.patientName LIKE '" + searchKey + "%')";

		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<IpdPatientAdmissionLog> list = q.list();
		return list.size();

	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmissionLog> listIpdPatientAdmissionLog(
			Integer patientId, Integer admissionWardId, String status,
			Integer min, Integer max) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmissionLog.class, "ipdPatientAdmissionLog");
		if (patientId != null && patientId > 0) {
			criteria.createAlias("ipdPatientAdmissionLog.patient", "patient");
			criteria.add(Restrictions.eq("patient.patientId", patientId));
		}
		if (admissionWardId != null && admissionWardId > 0) {
			criteria.add(Restrictions.eq(
					"ipdPatientAdmissionLog.admissionWard.conceptId",
					admissionWardId));
		}
		if (StringUtils.isNotBlank(status)) {
			criteria.add(Restrictions.eq("ipdPatientAdmissionLog.status",
					status));
		}
		if (max > 0) {
			criteria.setFirstResult(min).setMaxResults(max);
		}
		criteria.addOrder(Order.desc("ipdPatientAdmissionLog.admissionDate"));
		List<IpdPatientAdmissionLog> list = criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmitted> getAllIpdPatientAdmitted()
			throws DAOException {
		return sessionFactory.getCurrentSession()
				.createCriteria(IpdPatientAdmitted.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmittedLog> getAllIpdPatientAdmittedLog()
			throws DAOException {
		return sessionFactory.getCurrentSession()
				.createCriteria(IpdPatientAdmittedLog.class).list();
	}

	public IpdPatientAdmission getIpdPatientAdmission(Integer id)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class);
		criteria.add(Restrictions.eq("id", id));
		return (IpdPatientAdmission) criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Integer id)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmissionLog.class);
		criteria.add(Restrictions.eq("id", id));
		return (IpdPatientAdmissionLog) criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(
			OpdPatientQueueLog opdLog) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmissionLog.class);
		criteria.add(Restrictions.eq("opdLog", opdLog));
		return (IpdPatientAdmissionLog) criteria.uniqueResult();
	}

	public IpdPatientAdmissionLog getIpdPatientAdmissionLog(Encounter encounter)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmissionLog.class);
		criteria.add(Restrictions.eq("ipdEncounter", encounter));
		return (IpdPatientAdmissionLog) criteria.uniqueResult();
	}

	public IpdPatientAdmitted getIpdPatientAdmitted(Integer id)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class);
		criteria.add(Restrictions.eq("id", id));
		return (IpdPatientAdmitted) criteria.uniqueResult();
	}

	public IpdPatientAdmittedLog getIpdPatientAdmittedLog(Integer id)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmittedLog.class);
		criteria.add(Restrictions.eq("id", id));
		return (IpdPatientAdmittedLog) criteria.uniqueResult();
	}

	public IpdPatientAdmission saveIpdPatientAdmission(
			IpdPatientAdmission admission) throws DAOException {
		return (IpdPatientAdmission) sessionFactory.getCurrentSession().merge(
				admission);
	}

	public IpdPatientAdmissionLog saveIpdPatientAdmissionLog(
			IpdPatientAdmissionLog admissionLog) throws DAOException {
		return (IpdPatientAdmissionLog) sessionFactory.getCurrentSession()
				.merge(admissionLog);
	}

	public IpdPatientAdmitted saveIpdPatientAdmitted(IpdPatientAdmitted admitted)
			throws DAOException {
		return (IpdPatientAdmitted) sessionFactory.getCurrentSession().merge(
				admitted);
	}

	public IpdPatientAdmittedLog saveIpdPatientAdmittedLog(
			IpdPatientAdmittedLog admittedLog) throws DAOException {
		return (IpdPatientAdmittedLog) sessionFactory.getCurrentSession()
				.merge(admittedLog);
	}

	public List<IpdPatientAdmission> searchIpdPatientAdmission(
			String patientSearch, ArrayList<Integer> userIds, String fromDate,
			String toDate, String wardId, String status) throws APIException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class, "patientAdmission");
		if (StringUtils.isNotBlank(fromDate) && StringUtils.isBlank(toDate)) {
			String startFromDate = fromDate + " 00:00:00";
			String endFromDate = fromDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmission.admissionDate",
						formatter.parse(startFromDate)), Restrictions.le(
						"patientAdmission.admissionDate",
						formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isBlank(fromDate)
				&& StringUtils.isNotBlank(toDate)) {
			String startToDate = toDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmission.admissionDate",
						formatter.parse(startToDate)), Restrictions.le(
						"patientAdmission.admissionDate",
						formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isNotBlank(fromDate)
				&& StringUtils.isNotBlank(toDate)) {
			String startToDate = fromDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmission.admissionDate",
						formatter.parse(startToDate)), Restrictions.le(
						"patientAdmission.admissionDate",
						formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		}

		String prefix = Context.getAdministrationService().getGlobalProperty(
				"registration.identifier_prefix");
		if (StringUtils.isNotBlank(patientSearch)) {
			if (patientSearch.contains("-") && !patientSearch.contains(prefix)) {
				patientSearch = prefix + patientSearch;
			}
			if (patientSearch.contains(prefix)) {
				criteria.add(Restrictions.eq(
						"patientAdmission.patientIdentifier", patientSearch));
			} else {
				criteria.add(Restrictions.like("patientAdmission.patientName",
						"%" + patientSearch + "%"));
			}
		}

		if (CollectionUtils.isNotEmpty(userIds)) {
			criteria.createAlias("patientAdmission.opdAmittedUser", "user");
			criteria.add(Restrictions.in("user.id", userIds));
		}

		criteria.createAlias("patientAdmission.admissionWard", "ward");
		criteria.add(Restrictions.eq("ward.conceptId", Integer.parseInt(wardId)));

		if (StringUtils.isNotBlank(status)) {
			criteria.add(Restrictions.eq("patientAdmission.status", status));
		}
		criteria.addOrder(Order.asc("patientAdmission.admissionDate"));
		List<IpdPatientAdmission> list = criteria.list();
		return list;
	}

	public List<IpdPatientAdmitted> searchIpdPatientAdmitted(
			String patientSearch, ArrayList<Integer> userIds, String fromDate,
			String toDate, String wardId, String status) throws APIException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class, "patientAdmitted");

		if (!StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate)) {
			String startFromDate = fromDate + " 00:00:00";
			String endFromDate = fromDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmitted.admissionDate",
						formatter.parse(startFromDate)), Restrictions.le(
						"patientAdmitted.admissionDate",
						formatter.parse(endFromDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (StringUtils.isBlank(fromDate)
				&& !StringUtils.isBlank(toDate)) {
			String startToDate = toDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmitted.admissionDate",
						formatter.parse(startToDate)), Restrictions.le(
						"patientAdmitted.admissionDate",
						formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		} else if (!StringUtils.isBlank(fromDate)
				&& !StringUtils.isBlank(toDate)) {
			String startToDate = fromDate + " 00:00:00";
			String endToDate = toDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge(
						"patientAdmitted.admissionDate",
						formatter.parse(startToDate)), Restrictions.le(
						"patientAdmitted.admissionDate",
						formatter.parse(endToDate))));
			} catch (Exception e) {
				// TODO: handle exception
				log.error("Error convert date: " + e.toString());
				e.printStackTrace();
			}
		}

		String prefix = Context.getAdministrationService().getGlobalProperty(
				"registration.identifier_prefix");
		if (StringUtils.isNotBlank(patientSearch)) {
			if (patientSearch.contains("-") && !patientSearch.contains(prefix)) {
				patientSearch = prefix + patientSearch;
			}
			if (patientSearch.contains(prefix)) {
				criteria.add(Restrictions.eq(
						"patientAdmitted.patientIdentifier", patientSearch));
			} else {
				criteria.add(Restrictions.like("patientAdmitted.patientName",
						"%" + patientSearch + "%"));
			}
		}

		if (CollectionUtils.isNotEmpty(userIds)) {
			criteria.createAlias("patientAdmitted.ipdAdmittedUser", "user");
			criteria.add(Restrictions.in("user.id", userIds));
		}

		criteria.createAlias("patientAdmitted.admittedWard", "ward");
		criteria.add(Restrictions.eq("ward.conceptId", Integer.parseInt(wardId)));

		if (StringUtils.isNotBlank(status)) {
			criteria.add(Restrictions.eq("patientAdmitted.status", status));
		}
		return criteria.list();
	}

	public void removeIpdPatientAdmission(IpdPatientAdmission admission)
			throws DAOException {
		sessionFactory.getCurrentSession().delete(admission);
	}

	public void removeIpdPatientAdmitted(IpdPatientAdmitted admitted)
			throws DAOException {
		sessionFactory.getCurrentSession().delete(admitted);
	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmittedLog> listAdmittedLogByPatientId(
			Integer patientId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmittedLog.class);
		criteria.add(Restrictions.eq("patient.id", patientId));
		criteria.addOrder(Order.desc("admissionDate"));

		return criteria.list();
	}

	public IpdPatientAdmitted getAdmittedByPatientId(Integer patientId)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class);
		criteria.add(Restrictions.eq("patient.id", patientId));
		List<IpdPatientAdmitted> list = criteria.list();
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public IpdPatientAdmitted getAdmittedByAdmissionLogId(
			IpdPatientAdmissionLog ipdPatientAdmissionLog) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class);
		criteria.add(Restrictions.eq("patientAdmissionLog",
				ipdPatientAdmissionLog));
		return (IpdPatientAdmitted) criteria.uniqueResult();
	}

	// ghanshyam 10-june-2013 New Requirement #1847 Capture Vital statistics for
	// admitted patient in ipd
	public IpdPatientVitalStatistics saveIpdPatientVitalStatistics(
			IpdPatientVitalStatistics vitalStatistics) throws DAOException {
		return (IpdPatientVitalStatistics) sessionFactory.getCurrentSession()
				.merge(vitalStatistics);
	}

	public List<Concept> getDiet() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Concept.class, "con");
		ConceptClass conClass = Context.getConceptService()
				.getConceptClassByName("Diet");
		criteria.add(Restrictions.eq("con.conceptClass", conClass));
		return criteria.list();
	}

	public List<IpdPatientVitalStatistics> getIpdPatientVitalStatistics(
			Integer patientId, Integer patientAdmissionLogId)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientVitalStatistics.class);
		criteria.add(Restrictions.eq("patient.personId", patientId));
		criteria.add(Restrictions.eq("ipdPatientAdmissionLog.id",
				patientAdmissionLogId));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<IpdPatientAdmitted> getAllIpdAdmittedPatientByWardId(
			Integer wardId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class);
		criteria.add(Restrictions.eq("admittedWard.id", wardId));
		return sessionFactory.getCurrentSession()
				.createCriteria(IpdPatientAdmitted.class).list();
	}

	public WardBedStrength getWardBedStrengthByWardId(Integer wardId)
			throws DAOException {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				WardBedStrength.class);
		criteria.add(Restrictions.eq("ward.id", wardId));
		List<WardBedStrength> list = criteria.list();

		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public void saveWardBedStrength(WardBedStrength wardBedStrength)
			throws DAOException {
		sessionFactory.getCurrentSession().saveOrUpdate(wardBedStrength);

	}

	public IpdPatientAdmission getIpdPatientAdmissionByEncounter(
			Encounter encounter) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class);
		criteria.add(Restrictions.eq("ipdEncounter", encounter));
		return (IpdPatientAdmission) criteria.uniqueResult();

	}

	public IpdPatientAdmission getIpdPatientAdmissionByPatientId(Patient patientId)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class);
		criteria.add(Restrictions.eq("patient", patientId));
		return (IpdPatientAdmission) criteria.uniqueResult();
	}

	public IpdPatientAdmission getIpdPatientAdmissionByPatient(Patient patient)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmission.class);
		criteria.add(Restrictions.eq("patient", patient));
		return (IpdPatientAdmission) criteria.uniqueResult();
	}
	public List<IpdPatientAdmitted> getBedAvailability(Concept wardId,String bedNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				IpdPatientAdmitted.class);
		criteria.add(Restrictions.eq("admittedWard", wardId));
		criteria.add(Restrictions.eq("bed", bedNo));
		return criteria.list();
	}

}
