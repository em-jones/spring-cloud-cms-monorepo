package com.agiantagravic.auth.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@Component
@EnableAuthorizationServer
@EnableResourceServer
public class AuthController {

  @GetMapping(value = "/")
  public String index(){
    return "public";
  }

  @RequestMapping(value = "/user", produces = "application/json")
  public Map<String, Object> user(OAuth2Authentication user){
    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("user", user.getUserAuthentication().getPrincipal());
    userInfo.put("authorities",
        AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities())
    );
    return userInfo;
  }

  @PostMapping(value = "/banesullivan")


  @GetMapping(value = "/private")
  public String privateArea(){
    return "private";
  }

}
