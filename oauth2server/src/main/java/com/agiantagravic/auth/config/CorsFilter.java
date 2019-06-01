package com.agiantagravic.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements Filter {

  @Value("${management.endpoints.web.cors.allowed-origins}")
  private String corsOrigin;

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;
    try {
      response.setHeader("Access-Control-Allow-Origin", validOrigin(request));
    } catch (Exception e) {
      e.printStackTrace();
    }
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
    response.setHeader("Access-Control-Max-Age", "3600");
    if (request.getMethod() != "OPTIONS") {
      chain.doFilter(req, res);
    } else {
    }
  }

  private String validOrigin(HttpServletRequest request) throws Exception {
    // origin
    String origin = request.getHeader("Origin");

    URL originUrl = null;
    try {
      originUrl = new URL(origin);
    } catch (MalformedURLException ex) {
    }

    // Allow myDomain.com
    // Or anySubDomain.myDomain.com
    // Or subSub.anySubDomain.myDomain.com

    // hostAllowedPattern
    Pattern hostAllowedPattern = Pattern.compile(corsOrigin, Pattern.CASE_INSENSITIVE);
    if(!hostAllowedPattern.matcher(originUrl.getHost()).matches()) {
      throw new Exception();
    }

    return origin;
  }

  public void init(FilterConfig filterConfig) {}

  public void destroy() {}

}