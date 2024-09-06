package org.scratchgame.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GroupType {
  SAME_SYMBOLS,
  HORIZONTALLY_LINEAR_SYMBOLS,
  VERTICALLY_LINEAR_SYMBOLS,
  LTR_DIAGONALLY_LINEAR_SYMBOLS,
  RTL_DIAGONALLY_LINEAR_SYMBOLS;

  @JsonCreator
  public static GroupType forValue(String value) {
    return GroupType.valueOf(value.toUpperCase());
  }
}
