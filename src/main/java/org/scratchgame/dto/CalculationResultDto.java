package org.scratchgame.dto;

import java.math.BigDecimal;
import java.util.List;

public record CalculationResultDto(
  List<String> appliedBonuses,
  BigDecimal reward
) {
}
