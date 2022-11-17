package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;

import java.io.Serializable;
import java.util.Set;

public class PatientRegimen extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private InventoryDrug drugId;
    private InventoryDrugUnit unit;
    private Set<InventoryDrugFormulation> formulations;
    private Integer dose;
    private String route;
    private String comment;
    private String tag;
    private Cycle cycleId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public InventoryDrugUnit getUnit() {
        return unit;
    }
    public void setUnit(InventoryDrugUnit unit) {
        this.unit = unit;
    }
    public Set<InventoryDrugFormulation> getFormulations() {
        return formulations;
    }
    public void setFormulations(Set<InventoryDrugFormulation> formulations) {
        this.formulations = formulations;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public InventoryDrug getDrugId() {
        return drugId;
    }

    public void setDrugId(InventoryDrug drugId) {
        this.drugId = drugId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Cycle getCycleId() {
        return cycleId;
    }

    public void setCycleId(Cycle cycleId) {
        this.cycleId = cycleId;
    }
}
