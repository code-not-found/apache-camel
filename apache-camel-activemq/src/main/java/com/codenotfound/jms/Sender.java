package com.codenotfound.jms;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Sender.class);

  @Produce(value = "direct:sender")
  private ProducerTemplate template;

  public void send(String message) {
    LOGGER.info("sending message='{}'", message);
    template.sendBody(template.getDefaultEndpoint(), message);
  }
}
