package org.example;

import org.example.model.Schedule;

public class Main {

  static String authtoken = "c6d846dd-df58-4f6e-8e45-fa204d4ae09a";
  public static void main(String[] args) {
    Scheduler scheduler = new Scheduler(authtoken);

    Schedule finalSchedule = scheduler.scheduleAllAppointments();

    System.out.println(finalSchedule.toString());
  }

}