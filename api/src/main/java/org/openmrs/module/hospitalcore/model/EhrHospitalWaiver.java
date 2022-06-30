package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseChangeableOpenmrsMetadata;
import org.openmrs.Patient;

import java.util.Date;

public class EhrHospitalWaiver extends BaseChangeableOpenmrsMetadata {

  private Integer waiverId;
  private Patient patient;

  public Integer getWaiverId() {
    return waiverId;
  }

  public void setWaiverId(Integer waiverId) {
    this.waiverId = waiverId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  private String gender;
  private Date birthdate;
  private String name;
  private Integer status;

  @Override
  public Integer getId() {
    return waiverId;
  }

  @Override
  public void setId(Integer waiverId) {
    setWaiverId(waiverId);
  }
}
