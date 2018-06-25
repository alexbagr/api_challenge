package com.shows.challenge;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(ChallengeApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("Application arguments: ");

    args.getOptionNames().forEach(System.out::println);
  }
}
