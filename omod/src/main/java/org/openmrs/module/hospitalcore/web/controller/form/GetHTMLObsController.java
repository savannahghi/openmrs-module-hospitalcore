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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreGetHTMLObsController")
@RequestMapping("/module/hospitalcore/getHTMLObs.form")
public class GetHTMLObsController {

	@RequestMapping(method = RequestMethod.GET)
	public String getHTMLObs(
			@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("title") String title,
			@RequestParam("data") String data,
			Model model) {
		Concept concept = Context.getConceptService().getConcept(name);
		if (concept != null) {

			model.addAttribute("obsName", name);
			data = data.replace("\"", "'");
			model.addAttribute("data", data);
			title = title.replace("\"", "'");
			model.addAttribute("title", title);
			if (concept.getDatatype().getName().equalsIgnoreCase("text")) {
				if (type.equalsIgnoreCase("textbox")) {
					model.addAttribute("type", "text");
				} else if (type.equalsIgnoreCase("textarea")) {
					model.addAttribute("type", "textarea");
				}
			} else if (concept.getDatatype().getName()
					.equalsIgnoreCase("numeric")) {
				model.addAttribute("type", "number");
			} else if (concept.getDatatype().getName()
					.equalsIgnoreCase("datetime")) {
				model.addAttribute("type", "datetime");
			} else if (concept.getDatatype().getName()
					.equalsIgnoreCase("coded")) {
				Set<String> options = new HashSet<String>();
				for (ConceptAnswer ca : concept.getAnswers()) {
					Concept c = ca.getAnswerConcept();
					options.add(c.getName().getName());
				}

				for (ConceptSet cs : concept.getConceptSets()) {
					Concept c = cs.getConcept();
					options.add(c.getName().getName());
				}

				model.addAttribute("options", getSortedOptionNames(options));
				if (!type.equalsIgnoreCase("textbox")) {
					model.addAttribute("type", type);
				} else {
					model.addAttribute("type", "selection");
				}
			}
		}

		return "/module/hospitalcore/form/getHTMLObs";
	}

	private List<String> getSortedOptionNames(Set<String> options) {
		List<String> names = new ArrayList<String>();
		names.addAll(options);
		Collections.sort(names);
		return names;
	}
}
