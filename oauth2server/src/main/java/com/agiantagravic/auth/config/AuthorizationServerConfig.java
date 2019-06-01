package com.agiantagravic.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailService;

  @Autowired
  public AuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.userDetailService = userDetailsService;
  }

  //security constraints on token endpoint
//  @Override
//  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    security.checkTokenAccess("isAuthenticated()");
//  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient("cms")
        .authorizedGrantTypes("client_credentials", "password", "refresh_token")
        .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
        .scopes("cmsCrud")
//        .accessTokenValiditySeconds(5000)
        .secret("secret");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer server){
    server.allowFormAuthenticationForClients();
  }
}
