package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class EndGame implements GameState {

    @Override
    public GameState startGame() {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }

    @Override
    public GameState endGame() {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }

    @Override
    public GameState playTurn(Board board, Position source, Position destination) {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }
}
