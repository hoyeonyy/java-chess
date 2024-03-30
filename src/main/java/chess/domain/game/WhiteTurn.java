package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class WhiteTurn implements GameState {

    private final Board board;

    public WhiteTurn(Board board) {
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
        return new BlackTurn(board);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
