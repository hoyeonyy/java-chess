package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class EndGame implements GameState {

    private final Board board;

    public EndGame(Board board) {
        this.board = board;
    }

    @Override
    public GameState startGame() {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }

    @Override
    public GameState endGame() {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }

    @Override
    public GameState playTurn(Position source, Position destination) {
        throw new IllegalStateException("게임은 종료 되었습니다.");
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return board.pieces();
    }

    @Override
    public double calculateScoreBy(Color color) {
        return board.calculateScoreByColor(color);
    }
}
