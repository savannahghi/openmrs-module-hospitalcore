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

package org.openmrs.module.hospitalcore.web.controller.billsearch;

import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.model.PatientServiceBill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */
@Controller("HospitalcoreSearchBillController")
@RequestMapping("/module/hospitalcore/findBill.htm")
public class BillSearchController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchBill(@RequestParam("billId") String billId, Model model) {
		BillingService bs = Context.getService(BillingService.class);
		int receiptId = 0;
		try {
			receiptId = Integer.parseInt(billId);
		}
		catch (NumberFormatException e) {
			model.addAttribute("Found", "Cannot find bill");
			return "redirect:/module/billing/main.form";
		}
		
		/**
		 * June 20th 2012 - Thai Chuong supported getPatientServiceBillByReceiptId to solve issue
		 * #271
		 */
		PatientServiceBill patientServiceBill = bs.getPatientServiceBillByReceiptId(receiptId);
		
		if (null != patientServiceBill)
			//ghanshyam 24-july-2013 Bug #2259 Error on selecting Bill ID to search for a bill
			return "redirect:/module/billing/patientServiceBillForBD.list?patientId=" + patientServiceBill.getPatient().getId()
			        + "&billId=" + patientServiceBill.getPatientServiceBillId();
		
		model.addAttribute("Found", "Cannot find bill");
		return "redirect:/module/billing/main.form";
	}
}
