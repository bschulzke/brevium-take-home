package org.example.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Appointment {

  private final int doctorId;
  private final int personId;
  private final ZonedDateTime appointmentTime;
  private final boolean isNewPatientAppointment;

  public Appointment(int doctorId, int personId, ZonedDateTime appointmentTime, boolean isNewPatientAppointment) {
    this.doctorId = doctorId;
    this.personId = personId;
    this.appointmentTime = getUTC(appointmentTime);
    this.isNewPatientAppointment = isNewPatientAppointment;
  }

  private ZonedDateTime getUTC(ZonedDateTime appointmentTime) {
    return ZonedDateTime.ofInstant(appointmentTime.toInstant(), ZoneId.of("UTC"));
  }

  public int getDoctorId() {
    return doctorId;
  }

  public int getPersonId() {
    return personId;
  }

  public ZonedDateTime getAppointmentTime() {
    return appointmentTime;
  }

  public boolean isNewPatientAppointment() {
    return isNewPatientAppointment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Appointment that = (Appointment) o;
    return doctorId == that.doctorId && personId == that.personId && isNewPatientAppointment == that.isNewPatientAppointment && appointmentTime.equals(that.appointmentTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doctorId, personId, appointmentTime, isNewPatientAppointment);
  }

}
