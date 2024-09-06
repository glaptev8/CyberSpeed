package org.scratchgame.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ImpactType {
  EXTRA_BONUS,
  MULTIPLY_REWARD,
  MISS;

  @JsonCreator
  public static ImpactType forValue(String value) {
    return ImpactType.valueOf(value.toUpperCase());
  }
}
