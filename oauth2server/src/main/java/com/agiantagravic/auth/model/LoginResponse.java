package com.agiantagravic.auth.model;

public class LoginResponse {


  private boolean success;
  public LoginResponse(boolean success){
    this.success = success;
  }

  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
