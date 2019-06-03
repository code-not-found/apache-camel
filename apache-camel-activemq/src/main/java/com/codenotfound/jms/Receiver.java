package com.codenotfound.jms;

import java.util.concurrent.CountDownLatch;
import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @Consume(property = "direct:receiver")
  public void receive(String message) {
    LOGGER.info("received message='{}'", message);
    latch.countDown();
  }
}
