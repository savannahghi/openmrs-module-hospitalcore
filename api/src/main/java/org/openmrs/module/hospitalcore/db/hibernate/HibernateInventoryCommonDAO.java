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
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.hospitalcore.db.InventoryCommonDAO;
import org.openmrs.module.hospitalcore.model.*;

public class HibernateInventoryCommonDAO implements InventoryCommonDAO {

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
	public List<InventoryStoreDrugPatient> getAllIssueDateByPatientId(
			Patient patient) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				InventoryStoreDrugPatient.class);
		criteria.add(Restrictions.eq("patient", patient));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<InventoryStoreDrugPatient> getDeatilOfInventoryStoreDrugPatient(
			Patient patient, String date) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				InventoryStoreDrugPatient.class);
		criteria.add(Restrictions.eq("patient", patient));
		if (!date.equals("all")) {
			String startDate = date + " 00:00:00";
			String endDate = date + " 23:59:59";
			try {
				criteria.add(Restrictions.and(Restrictions.ge("createdOn",
						formatter.parse(startDate)), Restrictions.le(
						"createdOn", formatter.parse(endDate))));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<InventoryStoreDrugPatientDetail> getDrugDetailOfPatient(
			InventoryStoreDrugPatient isdpd) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				InventoryStoreDrugPatientDetail.class);
		if(isdpd != null) {
			criteria.add(Restrictions.eq("storeDrugPatient", isdpd));
		}

		return criteria.list();
	}

	// ghanshyam 12-june-2013 New Requirement #1635 User should be able to send pharmacy orders to issue drugs to a patient from dashboard
	public InventoryDrug getDrugByName(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(InventoryDrug.class, "drug")
				.add(Restrictions.eq("drug.name", name));
		return (InventoryDrug) criteria.uniqueResult();
	}


    public List<Regimen> getRegimens(boolean voided) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Regimen.class,"regimen");
        if(!voided){
            criteria.add(Restrictions.eq("voided",voided));
        }
        return  criteria.list();
    }

    @Override
    public PatientRegimen createPatientRegimen(PatientRegimen patientRegimen) {
        if (patientRegimen == null){
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(patientRegimen);
        return patientRegimen;
    }

    @Override
    public PatientRegimen updatePatientRegimen(PatientRegimen patientRegimen) {
        if (patientRegimen == null){
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(patientRegimen);
        return patientRegimen;
    }

    @Override
    public void voidPatientRegimen(PatientRegimen patientRegimen) {
	    sessionFactory.getCurrentSession().delete(patientRegimen);
    }

    @Override
    public Regimen createRegimen(Regimen regimen) {
	   if (regimen == null) {
	       return null;
       }
	   sessionFactory.getCurrentSession().saveOrUpdate(regimen);
        return regimen;
    }

    @Override
    public Regimen updateRegimen(Regimen regimen) {
        if (regimen == null) {
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(regimen);
        return regimen;
    }

    @Override
    public void voidRegimen(Regimen regimen) {
	    sessionFactory.getCurrentSession().delete(regimen);

    }

    @Override
    public Cycle createCycle(Cycle cycle) {
        if(cycle == null){
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(cycle);
        return cycle;
    }

    @Override
    public Cycle updateCycle(Cycle cycle) {
        if(cycle == null){
            return null;
        }
        sessionFactory.getCurrentSession().saveOrUpdate(cycle);
        return cycle;
    }

    @Override
    public List<PatientRegimen> getPatientRegimen(Patient patient, Regimen regimen, String tag, Integer cycle) {
	    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PatientRegimen.class);
	    if (patient != null){
	        criteria.add(Restrictions.eq("patientId", patient));
        }
        if (regimen != null){
            criteria.add(Restrictions.eq("regimenId",regimen));
        }
        if (!StringUtils.isBlank("tag")) {
            criteria.add(Restrictions.eq("tag",tag));

        }
        if (cycle != null) {
            criteria.add(Restrictions.eq("cycle",cycle));

        }

        return criteria.list();
    }


	public List<Concept> getDrugFrequency() throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Concept.class, "con");
		ConceptClass conClass = Context.getConceptService()
				.getConceptClassByName("Frequency");
		criteria.add(Restrictions.eq("con.conceptClass", conClass));
		return criteria.list();
	}
	
	public InventoryDrugFormulation getDrugFormulationById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession()
		        .createCriteria(InventoryDrugFormulation.class, "drugFormulation")
		        .add(Restrictions.eq("drugFormulation.id", id));
		return (InventoryDrugFormulation) criteria.uniqueResult();
	}

	public List<InventoryStoreDrugPatient> getAllIssueByDateRange(String startDate, String endDate) {
		List<InventoryStoreDrugPatient> inventoryStoreDrugPatients= new ArrayList<InventoryStoreDrugPatient>();
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(InventoryStoreDrugPatient.class);

		String today = formatterExt.format(new Date());

		if (!StringUtils.isBlank(startDate) && !StringUtils.isBlank(endDate)){
			String startFromDate = startDate + " 00:00:00";
			String endAtDate = endDate + " 23:59:59";
			try {
				criteria.add(Restrictions.and(
						Restrictions.ge("createdOn",formatter.parse(startFromDate)),
						Restrictions.le("createdOn",formatter.parse(endAtDate))
				));
				inventoryStoreDrugPatients.addAll(criteria.list());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {

			String startFromDate = today + " 00:00:00";
			String endAtDate = today + " 23:59:59";
			try {
				criteria.add(Restrictions.and(
						Restrictions.ge("createdOn",formatter.parse(startFromDate)),
						Restrictions.le("createdOn",formatter.parse(endAtDate))
				));
				inventoryStoreDrugPatients.addAll(criteria.list());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		return inventoryStoreDrugPatients;
	}

}
