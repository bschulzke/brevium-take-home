package org.example.model;

import org.example.dto.AppointmentDTO;
import org.example.dto.ScheduleDTO;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Schedule {

  Set<Appointment> appointments;

  public Schedule(ScheduleDTO dto) {
    appointments = new HashSet<>();
    List<AppointmentDTO> appointmentDTOS = dto.getAppointments();
    for (AppointmentDTO aptDto : appointmentDTOS) {
      appointments.add(new Appointment(aptDto));
    }
  }

  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
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
