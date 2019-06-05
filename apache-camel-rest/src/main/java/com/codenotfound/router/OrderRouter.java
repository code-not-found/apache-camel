package com.codenotfound.router;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import com.codenotfound.model.Order;
import com.codenotfound.service.OrderNotFoundException;

@Component
public class OrderRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration()
        // use the servlet component and run on port 8080
        .component("servlet").port("8080")
        // and enable json binding mode
        .bindingMode(RestBindingMode.json)
        // enable swagger
        .apiContextPath("/swagger-doc")
        // setup the api properties
        .apiProperty("api.title", "Order API")
        .apiProperty("api.version", "1.0.0");

    onException(OrderNotFoundException.class).handled(true)
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(404))
        .setBody(constant(""));

    // rest operations under the orders context-path
    rest("/orders").description("Order REST service")
        .consumes("application/json").produces("application/json")

        .get("/{id}").outType(Order.class)
        .description("Find an order by ID").param().name("id")
        .description("The order ID").endParam().responseMessage()
        .code(200).message("The order for the given ID")
        .endResponseMessage().responseMessage().code(404)
        .message("Order not found").endResponseMessage()
        .to("bean:orderService?method=findById(${header.id})")

        .post().type(Order.class).outType(String.class)
        .description("Service to submit a new order")
        .responseMessage().code(200).message("The created order id")
        .endResponseMessage().responseMessage().code(400)
        .message("Invalid input data").endResponseMessage()
        .responseMessage().code(500).message("Server error")
        .endResponseMessage()
        .to("bean:orderService?method=createOrder")

        .put().type(Order.class)
        .description("Service to update an existing order")
        .responseMessage().code(400).message("Invalid input data")
        .endResponseMessage().responseMessage().code(500)
        .message("Server error").endResponseMessage()
        .to("bean:orderService?method=updateOrder")

        .delete("{id}")
        .description("Service to cancel an existing order").param()
        .name("id").description("The order id").endParam()
        .responseMessage().code(404).message("Order not found")
        .endResponseMessage().responseMessage().code(500)
        .message("Server error").endResponseMessage()
        .to("bean:orderService?method=cancelOrder(${header.id})");
  }
}
