package com.agiantagravic;

import com.agiantagravic.auth.data.UserRepository;
import com.agiantagravic.auth.model.AgravicUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan("com.agiantagravic")
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }

  @Autowired
  public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
    builder.userDetailsService(username -> new AgravicUserDetails(repo.findByUsername(username)));
  }
}
