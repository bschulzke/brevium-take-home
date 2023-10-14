package org.example.model;

import org.example.dto.AppointmentRequestDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AppointmentRequest {

  int requestId;
  int personId;
  List<Integer> preferredDocs;
  Set<ZonedDateTime> preferredDays;
  boolean isNew;

  public AppointmentRequest(AppointmentRequestDTO dto) {
    DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
    this.requestId = dto.getRequestId();
    this.personId = dto.getPersonId();
    this.preferredDocs = dto.getPreferredDocs();
    this.isNew = dto.isNew();

    this.preferredDays = new HashSet<ZonedDateTime>();

    for (String date : dto.getPreferredDays()) {
      this.preferredDays.add(ZonedDateTime.parse(date, formatter));
    }
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

  public Set<ZonedDateTime> getPreferredDays() {
    return preferredDays;
  }

  public void setPreferredDays(Set<ZonedDateTime> preferredDays) {
    this.preferredDays = preferredDays;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }
}
