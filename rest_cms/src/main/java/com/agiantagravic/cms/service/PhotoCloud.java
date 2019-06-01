package com.agiantagravic.cms.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface PhotoCloud {
  public Map upload(File file) throws IOException;
}
