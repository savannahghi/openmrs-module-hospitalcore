package org.openmrs.module.hospitalcore.model;

import org.openmrs.Role;

import java.io.Serializable;
import java.util.Date;

public class WaiverType implements Serializable {
    int Id;
    String name;
    String description;
    Role userRole;
    Boolean retired = false;
    Date createdDate;
    Date retiredDate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    @Override
    public String toString() {
        return "WaiverType{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userRole=" + userRole +
                ", retired=" + retired +
                ", createdDate=" + createdDate +
                ", retiredDate=" + retiredDate +
                '}';
    }
}
