package com.shows.challenge;

import com.shows.challenge.entities.Show;
import java.net.URI;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class TestClient {

  public static final String REST_SERVICE_URI = "http://localhost:8080/";

  @SuppressWarnings("unchecked")
  private static void listAllShows() {
    System.out.println("Testing listAllShows API");

    RestTemplate restTemplate = new RestTemplate();
    List<LinkedHashMap<String, Object>> showsMap =
        restTemplate.getForObject(REST_SERVICE_URI + "/showAll/", List.class);

    if (showsMap != null) {
      for (LinkedHashMap<String, Object> map : showsMap) {
        System.out.println(
            "show : id=" + map.get("id") + ", Description=" + map.get("description"));
      }
    } else {
      System.out.println("There are no shows");
    }
  }

  private static void createShow() {
    System.out.println("Testing create Show API");
    RestTemplate restTemplate = new RestTemplate();
    Show show = new Show("Testing Add 2");
    show.setDescription("Testing Add 2");
    show.setRating("R");
    show.setOriginalAirDate(LocalDate.parse("2018-01-01"));
    show.setId(3L);
    URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/addShow/", show, Show.class);
    System.out.println("Location : "+uri.toASCIIString());
  }

  /* PUT */
  private static void updateShow() {
    System.out.println("Testing update Show API");
    RestTemplate restTemplate = new RestTemplate();
    Show show = new Show("Update Test");
    show.setDescription("Update Test");
    restTemplate.put(REST_SERVICE_URI+"/show/2", show);
    System.out.println(show);
  }

  public static void main(String[] args) {
    listAllShows();
    createShow();
    updateShow();
    listAllShows();
  }
}
