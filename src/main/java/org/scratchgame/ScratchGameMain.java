package org.scratchgame;

import java.io.IOException;
import java.util.List;

import org.scratchgame.config.ConfigObjectMapper;
import org.scratchgame.service.ConfigFileParserServiceImpl;
import org.scratchgame.service.GameService;
import org.scratchgame.service.GameServiceImpl;
import org.scratchgame.service.MapGeneratorImpl;
import org.scratchgame.service.WinningCombinationServiceImpl;
import org.scratchgame.service.CalculationServiceImpl;
import org.scratchgame.winingcombinationstrategies.SameSymbolInTheRowStrategy;
import org.scratchgame.winingcombinationstrategies.SameSymbolsStrategy;


public class ScratchGameMain {

  private static final GameService gameService;

  static {
    var mapGenerator = new MapGeneratorImpl();
    var winningCombinationService = new WinningCombinationServiceImpl(List.of(new SameSymbolsStrategy(),
                                                                              new SameSymbolInTheRowStrategy()));
    var calculationService = new CalculationServiceImpl();
    gameService = new GameServiceImpl(mapGenerator, winningCombinationService, calculationService);
  }

  public static void main(String[] args) throws IOException {
    String filePath = null;
    int bettingAmount = 0;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--config")) {
        filePath = args[i + 1];
      } else if (args[i].equals("--betting-amount")) {
        bettingAmount = Integer.parseInt(args[i + 1]);
      }
    }

    if (filePath == null || bettingAmount == 0) {
      System.out.println("Please provide both --config <path> and --betting-amount <amount>");
      return;
    }

    var parserService = new ConfigFileParserServiceImpl();
    var gameConfig = parserService.parse(filePath);
    var result = gameService.run(gameConfig, bettingAmount);

    var instance = ConfigObjectMapper.getInstance();
    System.out.println(instance.writerWithDefaultPrettyPrinter().writeValueAsString(result));
  }
}