package org.example.service;

import org.example.model.Appointment;
import org.example.model.Schedule;

import java.util.HashSet;

public class ScheduleService {

  private String authtoken;

  public ScheduleService(String authtoken) {
    this.authtoken = authtoken;
  }

  public void start() {
    // TODO: Make API call
  }

  public void stop() {
    // TODO: Make API call
  }

  public Appointment getAppointmentRequest() {
    // TODO: Make API call, deserialize JSON body into AppointmentRequest
    return new Appointment(0, 0, null, false);
  }

  public Schedule getSchedule() {
    // TODO: Make API call, deserialize JSON body into Schedule
    return new Schedule(new HashSet<>());
  }

  public void postAppointmentToSchedule(Appointment appointment) {
    // TODO: Serialize appointment into JSON for body, make API call
  }

}
