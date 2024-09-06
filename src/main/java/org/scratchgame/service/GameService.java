package org.scratchgame.service;

import org.scratchgame.dto.GameConfigDto;
import org.scratchgame.dto.GameResultDto;

public interface GameService {
  GameResultDto run(GameConfigDto configDto, int bet);
}
