package org.openmrs.module.hospitalcore.model;

import org.apache.commons.lang.StringUtils;
import org.openmrs.Patient;

import java.io.Serializable;
import java.util.Set;

public class PatientRegimen implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Patient patientId;
    private Regimen regimenId;
    private InventoryDrug drugId;
    private String name;
    private InventoryDrugUnit unit;
    private Set<InventoryDrugFormulation> formulations;
    private Integer dose;
    private String route;
    private String comment;
    private String tag;
    private Integer cycle;

    public Integer getVoided() {
        return voided;
    }

    public void setVoided(Integer voided) {
        this.voided = voided;
    }

    private Integer voided;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getNameShort() {
        return StringUtils.isNotBlank(name) && name.length() > 30 ?name.substring(0,30)+"..." : name;
    }
    public void setName(String name) {
        this.name = name;
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

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Regimen getRegimenId() {
        return regimenId;
    }

    public void setRegimenId(Regimen regimenId) {
        this.regimenId = regimenId;
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

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }
}
