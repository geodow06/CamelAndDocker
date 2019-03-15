package com.qa.camel.packageMain;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
 
import javax.jms.ConnectionFactory;
 
public class FtpToJMSExample5 {
 
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
 
        PropertiesComponent prop = context.getComponent("properties", PropertiesComponent.class);
        prop.setLocation("classpath:system-properties.properties");
        
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("jms:xmlOrders")
                    .multicast()
                    .parallelProcessing()
                    .to("activemq:queue:{{accounting}}", "activemq:queue:{{manufacturing}}");
            }
        });
        context.start();
        Thread.sleep(10000);
        context.stop();
    }
 
}