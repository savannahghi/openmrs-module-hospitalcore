package org.openmrs.module.hospitalcore.task;

import org.openmrs.Concept;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hospitalcore.BillingService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class UpdateBillableItems extends AbstractTask {
  private static final Logger log = LoggerFactory.getLogger(UpdateBillableItems.class);
  @Override
  public void execute() {

    if (!isExecuting) {
      if (log.isDebugEnabled()) {
        log.debug("Updating the billable service items with the associated department");
      }

      startExecuting();
      try {
        //do all the work here
        performBillableItemsUpdate();

      }
      catch (Exception e) {
        log.error("Error while updating the billable items ", e);
      }
      finally {
        stopExecuting();
      }
    }

  }

  private void performBillableItemsUpdate() {
    BillingService billingService = Context.getService(BillingService.class);
    List<PatientServiceBillItem> emptyDepartmentItems = billingService.getPatientBillableServicesItemsWithNoDepartment();
    for(PatientServiceBillItem item :emptyDepartmentItems ) {
      if(item.getDepartment() == null) {
        billingService.updateBillItems(updatePatientServiceBillItem(item));
      }
    }
  }

  private EhrDepartment getDepartment(String department) {
    return Context.getService(HospitalCoreService.class).getDepartmentByName(department);
  }

  private PatientServiceBillItem updatePatientServiceBillItem(PatientServiceBillItem patientServiceBillItem) {
    Concept conceptWithService = null;
    EhrDepartment ehrDepartment = null;
    ConceptService conceptService = Context.getConceptService();
   if(patientServiceBillItem != null && patientServiceBillItem.getService() != null) {
     conceptWithService = conceptService.getConcept(patientServiceBillItem.getService().getConceptId());
     //check which class or category this concept belong to, if it gets the required point it return its department
     if(conceptWithService != null) {
       if(conceptWithService.getConceptClass().equals(conceptService.getConceptClassByUuid("8d4907b2-c2cc-11de-8d13-0010c6dffd0f"))) {
         ehrDepartment = getDepartment("Laboratory");
       }
       else if(conceptWithService.getConceptClass().equals(conceptService.getConceptClassByUuid("8d490bf4-c2cc-11de-8d13-0010c6dffd0f"))) {
         ehrDepartment = getDepartment("Procedure");
       }
       else if(conceptWithService.getConceptClass().equals(conceptService.getConceptClassByUuid("8caa332c-efe4-4025-8b18-3398328e1323"))) {
         ehrDepartment = getDepartment("Radiology");
       }
       else if(getRegistrationConcepts().contains(conceptWithService)) {
         ehrDepartment = getDepartment("Registration");
       }
       else {
         log.info("This concept is NOT mapped>>"+conceptWithService.getConceptId());
       }
     }
     if(ehrDepartment != null) {
       //update the item object
       patientServiceBillItem.setDepartment(ehrDepartment);
       //update the item in the database

     }
   }
    return patientServiceBillItem;
  }

  private List<Concept> getRegistrationConcepts() {
    ConceptService conceptService = Context.getConceptService();
    return Arrays.asList(
            conceptService.getConceptByUuid("19e1f7a9-52b4-4975-804d-5c74445be316"),
            conceptService.getConceptByUuid("430fc46d-94bb-4fbc-b7bd-894b7cc98058"),
            conceptService.getConceptByUuid("9ecd0c9e-37ab-4449-923b-8cfebaecd1ce"),
            conceptService.getConceptByUuid("81f2e941-d724-4794-98bf-8764b593c838"),
            conceptService.getConceptByUuid("3aae62a7-dfb6-43f1-9516-32ed7cefc039"),
            conceptService.getConceptByUuid("81cfb05a-998d-46f6-b0b0-8d26e27840ae"),
            conceptService.getConceptByUuid("cecc12d2-4308-4567-9bd1-92011b1648df"),
            conceptService.getConceptByUuid("caf177ab-8d96-45bb-8ab4-f66507f11b2b"),
            conceptService.getConceptByUuid("a6deb3a8-e9a6-41fc-a221-469b8a364f9b")
    );
  }
}
