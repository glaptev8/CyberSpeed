package org.scratchgame.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CombinationDto(
  @JsonProperty("reward_multiplier") BigDecimal multiplier,
  @JsonProperty("when") WinCombinationType combinationType,
  Integer count,
  GroupType group,
  @JsonProperty("covered_areas") List<List<String>> coveredArea
) {}
