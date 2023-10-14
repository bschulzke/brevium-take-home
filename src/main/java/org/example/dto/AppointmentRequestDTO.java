package org.example.dto;

import java.util.List;

public class AppointmentRequestDTO {

  int requestId;
  int personId;
  List<Integer> preferredDocs;
  List<String> preferredDays;
  boolean isNew;

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
  }

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public List<Integer> getPreferredDocs() {
    return preferredDocs;
  }

  public void setPreferredDocs(List<Integer> preferredDocs) {
    this.preferredDocs = preferredDocs;
  }

  public List<String> getPreferredDays() {
    return preferredDays;
  }

  public void setPreferredDays(List<String> preferredDays) {
    this.preferredDays = preferredDays;
  }
}
