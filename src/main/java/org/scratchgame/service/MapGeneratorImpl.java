package org.scratchgame.service;

import java.util.Map;
import java.util.Random;

import org.scratchgame.config.DefaultGameSettings;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;
import org.scratchgame.dto.StandardProbabilityDto;

public class MapGeneratorImpl implements MapGenerator {

  private final Random random = new Random();

  @Override
  public MapDto generate(GameConfigDto config) {
    int rows = config.rows() == null ? DefaultGameSettings.ROWS : config.rows();
    int columns = config.columns() == null ? DefaultGameSettings.COLUMNS : config.columns();
    var map = new MapDto(rows, columns);
    Integer countOfBonusesInTheMap = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        boolean isBonus = shouldGenerateBonus(countOfBonusesInTheMap);
        String generatedSymbol;
        if (isBonus) {
          generatedSymbol = generateBonusSymbol(config);
          countOfBonusesInTheMap++;
        } else {
          generatedSymbol = generateStandardSymbol(i, j, config);
        }
        map.setCell(i, j, generatedSymbol);
      }
    }

    return map;
  }

  private String generateStandardSymbol(int row, int column, GameConfigDto config) {
    var probabilities = getProbabilitiesForCell(config, row, column).probabilities();
    return generateSymbolFromProbabilities(probabilities);
  }

  private String generateBonusSymbol(GameConfigDto config) {
    var bonusProbabilities = config.probabilities().bonusProbability().probability();
    return generateSymbolFromProbabilities(bonusProbabilities);
  }

  private String generateSymbolFromProbabilities(Map<String, Integer> symbolProbabilities) {
    int totalProbability = symbolProbabilities.values().stream().mapToInt(Integer::intValue).sum();
    int randomValue = random.nextInt(totalProbability);
    int cumulativeProbability = 0;

    for (Map.Entry<String, Integer> entry : symbolProbabilities.entrySet()) {
      cumulativeProbability += entry.getValue();
      if (randomValue < cumulativeProbability) {
        return entry.getKey();
      }
    }
    return "MISS";
  }

  private boolean shouldGenerateBonus(Integer countOfBonusesInTheMap) {
    if (DefaultGameSettings.MAXIMUM_BONUS_IN_GAME <= countOfBonusesInTheMap) {
      return false;
    }
    return random.nextInt(100) < DefaultGameSettings.BONUS_PROBABILITY;
  }

  private StandardProbabilityDto getProbabilitiesForCell(GameConfigDto config, int row, int col) {
    return config.probabilities().standardProbability().stream()
      .filter(probability -> probability.row() == row && probability.column() == col)
      .findFirst()
      .orElse(config.probabilities().standardProbability().getFirst());
  }
}
