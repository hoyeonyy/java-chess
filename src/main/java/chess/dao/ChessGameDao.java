package chess.dao;

import chess.domain.game.GameState;

public interface ChessGameDao {
    void createChessGame(GameState gameState);

    GameState findGame();

    void removeGame();
}
