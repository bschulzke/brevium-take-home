package org.example.dto;

import java.util.List;

public class ScheduleDTO {

  List<AppointmentDTO> appointments;

  public List<AppointmentDTO> getAppointments() {
    return appointments;
  }

  public void setAppointments(List<AppointmentDTO> appointments) {
    this.appointments = appointments;
  }
}
