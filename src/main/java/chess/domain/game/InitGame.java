package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class InitGame implements GameState {
    @Override
    public GameState startGame() {
        return new WhiteTurn();
    }

    @Override
    public GameState endGame() {
        return new EndGame();
    }

    @Override
    public GameState playTurn(Board board, Position source, Position destination) {
        throw new IllegalStateException("게임이 시작하지 않았습니다.");
    }
}
