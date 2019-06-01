package com.agiantagravic.cms.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService implements PhotoCloud {

  private static Cloudinary service;

  private static String cloudName;

  private static String myApiKey;

  private static String myApiSecret;

  @Value(value = "${cloudinary.secret}")
  public void setSecret(String secret) {
    myApiSecret = secret;
  }

  @Value(value = "${cloudinary.name}")
  public void setName(String name) {
    cloudName = name;
  }

  @Value(value = "${cloudinary.key}")
  public void setKey(String key) {
    myApiKey = key;
  }


  public CloudinaryService(){
    service = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudName,
        "api_key", myApiKey,
        "api_secret", myApiSecret));
  }

  @Override
  public Map upload(File file) throws IOException {
    return service.uploader().upload(file, ObjectUtils.emptyMap());
  }
}
