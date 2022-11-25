package com.ivan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApp.class);
        TomcatServletWebServerFactory tomcatWebServer = ((TomcatServletWebServerFactory) run.getBeanFactory().getBean("tomcatServletWebServerFactory"));
        System.out.println("Listening on port " + tomcatWebServer.getPort());
    }
}
