package org.scratchgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.scratchgame.config.ConfigObjectMapper;
import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.GameResultDto;
import org.scratchgame.dto.MapDto;
import org.scratchgame.winingcombinationstrategies.SameSymbolInTheRowStrategy;
import org.scratchgame.winingcombinationstrategies.SameSymbolsStrategy;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServiceImplTest {

  private GameService gameService;
  private GameConfigDto configDto;
  private final ObjectMapper objectMapper = ConfigObjectMapper.getInstance();

  public void setUp(String configPath, String mapPath) throws Exception {
    MapGenerator mapGenerator = Mockito.mock(MapGenerator.class);
    WinningCombinationService winningCombinationService = new WinningCombinationServiceImpl(List.of(new SameSymbolsStrategy(),
                                                                                                    new SameSymbolInTheRowStrategy()));
    CalculationService calculationService = new CalculationServiceImpl();
    configDto = objectMapper.readValue(new File(configPath), GameConfigDto.class);
    MapDto map = objectMapper.readValue(new File(mapPath), MapDto.class);
    Mockito.when(mapGenerator.generate(configDto)).thenReturn(map);
    gameService = new GameServiceImpl(mapGenerator, winningCombinationService, calculationService);
  }

  @Test
  @DisplayName("Test vertical winning combination")
  public void test_vertical_winning_combination() throws Exception {
    setUp("src/test/resources/vertical_config.json", "src/test/resources/vertical_map.json");

    GameResultDto result = gameService.run(configDto, 100);

    BigDecimal expectedReward = new BigDecimal(100 * 5 * 2 * 2);
    assertEquals(expectedReward, result.reward());
  }

  @Test
  @DisplayName("Test horizontal winning combination")
  public void test_horizon_winning_combination() throws Exception {
    setUp("src/test/resources/horizontal_config.json", "src/test/resources/horizontal_map.json");

    GameResultDto result = gameService.run(configDto, 100);

    BigDecimal expectedReward = new BigDecimal(100 * 5 * 2 * 2);
    assertEquals(expectedReward, result.reward());
  }

  @Test
  @DisplayName("Test winning the same symbols plus bonus +100")
  public void test_same_symbols_plus_bonus100() throws Exception {
    setUp("src/test/resources/test_config_same_symbol_and_bonus100.json", "src/test/resources/test_map_same_symbol_and_bonus100.json");

    GameResultDto result = gameService.run(configDto, 100);

    BigDecimal expectedReward = new BigDecimal(1600 + 1000);
    assertEquals(expectedReward, result.reward());
    assertEquals(List.of("+1000"), result.appliedBonuses());
  }

  @Test
  @DisplayName("Test no winning combination")
  public void testNoWinningCombination() throws Exception {
    setUp("src/test/resources/no_win_config.json", "src/test/resources/no_win_map.json");

    GameResultDto result = gameService.run(configDto, 100);

    BigDecimal expectedReward = BigDecimal.ZERO;
    assertEquals(expectedReward, result.reward());
    assertEquals(List.of(), result.appliedBonuses());
  }
}