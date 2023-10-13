package org.example.request;

import java.time.ZonedDateTime;
import java.util.Set;

public class AppointmentRequest {

  int requestId;
  int personId;
  Set<ZonedDateTime> preferredDays;

  Set<Integer> preferredDoctors;

  public AppointmentRequest(int requestId, int personId, Set<ZonedDateTime> preferredDays, Set<Integer> preferredDoctors) {
    this.requestId = requestId;
    this.personId = personId;
    this.preferredDays = preferredDays;
    this.preferredDoctors = preferredDoctors;
  }

}
