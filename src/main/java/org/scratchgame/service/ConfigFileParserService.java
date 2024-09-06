package org.scratchgame.service;

import java.io.IOException;

import org.scratchgame.dto.GameConfigDto;

public interface ConfigFileParserService {
  GameConfigDto parse(String fileName) throws IOException;
}
