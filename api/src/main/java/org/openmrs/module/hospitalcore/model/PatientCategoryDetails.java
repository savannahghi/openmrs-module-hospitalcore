package org.openmrs.module.hospitalcore.model;

import org.openmrs.BaseChangeableOpenmrsMetadata;
import org.openmrs.Patient;

import java.util.Date;

public class PatientCategoryDetails extends BaseChangeableOpenmrsMetadata {
  private Integer patientCategoryId;
  private Patient patient;
  private String patientCategory;
  private String payingCategory;
  private String insuranceDetails;

  public Integer getPatientCategoryId() {
    return patientCategoryId;
  }

  public void setPatientCategoryId(Integer patientCategoryId) {
    this.patientCategoryId = patientCategoryId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public String getPatientCategory() {
    return patientCategory;
  }

  public void setPatientCategory(String patientCategory) {
    this.patientCategory = patientCategory;
  }

  public String getPayingCategory() {
    return payingCategory;
  }

  public void setPayingCategory(String payingCategory) {
    this.payingCategory = payingCategory;
  }

  public String getInsuranceDetails() {
    return insuranceDetails;
  }

  public void setInsuranceDetails(String insuranceDetails) {
    this.insuranceDetails = insuranceDetails;
  }

  public String getWaiverNumber() {
    return waiverNumber;
  }

  public void setWaiverNumber(String waiverNumber) {
    this.waiverNumber = waiverNumber;
  }

  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }

  public String getNhifNumber() {
    return nhifNumber;
  }

  public void setNhifNumber(String nhifNumber) {
    this.nhifNumber = nhifNumber;
  }

  public String getVisitType() {
    return visitType;
  }

  public void setVisitType(String visitType) {
    this.visitType = visitType;
  }

  public String getVisitedRoom() {
    return visitedRoom;
  }

  public void setVisitedRoom(String visitedRoom) {
    this.visitedRoom = visitedRoom;
  }

  public String getRoomTypeVisited() {
    return roomTypeVisited;
  }

  public void setRoomTypeVisited(String roomTypeVisited) {
    this.roomTypeVisited = roomTypeVisited;
  }

  public String getFileNumber() {
    return fileNumber;
  }

  public void setFileNumber(String fileNumber) {
    this.fileNumber = fileNumber;
  }

  public String getLegalCase() {
    return legalCase;
  }

  public void setLegalCase(String legalCase) {
    this.legalCase = legalCase;
  }

  private String waiverNumber;
  private String studentNumber;
  private String nhifNumber;
  private String visitType;
  private String visitedRoom;
  private String roomTypeVisited;
  private String fileNumber;
  private String legalCase;

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  private Date createdOn;

  @Override
  public Integer getId() {
    return getPatientCategoryId();
  }

  @Override
  public void setId(Integer id) {
    setPatientCategoryId(id);
  }
}
