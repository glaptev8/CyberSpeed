package org.scratchgame.winingcombinationstrategies;

import java.util.List;
import java.util.Map;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;

public interface WinningCombinationStrategy {
  void check(MapDto map, GameConfigDto config, Map<String, List<String>> winningCombinations);
}
