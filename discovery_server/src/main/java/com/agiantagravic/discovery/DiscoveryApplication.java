package com.agiantagravic.discovery;

import com.sun.xml.bind.v2.runtime.JAXBContextImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryApplication {

  public static void main(String[] args) throws JAXBException {
    JAXBContext jaxbContext = JAXBContextImpl.newInstance(DiscoveryApplication.class);
    SpringApplication.run(DiscoveryApplication.class, args);
  }
}
