package org.openmrs.module.hospitalcore.model;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class OrderSet implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description ;
    private Date createdOn;
    private String createdBy;
    private Integer cycleCount;
    private Integer cycleLength;
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
        return !StringUtils.isBlank(name) &&  name.length() > 30 ? name.substring(0 , 30)+"..." : name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getCycleCount() {
        return cycleCount;
    }

    public void setCycleCount(Integer cycleCount) {
        this.cycleCount = cycleCount;
    }

    public Integer getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(Integer cycleLength) {
        this.cycleLength = cycleLength;
    }
}
