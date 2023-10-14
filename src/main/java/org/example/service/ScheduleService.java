package org.example.service;

import com.google.gson.Gson;
import org.example.dto.AppointmentDTO;
import org.example.dto.AppointmentRequestDTO;
import org.example.dto.ScheduleDTO;
import org.example.model.AppointmentRequest;
import org.example.model.Schedule;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {

  private String authtoken;
  private ScheduleClient client;
  private Gson gson;

  public ScheduleService(String authtoken) {
    this.gson = new Gson();
    this.authtoken = authtoken;
    this.client = new ScheduleClient(authtoken,
        "http://scheduling-interview-2021-265534043.us-west-2.elb.amazonaws.com/api/Scheduling");
  }

  public void start() {
    try {
      client.post("/Start", "");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public ScheduleDTO stop() {
    ScheduleDTO scheduleDTO = new ScheduleDTO();
    scheduleDTO.setAppointments(new ArrayList<>());
    try {
      HttpResponse<String> response = client.post("/Stop", "");
      List<AppointmentDTO> appointmentDTOS = gson.fromJson(response.body(), List.class);
      scheduleDTO.setAppointments(appointmentDTOS);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return scheduleDTO;
  }

  public AppointmentRequest getNextAppointmentRequest() {
    AppointmentRequest appointmentRequest = null;
    try {
      HttpResponse<String> response = client.get("/AppointmentRequest");
      String body = response.body();
      if (response.statusCode() == 200) {
        AppointmentRequestDTO dto = gson.fromJson(body, AppointmentRequestDTO.class);
        appointmentRequest = new AppointmentRequest(dto);
      } else {
        System.out.println("Response code: " + response.statusCode());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return appointmentRequest;
  }

  public Schedule getSchedule() {
    Schedule schedule = null;
    try {
      HttpResponse<String> response = client.get("/Schedule");
      String body = response.body();
      if (response.statusCode() == 200) {
        List<AppointmentDTO> appointmentDTOS = gson.fromJson(body, List.class);
        ScheduleDTO dto = new ScheduleDTO();
        dto.setAppointments(appointmentDTOS);
        schedule = new Schedule(dto);
      } else {
        System.out.println("Response code: " + response.statusCode());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return schedule;
  }

  public void postAppointmentToSchedule(AppointmentDTO appointment) {
    // TODO: Serialize appointment into JSON for body, make API call
  }

}
