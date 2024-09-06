package org.scratchgame.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WinCombinationType {
  SAME_SYMBOLS,
  LINEAR_SYMBOLS;

  @JsonCreator
  public static WinCombinationType forValue(String value) {
    return WinCombinationType.valueOf(value.toUpperCase());
  }
}
