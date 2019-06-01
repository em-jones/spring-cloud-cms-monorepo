package com.agiantagravic.cms.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class CustomSerializer extends StdSerializer<Set<CmsItem>> {
  protected CustomSerializer(Class<Set<CmsItem>> t) {
    super(t);
  }

  protected CustomSerializer(JavaType type) {
    super(type);
  }

  protected CustomSerializer(Class<?> t, boolean dummy) {
    super(t, dummy);
  }

  protected CustomSerializer(StdSerializer<?> src) {
    super(src);
  }

  @Override
  public void serialize(Set<CmsItem> value, JsonGenerator gen, SerializerProvider provider) throws IOException {

  }
}
