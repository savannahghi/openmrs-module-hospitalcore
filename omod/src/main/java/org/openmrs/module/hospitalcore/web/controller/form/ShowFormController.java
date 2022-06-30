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

package org.openmrs.module.hospitalcore.web.controller.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import java.lang.StringBuilder;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreShowFormController")
@RequestMapping("/module/hospitalcore/showForm.form")
public class ShowFormController {

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			@RequestParam("formId") Integer formId,
			@RequestParam("mode") String mode,
			@RequestParam(value = "encounterId", required = false) Integer encounterId,
			@RequestParam(value = "redirect", required = false) String redirect,
			Model model) {

		CoreForm form = Context.getService(HospitalCoreService.class)
				.getCoreForm(formId);
		model.addAttribute("mode", mode);
		model.addAttribute("form", form);

		if (mode.equalsIgnoreCase("edit")) {
			model.addAttribute("encounterId", encounterId);
			model.addAttribute("redirect", redirect);
		}

		// get values from encounter
		if (encounterId != null) {

			StringBuilder builder = new StringBuilder();
			Encounter encounter = Context.getEncounterService().getEncounter(
					encounterId);
			for (Obs obs : encounter.getAllObs()) {
				builder.append(obs.getConcept().getName().getName() + "=="
						+ getObsValue(obs) + "||");
			}
			model.addAttribute("values", builder.toString());
		}

		return "/module/hospitalcore/form/show";
	}

	/**
	 * Get value from observation
	 * @param obs
	 * @return
	 */
	private static String getObsValue(Obs obs) {
		Concept concept = obs.getConcept();
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) {
			return obs.getValueText();
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric")) {
			return obs.getValueNumeric().toString();
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			return obs.getValueCoded().getName().getName();
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Datetime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(obs.getValueDatetime());
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(
			HttpServletRequest request,
			@RequestParam("encounterId") Integer encounterId,
			@RequestParam(value = "redirect", required = false) String redirect,
			Model model) throws ParseException {

		Map<String, String> parameters = buildParameterList(request);
		Encounter encounter = Context.getEncounterService().getEncounter(
				encounterId);
		if (encounter != null) {
			for (String key : parameters.keySet()) {
				Concept concept = Context.getConceptService().getConcept(key);
				Obs obs = insertValue(encounter, concept, parameters.get(key));
				if (obs.getId() == null)
					encounter.addObs(obs);
			}
			Context.getEncounterService().saveEncounter(encounter);
			model.addAttribute("status", "success");
		}
		if (!StringUtils.isBlank(redirect))
			return redirect;
		else {
			return "/module/hospitalcore/form/enterForm";
		}
	}

	@SuppressWarnings("rawtypes")
	private Map<String, String> buildParameterList(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<String, String>();
		for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String parameterName = (String) e.nextElement();
			if (!parameterName.equalsIgnoreCase("id"))
				if (!parameterName.equalsIgnoreCase("mode"))
					if (!parameterName.equalsIgnoreCase("encounterId"))
						if (!parameterName.equalsIgnoreCase("redirect"))
							parameters.put(parameterName,
									request.getParameter(parameterName));

		}
		return parameters;
	}

	private Obs insertValue(Encounter encounter, Concept concept, String value)
			throws ParseException {

		Obs obs = getObs(encounter, concept);
		obs.setConcept(concept);
		if (concept.getDatatype().getName().equalsIgnoreCase("Text")) {
			value = value.replace("\n", "\\n");
			obs.setValueText(value);
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Numeric")) {
			obs.setValueNumeric(new Double(value));
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Datetime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			obs.setValueDatetime(sdf.parse(value));
		} else if (concept.getDatatype().getName().equalsIgnoreCase("Coded")) {
			Concept answerConcept = Context.getConceptService().getConcept(
					value);
			obs.setValueCoded(answerConcept);
		}
		return obs;
	}

	private Obs getObs(Encounter encounter, Concept concept) {
		for (Obs obs : encounter.getAllObs()) {
			if (obs.getConcept().equals(concept))
				return obs;
		}
		return new Obs();
	}
}
