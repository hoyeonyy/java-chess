package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class InitGame implements GameState {

    private final Board board;

    private InitGame(Board board) {
        this.board = board;
    }

    public static GameState createInitGame() {
        return new InitGame(BoardInitializer.createBoard());
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

    @Override
    public Map<Position, Piece> getPieces() {
        return board.pieces();
    }

    @Override
    public double calculateScoreBy(Color color) {
        return board.calculateScoreByColor(color);
    }

    @Override
    public String getStateName() {
        return "init";
    }
}
