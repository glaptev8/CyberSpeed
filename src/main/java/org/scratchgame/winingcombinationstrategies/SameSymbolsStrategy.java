package org.scratchgame.winingcombinationstrategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.scratchgame.dto.CombinationDto;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;
import org.scratchgame.dto.WinCombinationType;

public class SameSymbolsStrategy implements WinningCombinationStrategy {

  @Override
  public void check(MapDto map, GameConfigDto config, Map<String, List<String>> winningCombinations) {
    Map<String, Integer> symbolCount = new HashMap<>();

    for (int row = 0; row < map.getRow(); row++) {
      for (int col = 0; col < map.getCol(); col++) {
        String symbol = map.getMap()[row][col];
        symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
      }
    }

    for (Map.Entry<String, Integer> entry : symbolCount.entrySet()) {
      String symbol = entry.getKey();
      int count = entry.getValue();

      for (Map.Entry<String, CombinationDto> combination : config.winCombinations().entrySet()) {
        CombinationDto combo = combination.getValue();
        if (WinCombinationType.SAME_SYMBOLS.equals(combo.combinationType()) && count == combo.count()) {
          winningCombinations.putIfAbsent(symbol, new ArrayList<>());
          winningCombinations.get(symbol).add(combination.getKey());
        }
      }
    }
  }
}
