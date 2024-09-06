package org.scratchgame.winingcombinationstrategies;

import java.util.List;
import java.util.Map;

import org.scratchgame.dto.CalculationResultDto;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.MapDto;

public interface CalculationService {
  CalculationResultDto calculate(Map<String, List<String>> winningCombinations, GameConfigDto configDto, MapDto map, int bet);
}
