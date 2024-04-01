package chess.dao;

import chess.ChessGame;
import chess.domain.game.GameState;

public record ChessGameDto(GameState gameState) {

    public ChessGame toGame() {
        return new ChessGame(gameState);
    }
}
