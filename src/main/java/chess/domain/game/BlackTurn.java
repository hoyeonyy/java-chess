package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class BlackTurn implements GameState {

    private final Board board;

    public BlackTurn(Board board) {
        this.board = board;
    }

    @Override
    public GameState startGame() {
        throw new IllegalStateException("게임은 이미 시작했습니다.");
    }

    @Override
    public GameState endGame() {
        return new EndGame(board);
    }

    @Override
    public GameState playTurn(Position source, Position destination) {
        Piece removePiece = board.move(source, destination);
        if (removePiece.isKing()) {
            return new EndGame(board);
        }
        return new WhiteTurn(board);
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
