package org.example.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScheduleClient {

  HttpClient client;
  String token;
  String baseURL;

  public ScheduleClient(String token, String baseURL) {
    this.client = HttpClient.newHttpClient();
    this.token = token;
    this.baseURL = baseURL;
  }

  public HttpResponse<String> get(String path) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    // create a request
    var request = HttpRequest.newBuilder(
            URI.create(baseURL + path + "?token=" + token))
        .version(HttpClient.Version.HTTP_2)
        .header("accept", "application/json")
        .build();

    // use the client to send the request
    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }

  public HttpResponse<String> post(String path, String body) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    // create a request
    var request = HttpRequest.newBuilder(
            URI.create(baseURL + path + "?token=" + token))
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .version(HttpClient.Version.HTTP_2)
        .header("accept", "application/json")
        .build();

    // use the client to send the request
    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }



}
