package org.scratchgame;

import java.io.IOException;
import java.util.List;

import org.scratchgame.config.ConfigObjectMapper;
import org.scratchgame.service.ConfigFileParserServiceImpl;
import org.scratchgame.service.GameService;
import org.scratchgame.service.GameServiceImpl;
import org.scratchgame.service.MapGeneratorImpl;
import org.scratchgame.service.WinningCombinationServiceImpl;
import org.scratchgame.winingcombinationstrategies.CalculationServiceImpl;
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
    if (args.length == 0) {
      System.out.println("Please provide the path to the config file as a parameter.");
      return;
    }

    var filePath = args[0];
    var parserService = new ConfigFileParserServiceImpl();
    var config = parserService.parse(filePath);

    var instance = ConfigObjectMapper.getInstance();
    System.out.println(instance.writerWithDefaultPrettyPrinter().writeValueAsString(gameService.run(config, Integer.parseInt(args[1]))));
  }
}