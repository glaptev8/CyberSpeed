package org.scratchgame.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SymbolDto (
  SymbolType type,
  @JsonProperty("reward_multiplier") BigDecimal multiplier,
  ImpactType impact,
  BigDecimal extra
) {
}
