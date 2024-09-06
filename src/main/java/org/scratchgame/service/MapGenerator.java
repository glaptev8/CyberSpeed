package org.scratchgame.service;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;

public interface MapGenerator {
  MapDto generate(GameConfigDto config);
}
