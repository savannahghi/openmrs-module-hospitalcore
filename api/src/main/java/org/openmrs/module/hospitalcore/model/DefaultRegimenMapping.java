package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;

import java.io.Serializable;

public class DefaultRegimenMapping extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String medication;
    private String dose;
    private String dosingUnit;
    private Concept route;
    private String tag;
    private RegimenType typeId;

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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDosingUnit() {
        return dosingUnit;
    }

    public void setDosingUnit(String dosingUnit) {
        this.dosingUnit = dosingUnit;
    }

    public Concept getRoute() {
        return route;
    }

    public void setRoute(Concept route) {
        this.route = route;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public RegimenType getTypeId() {
        return typeId;
    }

    public void setTypeId(RegimenType typeId) {
        this.typeId = typeId;
    }
}
