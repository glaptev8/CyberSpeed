package org.scratchgame.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameConfigDto(
  Integer columns,
  Integer rows,
  Map<String, SymbolDto> symbols,
  ProbabilityDto probabilities,
  @JsonProperty("win_combinations") Map<String, CombinationDto> winCombinations
) {
}
