package org.scratchgame.service;

import java.util.List;
import java.util.Map;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;

public interface WinningCombinationService {
  Map<String, List<String>> checkWinningCombinations(MapDto map, GameConfigDto config);
}
