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


package org.openmrs.module.hospitalcore.web.controller.concept;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("HCSDiagnosisImporterController")
@RequestMapping("/module/hospitalcore/conceptImport.form")
public class DiagnosisImporterController {

// TODO: replace System.out and printStackTrace with log messages

	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(
			Model model) {
		model.addAttribute("uploadFile", new UploadFile());
		return "/module/hospitalcore/concept/uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(UploadFile uploadFile, BindingResult result, Model model) {
		if (result.hasErrors()) {
			//ghanshyam 25/06/2012 tag BC_IMPOSSIBLE_CAST code Error error = (Error) obj
			
			for (ObjectError obj : result.getAllErrors()) {
				ObjectError error = (ObjectError) obj;
				System.err.println("Error: " + error.toString()) ;
			}
			return "/module/hospitalcore/concept/uploadForm";
		}
		
		System.out.println("Begin importing");
		Integer diagnosisNo = 0;
		try {
			HospitalCoreService hcs = (HospitalCoreService) Context.getService(HospitalCoreService.class);
			diagnosisNo = hcs.importConcepts(uploadFile.getDiagnosisFile().getInputStream(), 
					uploadFile.getMappingFile().getInputStream(), 
					uploadFile.getSynonymFile().getInputStream());
			model.addAttribute("diagnosisNo", diagnosisNo);
			System.out.println("Diagnosis imported " + diagnosisNo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("fail", true);
			model.addAttribute("error", e.toString());
		}

		return "/module/hospitalcore/concept/uploadForm";
	}
}
