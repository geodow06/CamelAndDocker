package com.qa.camel.packageMain;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
 
import javax.jms.ConnectionFactory;
 
public class FtpToJMSExample4 {
 
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
 
        PropertiesComponent prop = context.getComponent("properties", PropertiesComponent.class);
        prop.setLocation("classpath:system-properties.properties");
        
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:inbox?noop=true")
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .to("activemq:xmlOrders")
                .when(header("CamelFileName").regex("^.*(csv|csl)$"))
                .to("activemq:csvOrders")
                .otherwise()
                .to("activemq:otherOrders");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
 
}