package org.scratchgame.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WinCombinationDto(
  Map<String, CombinationDto> winCombinations
) {
}
