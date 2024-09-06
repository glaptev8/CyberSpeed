package org.scratchgame.service;

import java.io.File;
import java.io.IOException;

import org.scratchgame.config.ConfigObjectMapper;
import org.scratchgame.dto.GameConfigDto;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigFileParserServiceImpl implements ConfigFileParserService {
  private final ObjectMapper objectMapper = ConfigObjectMapper.getInstance();

  @Override
  public GameConfigDto parse(String fileName) throws IOException {
    return objectMapper.readValue(new File(fileName), GameConfigDto.class);
  }
}
