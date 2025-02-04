package com.agiantagravic.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter{

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private DefaultTokenServices tokenServices;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints){
    TokenEnhancerChain tokenEnhancerChain
        = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(
        Collections.singletonList(jwtAccessTokenConverter)
    );
    endpoints.tokenStore(tokenStore)
        .accessTokenConverter(jwtAccessTokenConverter)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }
}
