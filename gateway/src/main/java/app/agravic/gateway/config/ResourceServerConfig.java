package app.agravic.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  List<String> noAuth;
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/post/list", "/post/all", "/content/list", "/content/all", "/content/{\\d+}", "/actuator/**", "/discovery/**", "/cms/**", "/auth/**")
      .permitAll()
    .and();
//      .authorizeRequests()
//      .antMatchers(HttpMethod.OPTIONS)
//      .permitAll()
//    .and()
//      .authorizeRequests()
//      .antMatchers("/content")
//      .hasRole("ADMIN").anyRequest().authenticated()
//    .and()
//      .authorizeRequests()
//      .antMatchers(HttpMethod.DELETE)
//        .hasRole("ADMIN").anyRequest().authenticated()
//    .and()
//      .authorizeRequests()
//      .antMatchers("/post/create", "/post/update", "/post/photo")
//      .hasRole("ADMIN").anyRequest().authenticated();

  }
}
