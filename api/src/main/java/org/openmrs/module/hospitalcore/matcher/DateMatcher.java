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


package org.openmrs.module.hospitalcore.matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.PatientDashboardService;

public class DateMatcher implements Matcher<Patient> {
	private Date date;
	private Integer range;

	public DateMatcher(String date, Integer range) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		date = date + " 00:00:00";
		this.date = sdf.parse(date);
		this.range = range;
	}

	public boolean matches(Object obj) {
		Patient patient = (Patient) obj;		
		Date checkedDate = getLastVisitDate(patient);
		Date beforeDate;
		if(range==1){
			beforeDate = date;
		} else {
			beforeDate = calcDate(date, -range);
		}
		Date afterDate = calcDate(date, range);
		System.out.println("checkedDate ==> " + checkedDate);
		System.out.println("beforeDate ==> " + beforeDate);
		System.out.println("afterDate ==> " + afterDate);
		if (checkedDate != null) {
			if ((checkedDate.compareTo(beforeDate) >= 0)
					&& (checkedDate.compareTo(afterDate) <= 0)) {
				return true;
			}
		} else {
			System.out.println("Can't find the checked date of patient with id " + patient.getId());
		}
		return false;
	}

	private Date getLastVisitDate(Patient patient) {
		PatientDashboardService pds = Context
				.getService(PatientDashboardService.class);
		EncounterType encType = Context.getEncounterService().getEncounterType(
				"OPDENCOUNTER");
		List<Encounter> encounters = pds.getEncounter(patient, new Location(1),
				encType, null);
		System.out.println("encounters.size() ==> " + encounters.size());
		Encounter latestEncounter = getLatestEncounter(encounters);
		if (latestEncounter != null)
			return latestEncounter.getDateCreated();
		return null;
	}

	private Encounter getLatestEncounter(List<Encounter> encounters) {

		// get the smallest date
		Date d = new Date(Long.MIN_VALUE);
		Encounter latestEncounter = null;
		for (Encounter e : encounters) {
			if (d.before(e.getDateCreated())) {
				d = e.getDateCreated();
				latestEncounter = e;
			}
		}
		return latestEncounter;
	}

	private Date calcDate(Date date, Integer range) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, range);
		return c.getTime();
	}

}
