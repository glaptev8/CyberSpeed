package org.scratchgame.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class ConfigObjectMapper {
  private static ObjectMapper objectMapper;

  private ConfigObjectMapper() {}

  public static ObjectMapper getInstance() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
      objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    return objectMapper;
  }
}
