package com.codenotfound.jms;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SenderRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:sender").to("log:SenderRoute")
        .to("activemq:helloworld.q");
  }
}
