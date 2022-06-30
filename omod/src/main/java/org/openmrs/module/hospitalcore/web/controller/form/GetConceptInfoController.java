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

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreGetConceptInfoController")
@RequestMapping("/module/hospitalcore/getConceptInfo.form")
public class GetConceptInfoController {

	@RequestMapping(method = RequestMethod.GET)
	public String getInfo(@RequestParam("name") String name, Model model) {
		Concept concept = Context.getConceptService().getConcept(name);
		
		model.addAttribute("conceptDescription", concept.getDescription().getDescription());
		model.addAttribute("conceptDatatype", concept.getDatatype().getName());		

		return "/module/hospitalcore/form/getConceptInfo";
	}
}
