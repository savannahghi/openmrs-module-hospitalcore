package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import java.io.Serializable;
import java.util.Set;

public class Regimen extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Patient patient;
    private RegimenType regimenType;
    private Set<Cycle> cycles;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Cycle> getCycles() {
        return cycles;
    }

    public void setCycles(Set<Cycle> cycles) {
        this.cycles = cycles;
    }

    public RegimenType getRegimenType() {
        return regimenType;
    }

    public void setRegimenType(RegimenType regimenType) {
        this.regimenType = regimenType;
    }

    @Override
    public String toString() {
        return "Regimen{" +
                "id=" + id +
                ", regimenType=" + regimenType.getName() +
                ", regimenCycles=" + cycles != null ? cycles.toString() : "null" +
                '}';
    }
}
