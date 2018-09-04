package com.example.bittrexGG;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;
import org.springframework.stereotype.Component;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.HttpResponse;

@Component
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    String url = "https://bittrex.com/api/v1.1/public/getmarketsummary?market=usd-btc";
    WebClient client = WebClient.create(vertx);
    vertx.setPeriodic(10000, event -> {
    client
      .getAbs(url)
      .as(BodyCodec.jsonObject())
      .send(ar -> {
        if (ar.succeeded()) {
          HttpResponse<JsonObject> response = ar.result();

          JsonObject body = response.body();
          JsonArray result = body.getJsonArray("result");
          JsonObject arrayElem = result.getJsonObject(0);
          Double BTC_rate = arrayElem.getDouble("Last");
          System.out.println(BTC_rate);
        } else {
          System.out.println("Received nothing");
        }
      });
    });
  }

}
