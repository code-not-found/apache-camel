package com.codenotfound.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverRoute extends RouteBuilder {

  @Autowired
  private Receiver receiver;

  @Override
  public void configure() throws Exception {
    from("activemq:helloworld.q").to("log:ReceiverRoute")
        .bean(receiver);
  }
}
