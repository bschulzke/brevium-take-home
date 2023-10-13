package org.example.model;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class Schedule {

  Set<Appointment> appointments;

  public Schedule(Set<Appointment> appointments) {
    this.appointments = appointments;
  }

  public void addAppointment(Appointment appointment) throws AppointmentInvalidException {
    validateAppointment(appointment);
    this.appointments.add(appointment);
  }

  private void validateAppointment(Appointment appointment) throws AppointmentInvalidException {
    ZonedDateTime date = appointment.getAppointmentTime();

    if (date.getMinute() != 0 || date.getSecond() != 0) {
      throw new AppointmentInvalidException("Appointments can only be scheduled on the hour");
    }
    if (date.getHour() < 8 || date.getHour() > 16) {
      throw new AppointmentInvalidException("Appointment hour must be between 8:00 am and 4:00 pm");
    }
    if (date.getYear() != 2021 || (date.getMonth() != Month.DECEMBER && date.getMonth() != Month.NOVEMBER)) {
      throw new AppointmentInvalidException("Date must be in the months of December or November, 2021");
    }
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      throw new AppointmentInvalidException("Date cannot be on a Sunday");
    }
  }

  public Set<Appointment> getDoctorAppointments(int doctorId) {
    Set<Appointment> doctorAppointments = new HashSet<>();
    for (Appointment appointment : appointments) {
      if (appointment.getDoctorId() == doctorId) {
        doctorAppointments.add(appointment);
      }
    }
    return doctorAppointments;
  }

  public Set<Appointment> getPatientAppointments(int personId) {
    Set<Appointment> doctorAppointments = new HashSet<>();
    for (Appointment appointment : appointments) {
      if (appointment.getPersonId() == personId) {
        doctorAppointments.add(appointment);
      }
    }
    return doctorAppointments;
  }

}
