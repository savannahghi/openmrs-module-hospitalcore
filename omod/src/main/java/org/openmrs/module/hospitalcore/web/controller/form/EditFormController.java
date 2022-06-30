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

import java.util.Date;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("HospitalcoreEditFormController")
@RequestMapping("/module/hospitalcore/editForm.form")
public class EditFormController {
	
	@ModelAttribute("form")
	public CoreForm getForm(
			@RequestParam(value = "id", required = false) Integer id) {
		if (id != null) {
			CoreForm form = Context.getService(HospitalCoreService.class).getCoreForm(id);
			if (form != null)
				return form;
		}
		return new CoreForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			Model model) {		
		return "/module/hospitalcore/form/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveForm(@ModelAttribute("form") CoreForm form,
			Model model) {
		form.setCreatedOn(new Date());
		form.setCreatedBy(Context.getAuthenticatedUser());
		Context.getService(HospitalCoreService.class).saveCoreForm(form);
		return "redirect:/module/hospitalcore/listForm.form";
	}
}
