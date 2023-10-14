package org.example.model;

import org.example.dto.AppointmentDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

  private int doctorId;
  private int personId;
  private ZonedDateTime appointmentTime;
  private boolean isNewPatientAppointment;

  public Appointment(AppointmentDTO dto) {
    DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss a z");
    this.personId = dto.getPersonId();
    this.isNewPatientAppointment = dto.isNewPatientAppointment();
    this.appointmentTime = ZonedDateTime.parse(dto.getAppointmentTime(), formatter);
  }

  public int getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(int doctorId) {
    this.doctorId = doctorId;
  }

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public ZonedDateTime getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(ZonedDateTime appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public boolean isNewPatientAppointment() {
    return isNewPatientAppointment;
  }

  public void setNewPatientAppointment(boolean newPatientAppointment) {
    isNewPatientAppointment = newPatientAppointment;
  }
}
