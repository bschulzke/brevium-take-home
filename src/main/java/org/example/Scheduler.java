package org.example;

import org.example.dto.AppointmentDTO;
import org.example.model.Appointment;
import org.example.model.AppointmentRequest;
import org.example.model.Schedule;
import org.example.service.ScheduleService;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;

public class Scheduler {

  ScheduleService service;
  org.example.model.Schedule schedule;

  public Scheduler(String token) {
    service = new ScheduleService(token);
  }

  public Schedule scheduleAllAppointments() {
    service.start();
    schedule = service.getSchedule();

    AppointmentRequest nextRequest = service.getNextAppointmentRequest();

    while (nextRequest != null) {
      scheduleAppointment(nextRequest);
      nextRequest = service.getNextAppointmentRequest();
    }
    return new Schedule(service.stop());
  }

  private void scheduleAppointment(AppointmentRequest request) {
      for (ZonedDateTime date : request.getPreferredDays()) {
        int doc = getAvailableDoc(request.getPreferredDocs());
        if (isValidDate(date, request, doc)) {
          AppointmentDTO dto = new AppointmentDTO(request.getPersonId(), doc, date.toString(), request.isNew());
          Appointment appointment = new Appointment(dto);
          schedule.addAppointment(appointment);
          service.postAppointmentToSchedule(dto);
        }
      }
      // TODO: How to handle cases where none of the preferred dates work?
      // Should the scheduler pick the closest available match?
      // Should it prioritize matching the day of the week, the day of the month, preferred doctor, or time of day?
  }

  private boolean isValidDate(ZonedDateTime date, AppointmentRequest request, int doctorId) {
    return isInHours(date, request.isNew())
        && isWeekday(date)
        && isInDateRange(date)
        && schedule.docIsAvailable(date, doctorId)
        && !schedule.appointmentsTooClose(date, request.getPersonId());
  }

  private boolean isInDateRange(ZonedDateTime date) {
    return date.getYear() == 2021 &&
        (date.getMonth() == Month.NOVEMBER || date.getMonth() == Month.DECEMBER);
  }

  private boolean isWeekday(ZonedDateTime date) {
    return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY;
  }

  private boolean isInHours(ZonedDateTime date, boolean isNew) {
    if (isNew) {
      return date.getHour() == 15 || date.getHour() == 16;
    } else {
      return date.getHour() >= 8 && date.getHour() <= 16;
    }
  }

  private int getAvailableDoc(List<Integer> preferredDocs) {
    // TODO: Flesh out stub
    return 0;
  }

}
