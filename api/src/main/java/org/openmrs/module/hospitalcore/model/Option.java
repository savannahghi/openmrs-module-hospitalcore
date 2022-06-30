package org.openmrs.module.hospitalcore.model;

import org.openmrs.Concept;

public class Option {
    private Integer id;
    private String label;
    private String uuid;

    public Option() {
    }

    public Option(int id, String label) {
        this(id, null, label);
    }

    public Option(int id, String uuid, String label) {
        this.id = id;
        this.label = label;
        this.setUuid(uuid);
    }

    public Option(Concept answerConcept) {
        this.id = answerConcept.getConceptId();
        this.label = answerConcept.getDisplayString();
        this.uuid = answerConcept.getUuid();
    }

    public Option(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31;
        hash = hash * 31 + (this.id == null ? 0 : this.id.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Option)) {
            return false;
        }
        Option otherOption = (Option) obj;
        return this.id.equals(otherOption.id);
    }

}
