package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class InitGame implements GameState {

    private final Board board;

    public InitGame(Board board) {
        this.board = board;
    }

    @Override
    public GameState startGame() {
        return new WhiteTurn(board);
    }

    @Override
    public GameState endGame() {
        return new EndGame(board);
    }

    @Override
    public GameState playTurn(Position source, Position destination) {
        throw new IllegalStateException("게임이 시작하지 않았습니다.");
    }
}
