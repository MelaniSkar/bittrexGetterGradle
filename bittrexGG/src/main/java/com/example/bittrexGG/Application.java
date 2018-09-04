package com.example.bittrexGG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.vertx.core.Vertx;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

  @Autowired
  private MainVerticle mainVerticle;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
    Vertx.vertx().deployVerticle(mainVerticle);
  }
}
