package com.qa.CamelTest;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class Transform {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
 
        PropertiesComponent prop = context.getComponent("properties", PropertiesComponent.class);
        prop.setLocation("classpath:system-properties.properties");
        
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:inbox?noop=true").transform(body().regexReplaceAll(" ", "<br/>")) 
                .to("file:outbox");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
 
}