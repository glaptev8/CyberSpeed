package org.scratchgame.service;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.GameResultDto;

public class GameServiceImpl implements GameService {

  private final MapGenerator mapGenerator;
  private final WinningCombinationService winningCombinationService;
  private final CalculationService calculationService;

  public GameServiceImpl(MapGenerator mapGenerator,
                         WinningCombinationService winningCombinationService,
                         CalculationService calculationService) {
    this.mapGenerator = mapGenerator;
    this.winningCombinationService = winningCombinationService;
    this.calculationService = calculationService;
  }

  @Override
  public GameResultDto run(GameConfigDto config, int bet) {
    var map = mapGenerator.generate(config);
    var winingCombination = winningCombinationService.checkWinningCombinations(map, config);
    var calculate = calculationService.calculate(winingCombination, config, map, bet);

    return new GameResultDto(map.getMap(), calculate.reward(), winingCombination, calculate.appliedBonuses());
  }
}
