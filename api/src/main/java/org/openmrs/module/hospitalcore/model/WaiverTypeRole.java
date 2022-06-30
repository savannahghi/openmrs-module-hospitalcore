package org.openmrs.module.hospitalcore.model;

public class WaiverTypeRole {
    int waiverTypeId;
    String waiverRoleName;

    public int getWaiverTypeId() {
        return waiverTypeId;
    }

    public void setWaiverTypeId(int waiverTypeId) {
        this.waiverTypeId = waiverTypeId;
    }

    public String getWaiverRoleName() {
        return waiverRoleName;
    }

    public void setWaiverRoleName(String waiverRoleName) {
        this.waiverRoleName = waiverRoleName;
    }
}
