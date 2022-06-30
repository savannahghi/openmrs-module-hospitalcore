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

package org.openmrs.module.hospitalcore.web.controller.ajax;

import org.apache.commons.collections.CollectionUtils;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller("HospitalcoreAjaxController")
public class AjaxController {

	/**
	 * Concept search autocomplete for form
	 * 
	 * @param name
	 * @param model
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/module/hospitalcore/ajax/autocompleteConceptSearch.htm", method = RequestMethod.GET)
	public String autocompleteConceptSearch(
			@RequestParam(value = "q", required = false) String name,
			Model model) {
		List<Concept> cws = Context.getConceptService().getConceptsByName(name,
				new Locale("en"), false);
		Set<String> conceptNames = new HashSet<String>();
		for (Concept word : cws) {
			String conceptName = word.getName().getName();
			conceptNames.add(conceptName);
		}
		List<String> concepts = new ArrayList<String>();
		concepts.addAll(conceptNames);
		Collections.sort(concepts, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		model.addAttribute("conceptNames", concepts);
		return "/module/hospitalcore/ajax/autocompleteConceptSearch";
	}

	@RequestMapping(value = "/module/hospitalcore/ajax/checkExistingForm.htm", method = RequestMethod.GET)
	public String checkExistingForm(
			@RequestParam("conceptName") String conceptName,			
			@RequestParam(value = "formId", required = false) Integer formId,
			Model model) {
		Concept concept = Context.getConceptService().getConcept(conceptName);
		boolean duplicatedFormFound = false;
		if (concept != null) {
			HospitalCoreService hcs = (HospitalCoreService) Context
					.getService(HospitalCoreService.class);
			List<CoreForm> forms = hcs.getCoreForms(conceptName);
			if (!CollectionUtils.isEmpty(forms)) {
				if (formId != null) {
					CoreForm form = hcs.getCoreForm(formId);
					if ((forms.size() == 1) && (forms.contains(form))) {

					} else {
						duplicatedFormFound = true;
					}
					if (forms.contains(form)) {
						forms.remove(form);
					}
				} else {					
					duplicatedFormFound = true;
				}

			}
			model.addAttribute("duplicatedFormFound", duplicatedFormFound);
			model.addAttribute("duplicatedForms", forms);
		}
		return "/module/hospitalcore/ajax/checkExistingForm";
	}
}
