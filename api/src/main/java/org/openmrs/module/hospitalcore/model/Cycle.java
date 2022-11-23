package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;
import java.io.Serializable;
import java.util.Set;

public class Cycle extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String summaryNotes;
    private String outcome;
    private String name;
    private String icon;
    private Regimen regimenId;
    private Boolean active;
    private Set<PatientRegimen> patientRegimens;



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummaryNotes() {
        return summaryNotes;
    }

    public void setSummaryNotes(String summaryNotes) {
        this.summaryNotes = summaryNotes;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Regimen getRegimenId() {
        return regimenId;
    }

    public void setRegimenId(Regimen regimenId) {
        this.regimenId = regimenId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<PatientRegimen> getPatientRegimens() {
        return patientRegimens;
    }

    public void setPatientRegimens(Set<PatientRegimen> patientRegimens) {
        this.patientRegimens = patientRegimens;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
