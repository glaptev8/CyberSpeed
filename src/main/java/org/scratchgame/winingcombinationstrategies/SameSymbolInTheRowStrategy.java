package org.scratchgame.winingcombinationstrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.scratchgame.dto.CombinationDto;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.GroupType;
import org.scratchgame.dto.MapDto;

public class SameSymbolInTheRowStrategy implements WinningCombinationStrategy {

  private final Function<String, Integer> getRow = (position) -> Integer.parseInt(position.split(":")[0]);
  private final Function<String, Integer> getCol = (position) -> Integer.parseInt(position.split(":")[1]);

  @Override
  public void check(MapDto map, GameConfigDto config, Map<String, List<String>> winningCombinations) {
    for (Entry<String, CombinationDto> entry : config.winCombinations().entrySet()) {
      if (GroupType.HORIZONTALLY_LINEAR_SYMBOLS.equals(entry.getValue().group()) ||
          GroupType.VERTICALLY_LINEAR_SYMBOLS.equals(entry.getValue().group()) ||
          GroupType.LTR_DIAGONALLY_LINEAR_SYMBOLS.equals(entry.getValue().group()) ||
          GroupType.RTL_DIAGONALLY_LINEAR_SYMBOLS.equals(entry.getValue().group())) {
        for (List<String> coveredArea : entry.getValue().coveredArea()) {
          String firstSymbol = map.getMap()[getRow.apply(coveredArea.getFirst())][getCol.apply(coveredArea.getFirst())];
          boolean isWinning = true;

          for (String position : coveredArea) {
            int row = getRow.apply(position);
            int col = getCol.apply(position);
            if (!map.getMap()[row][col].equals(firstSymbol)) {
              isWinning = false;
              break;
            }
          }

          if (isWinning) {
            winningCombinations.putIfAbsent(firstSymbol, new ArrayList<>());
            winningCombinations.get(firstSymbol).add(entry.getKey());
          }
        }
      }
    }
  }
}
