package org.scratchgame.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StandardProbabilityDto(
  int column,
  int row,
  @JsonProperty("symbols") Map<String, Integer> probabilities
) {
}
