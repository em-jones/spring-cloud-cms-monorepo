package com.agiantagravic.auth.model;

import com.agiantagravic.auth.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class AgravicUserDetails implements UserDetails {
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public AgravicUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
