package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;

import java.io.Serializable;

public class PatientRegimen extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String medication;
    private String dosingUnit;
    private String dose;
    private Concept route;
    private String comment;
    private String tag;
    private Cycle cycleId;
    private Concept dispenseStatus;
    private String uuid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosingUnit() {
        return dosingUnit;
    }

    public void setDosingUnit(String dosingUnit) {
        this.dosingUnit = dosingUnit;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Concept getRoute() {
        return route;
    }

    public void setRoute(Concept route) {
        this.route = route;
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

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Concept getDispenseStatus() {
        return dispenseStatus;
    }

    public void setDispenseStatus(Concept dispenseStatus) {
        this.dispenseStatus = dispenseStatus;
    }
}
