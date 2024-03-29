package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class WhiteTurn implements GameState {
    
    @Override
    public GameState startGame() {
        throw new IllegalStateException("게임은 이미 시작했습니다.");
    }

    @Override
    public GameState endGame() {
        return new EndGame();
    }

    @Override
    public GameState playTurn(Board board, Position source, Position destination) {
        Piece removePiece = board.move(source, destination);
        if (removePiece.isKing()) {
            return new EndGame();
        }
        return new BlackTurn();
    }
}
