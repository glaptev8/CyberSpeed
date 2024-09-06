package org.scratchgame.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SymbolType {
  STANDARD,
  BONUS;

  @JsonCreator
  public static SymbolType forValue(String value) {
    return SymbolType.valueOf(value.toUpperCase());
  }
}
