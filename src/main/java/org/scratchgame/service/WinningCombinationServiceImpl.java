package org.scratchgame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;
import org.scratchgame.winingcombinationstrategies.WinningCombinationStrategy;

public class WinningCombinationServiceImpl implements WinningCombinationService {

  private final List<WinningCombinationStrategy> winningCombinationStrategies;

  public WinningCombinationServiceImpl(List<WinningCombinationStrategy> winningCombinationStrategies) {
    this.winningCombinationStrategies = winningCombinationStrategies;
  }

  @Override
  public Map<String, List<String>> checkWinningCombinations(MapDto map, GameConfigDto config) {
    Map<String, List<String>> winningCombinations = new HashMap<>();

    for (WinningCombinationStrategy strategy : winningCombinationStrategies) {
      strategy.check(map, config, winningCombinations);
    }

    return winningCombinations;
  }
}
