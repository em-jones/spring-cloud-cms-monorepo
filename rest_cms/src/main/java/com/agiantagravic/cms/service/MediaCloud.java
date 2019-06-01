package com.agiantagravic.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class MediaCloud {

  private final PhotoCloud photoCloud;

  @Autowired
  public MediaCloud(){
    this.photoCloud = new CloudinaryService();
  }

  public Map photoUpload(File file) throws IOException {
    return photoCloud.upload(file);
  }
}
