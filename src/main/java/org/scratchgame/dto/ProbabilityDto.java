package org.scratchgame.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProbabilityDto(
  @JsonProperty("standard_symbols") List<StandardProbabilityDto> standardProbability,
  @JsonProperty("bonus_symbols") BonusProbabilityDto bonusProbability
) {
}
