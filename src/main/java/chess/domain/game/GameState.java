package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;

public interface GameState {

    GameState startGame();

    GameState endGame();

    GameState playTurn(Position source, Position destination);

    Board getBoard();
}
