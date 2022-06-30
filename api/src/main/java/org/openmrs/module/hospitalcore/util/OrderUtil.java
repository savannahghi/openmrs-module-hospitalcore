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

import org.apache.commons.lang.StringUtils;
import org.openmrs.EncounterType;
import org.openmrs.OrderType;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;

import java.util.Iterator;
import java.util.List;

public class OrderUtil {
	
	private static final String RADIOLOGY_ORDER_TYPE = "billing.encounterType.radiology";
	
	public static void saveRadiologyOrder(PatientServiceBillItem item) {
		String radiologyEncounterTypeName = GlobalPropertyUtil.getString(RADIOLOGY_ORDER_TYPE, null);
		if (!StringUtils.isBlank(radiologyEncounterTypeName)) {
			EncounterType et = Context.getEncounterService().getEncounterType(radiologyEncounterTypeName);
			//ghanshyam 27/06/2012 tag DLS_DEAD_LOCAL_STORE code Encounter encounter = new Encounter();
			/*
			if(et!=null){
				Encounter encounter = new Encounter();
				
			}*/
		}
	}
	
	public static OrderType getOrderTypeByName(String orderTypeName) {
		
		OrderType orderType = null;
		List<OrderType> allOrderTypes = Context.getOrderService().getOrderTypes(false);
		Iterator<OrderType> allOrderTypesIterator = allOrderTypes.iterator();
		
		while (allOrderTypesIterator.hasNext()) {
			OrderType orderTypeTemp = allOrderTypesIterator.next();
			if (orderTypeTemp.getName().equals(orderTypeName)) {
				orderType = Context.getOrderService().getOrderType(orderTypeTemp.getId());
			}
		}
		
		return orderType;
	}
	
}
