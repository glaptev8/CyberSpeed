package org.scratchgame.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BonusProbabilityDto(
  @JsonProperty("symbols") Map<String, Integer> probability
) {
}
