package org.scratchgame.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.scratchgame.dto.CalculationResultDto;
import org.scratchgame.dto.CombinationDto;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;
import org.scratchgame.dto.SymbolDto;
import org.scratchgame.dto.SymbolType;

public class CalculationServiceImpl implements CalculationService {

  @Override
  public CalculationResultDto calculate(Map<String, List<String>> winningCombinations,
                                        GameConfigDto configDto,
                                        MapDto map,
                                        int bet) {
    return calculateWithBonuses(calculateBaseReward(winningCombinations, configDto, bet),
                                map.getMap(), configDto);
  }

  private BigDecimal calculateBaseReward(Map<String, List<String>> winningCombinations,
                                         GameConfigDto configDto,
                                         int bet) {
    if (winningCombinations.isEmpty()) {
      return BigDecimal.ZERO;
    }

    BigDecimal reward = new BigDecimal(bet);

    for (Map.Entry<String, List<String>> entry : winningCombinations.entrySet()) {
      String symbol = entry.getKey();
      List<String> combinations = entry.getValue();
      SymbolDto symbolDto = configDto.symbols().get(symbol);
      BigDecimal symbolReward = symbolDto.multiplier().multiply(new BigDecimal(bet));
      for (String combinationKey : combinations) {
        CombinationDto combinationDto = configDto.winCombinations().get(combinationKey);
        symbolReward = symbolReward.multiply(combinationDto.multiplier());
      }
      reward = reward.add(symbolReward);
    }

    return reward.subtract(new BigDecimal(bet));
  }

  private CalculationResultDto calculateWithBonuses(BigDecimal baseReward,
                                                    String[][] map,
                                                    GameConfigDto configDto) {
    BigDecimal finalReward = baseReward;
    List<String> appliedBonuses = new ArrayList<>();
    for (String[] strings : map) {
      for (String symbol : strings) {
        if (configDto.symbols().containsKey(symbol) && configDto.symbols().get(symbol).type() == SymbolType.BONUS) {
          SymbolDto bonus = configDto.symbols().get(symbol);

          switch (bonus.impact()) {
            case MULTIPLY_REWARD -> {
              finalReward = finalReward.multiply(bonus.multiplier());
              appliedBonuses.add(symbol);
            }
            case EXTRA_BONUS -> {
              finalReward = finalReward.add(bonus.extra());
              appliedBonuses.add(symbol);
            }
          }
        }
      }
    }
    return new CalculationResultDto(appliedBonuses, finalReward);
  }
}
