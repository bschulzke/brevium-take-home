package org.example.model;

import org.example.dto.AppointmentDTO;
import org.example.dto.ScheduleDTO;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class Schedule {

  Set<Appointment> appointments;

  public Schedule(ScheduleDTO dto) {
    appointments = new HashSet<>();
    for (AppointmentDTO aptDto : dto.getAppointments()) {
      appointments.add(new Appointment(aptDto));
    }
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

  public boolean docIsAvailable(ZonedDateTime date, int doctorId) {
    Set<Appointment> docAppointments = getDoctorAppointments(doctorId);
    boolean available = true;
    for (Appointment appt : docAppointments) {
      if (date.equals(appt.getAppointmentTime())) {
        available = false;
        break;
      }
    }
    return available;
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

  public boolean appointmentsTooClose(ZonedDateTime date, int personId) {
    // TODO: Flesh out stub
    return false;
  }

  @Override
  public String toString() {
    return "Schedule{" +
        "appointments=" + appointments +
        '}';
  }
}
