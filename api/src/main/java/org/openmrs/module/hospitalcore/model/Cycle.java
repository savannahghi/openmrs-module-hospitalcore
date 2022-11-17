package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;

import java.io.Serializable;

public class Cycle extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String summaryNotes;
    private String outcome;
    private Regimen regimenId;
    private Boolean active;


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
}
