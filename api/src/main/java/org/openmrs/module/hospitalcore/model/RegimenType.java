package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseOpenmrsData;

import java.io.Serializable;
import java.util.Set;

public class RegimenType extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String  name;
    private Integer cycles;

    private Set<DefaultRegimenMapping> regimenMappings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCycles() {
        return cycles;
    }

    public void setCycles(Integer cycles) {
        this.cycles = cycles;
    }

    public Set<DefaultRegimenMapping> getRegimenMappings() {
        return regimenMappings;
    }

    public void setRegimenMappings(Set<DefaultRegimenMapping> regimenMappings) {
        this.regimenMappings = regimenMappings;
    }
}
