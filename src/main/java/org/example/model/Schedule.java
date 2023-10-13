package org.example.model;

import java.time.ZonedDateTime;
import java.util.Map;

public class Schedule {

  Map<ZonedDateTime, Appointment> appointments;

  public Schedule(Map<ZonedDateTime, Appointment> appointments) {
    this.appointments = appointments;
  }

  public void addAppointment(Appointment appointment) throws AppointInvalidException {
    validateAppointment(appointment);
    this.appointments.put(appointment.getAppointmentTime(), appointment);
  }

  private void validateAppointment(Appointment appointment) throws AppointInvalidException {
    if (appointment.getAppointmentTime().getMinute() != 0 || appointment.getAppointmentTime().getSecond() != 0) {
      throw new AppointInvalidException("Appointments can only be scheduled on the hour");
    }
    if (this.appointments.containsKey(appointment.getAppointmentTime())) {
      throw new AppointInvalidException("Appointment date already taken");
    }
    if (appointment.getAppointmentTime().getHour() < 8 || appointment.getAppointmentTime().getHour() > 16) {
      throw new AppointInvalidException("Appointment hour must be between 8:00 am and 4:00 pm");
    }
  }

}
