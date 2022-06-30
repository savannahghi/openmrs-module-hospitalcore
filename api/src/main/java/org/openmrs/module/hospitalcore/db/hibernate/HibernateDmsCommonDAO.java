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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.DmsCommonDAO;
import org.openmrs.module.hospitalcore.model.DmsOpdUnit;

public class HibernateDmsCommonDAO implements DmsCommonDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public ConceptName getOpdWardNameByConceptId(Concept con)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				ConceptName.class);
		criteria.add(Restrictions.eq("concept", con));
		criteria.add(Restrictions.like("conceptNameType",
				ConceptNameType.FULLY_SPECIFIED));
		return (ConceptName) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<DmsOpdUnit> getOpdActivatedIdList() {
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		int second = date.getSeconds();
		String curtime = "";
		if(hour<10){
		curtime = "0" + hour + ":" + minute + ":" + second;
		}
		else{
			curtime = hour + ":" + minute + ":" + second;
		}
		String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		GregorianCalendar gcalendar = new GregorianCalendar();
		String dayName = days[gcalendar.get(Calendar.DAY_OF_WEEK) - 1];
		String hql = "from DmsOpdUnit d where d.opdWorkingDay='"
				+ dayName
				+ "' AND '"
				+ curtime
				+ "' BETWEEN d.startTime AND d.endTime AND d.unitActiveDate is not null";
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		List<DmsOpdUnit> list = q.list();
		return list;
	}
}
