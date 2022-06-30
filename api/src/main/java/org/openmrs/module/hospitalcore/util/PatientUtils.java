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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;

public class PatientUtils {
	
	public static final String MODULE_ID = "hospitalcore.";
	
	public final static String PATIENT_ATTRIBUTE_CATEGORY = "Payment Category";
	
//	public final static String PATIENT_ATTRIBUTE_BPL_NUMBER = "BPL Number";
	
//	public final static String PATIENT_ATTRIBUTE_RSBY_NUMBER = "RSBY Number";
	
	public final static String PATIENT_ATTRIBUTE_EXEMPTION_CATEGORY = "Exemption Number";
	
	public final static String PATIENT_ATTRIBUTE_WAIVER_CATEGORY = "Waiver Number";
	
	public final static String PATIENT_AGE_CATEGORY = MODULE_ID + "ageCategory";
	
	/**
	 * Get patient category printout based on patient's category
	 * 
	 * @param patient
	 * @return
	 */
	
	//ghanshyam 16-06-2012 Bug #44 OPD Dashboard/ Patient category,Temporary category is not being displayed
	public static String getPatientCategory(Patient patient) {
		//String category = "";
		String patientCategory = getPatientAttribute(patient, PATIENT_ATTRIBUTE_CATEGORY);
		/*
		if (!StringUtils.isBlank(patientCategory)) {
			if (patientCategory.contains("General"))
				category += "General";
			
			else if (patientCategory.contains("Child Less Than 5 yr"))
				category += "Child Less Than 5 yr";
			
			else if (patientCategory.contains("NHIF"))
				category += "NHIF";
			
			else if (patientCategory.contains("Waiver"))
				category += "Waiver";
			
			else if (patientCategory.contains("CCC"))
				category += "CCC";
			
			else if (patientCategory.contains("Expectant Mother"))
				category += "Expectant Mother";
			
		}
		*/
		return patientCategory;
	}
	
	/**
	 * Get the fullname of patient
	 * 
	 * @param patient
	 * @return
	 */
	public static String getFullName(Patient patient) {
		String fullName = "";
		
		if (!StringUtils.isBlank(patient.getGivenName())) {
			fullName += patient.getGivenName() + " ";
		}
		
		if (!StringUtils.isBlank(patient.getFamilyName())) {
			fullName += patient.getFamilyName() + " ";
		}
		
		if (!StringUtils.isBlank(patient.getMiddleName())) {
			fullName += patient.getMiddleName();
		}
		
		//fullName = StringUtils.trim(fullName);
		return fullName;
	}
	
	/**
	 * Get the age category based on patient's age
	 * 
	 * @param patient
	 * @return
	 */
	public static String getAgeCategory(Patient patient) {
		String ageCategories = GlobalPropertyUtil.getString(PATIENT_AGE_CATEGORY, "null");
		try {
			String[] categories = ageCategories.split(";");
			for (String category : categories) {
				String[] parts = category.split(":");
				String categoryName = parts[1];
				String categoryCondition = parts[0];
				String[] conditions = categoryCondition.split("-");
				Integer lower = Integer.parseInt(conditions[0]);
				Integer upper = Integer.parseInt(conditions[1]);
				if ((lower <= patient.getAge()) && (patient.getAge() <= upper))
					return categoryName;
			}
		}
		catch (Exception e) {
			System.out.println("Error while generating age category!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Get patient attribute
	 * 
	 * @param patient
	 * @param attributeNameType
	 * @return
	 */
	public static String getPatientAttribute(Patient patient, String attributeNameType) {
		String value = null;
		PersonAttributeType pat = Context.getPersonService().getPersonAttributeTypeByName(attributeNameType);
		//April 30th 2014: Thai Chuong temporary try/catch this to keep going on the requirement
		//The pending issue: Could not get Payment Category attribute 
        		PersonAttribute pa = patient.getAttribute(pat);
        		if (pa != null) {
        			value = pa.getValue();
        		}
		return value;
	}
	
	public static void removePatientAttribute(Patient patient, String attributeNameType) {
		PersonAttributeType pat = Context.getPersonService().getPersonAttributeTypeByName(attributeNameType);
		PersonAttribute pa = patient.getAttribute(pat);
		patient.removeAttribute(pa);
		Context.getPatientService().savePatient(patient);
	}
	
	public static Map<String, String> getAttributes(Patient patient) {
		Map<String, String> attributes = new HashMap<String, String>();
		
		for (String key : patient.getAttributeMap().keySet()) {
			attributes.put(patient.getAttributeMap().get(key).getAttributeType().getName(),
			    patient.getAttributeMap().get(key).getValue());
		}
		
		// get last encounter
		List<EncounterType> types = new ArrayList<EncounterType>();
		EncounterType reginit = Context.getEncounterService().getEncounterType(1);
		types.add(reginit);
		EncounterType revisit = Context.getEncounterService().getEncounterType(2);
		types.add(revisit);
		Encounter lastVisit = Context.getService(HospitalCoreService.class).getLastVisitEncounter(patient, types);
		
		if (lastVisit != null) {
			for (Obs obs : lastVisit.getAllObs()) {
				if (!obs.isVoided()) {
					if (obs.getConcept().getDatatype().getName().equalsIgnoreCase("Coded")) {
						//ghanshyam 8-august-2012 Bug #332 [Billing] java.lang.NullPointerException when searching for bill id(like 7749,7764,8000 etc)
						String str=" ";
						if (obs.getValueCoded()!=null){
							str=obs.getValueCoded().getName().getName();
						}
						attributes.put(obs.getConcept().getName().getName(),str);
					} 
				   else if (obs.getConcept().getDatatype().getName().equalsIgnoreCase("Text")) {
					 //ghanshyam 8-august-2012 [Billing] java.lang.NullPointerException when searching for bill id(like 7749,7764,8000 etc)
						String str=" ";
						if (obs.getValueText()!=null){
							str=obs.getValueText();
						}
						attributes.put(obs.getConcept().getName().getName(), str);
					}
					
				}
			}
		}
		
		return attributes;
	}
	
	/**
	 * Estimate patient age
	 * 
	 * @param patient
	 * @return
	 */
	public static String estimateAge(Patient patient) {
		return estimateAge(patient.getBirthdate());
	}
	
	/**
	 * Estimate age using birthdate
	 * 
	 * @param birthdate
	 * @return
	 * @throws ParseException
	 */
	public static String estimateAge(Date date) {
		
		String age = "~";
		// new date
		Calendar cal = Calendar.getInstance();
		
		// set to old date
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		
		Date date2 = cal.getTime();
		int yearNew = cal.get(Calendar.YEAR);
		int yearOld = cal2.get(Calendar.YEAR);
		int monthNew = cal.get(Calendar.MONTH);
		int monthOld = cal2.get(Calendar.MONTH);
		int dayNew = cal.get(Calendar.DAY_OF_MONTH);
		int dayOld = cal2.get(Calendar.DAY_OF_MONTH);
		int maxDayInOldMonth = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int yearDiff = yearNew - yearOld;
		int monthDiff = monthNew - monthOld;
		int dayDiff = dayNew - dayOld;
		
		int ageYear = yearDiff, ageMonth = monthDiff, ageDay = dayDiff;
		
		if (monthDiff < 0) {
			ageYear--;
			ageMonth = 12 - Math.abs(monthDiff);
			
		}
		if (dayDiff < 0) {
			ageMonth--;
			if (ageMonth < 0) {
				ageYear--;
				ageMonth = 12 - Math.abs(ageMonth);
			}
			ageDay = maxDayInOldMonth - dayOld + dayNew;
		}
		
		
		if (ageYear >= 1) {
			
			age += ageYear;
			if (ageMonth >= 6) {
				age += ".5";
			}
			if (ageYear == 1) {
				age += " year";
			} else {
				age += " years";
			}
		} else if (ageYear <= 0) {
			if (ageMonth >= 1) {
				if (ageMonth == 1) {
					age += ageMonth + " month ";
				} else {
					age += ageMonth + " months ";
				}
			}
			if (ageMonth <= 0) {
				
				if ((ageDay == 1) || (ageDay == 0)) {
					age += ageDay + " day ";
				} else {
					age += ageDay + " days ";
				}
			}
		}
		return age;
	}
	
      public static String estimateAgeInYear(Date date) {
		
		// new date
		Calendar cal = Calendar.getInstance();
		
		// set to old date
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
	
		int yearNew = cal.get(Calendar.YEAR);
		int yearOld = cal2.get(Calendar.YEAR);
		
		int yearDiff = yearNew - yearOld;
		
		String ageYear = String.valueOf(yearDiff);
		return ageYear;
	}

	public static String estimateAgeCategory(Date date) {

		String ageCategory = "";
		// new date
		Calendar cal = Calendar.getInstance();

		// set to old date
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);

		Date date2 = cal.getTime();
		int yearNew = cal.get(Calendar.YEAR);
		int yearOld = cal2.get(Calendar.YEAR);
		int monthNew = cal.get(Calendar.MONTH);
		int monthOld = cal2.get(Calendar.MONTH);
		int dayNew = cal.get(Calendar.DAY_OF_MONTH);
		int dayOld = cal2.get(Calendar.DAY_OF_MONTH);
		int maxDayInOldMonth = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);

		int yearDiff = yearNew - yearOld;
		int monthDiff = monthNew - monthOld;
		int dayDiff = dayNew - dayOld;

		int ageYear = yearDiff, ageMonth = monthDiff, ageDay = dayDiff;

		if (monthDiff < 0) {
			ageYear--;
			ageMonth = 12 - Math.abs(monthDiff);

		}
		if (dayDiff < 0) {
			ageMonth--;
			if (ageMonth < 0) {
				ageYear--;
				ageMonth = 12 - Math.abs(ageMonth);
			}
			ageDay = maxDayInOldMonth - dayOld + dayNew;
		}


		if (ageYear < 13) {
			ageCategory="CHILD";
		} else if(ageYear > 12 && ageYear < 20){
			ageCategory="ADOLESCENT";
		}
		else if(ageYear > 19 && ageYear < 60){
			ageCategory="ADULT";
		}
		else if(ageYear > 59){
			ageCategory="SENIOR CITIZEN";
		}
		return ageCategory;
	}
}
