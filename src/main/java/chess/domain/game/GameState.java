package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface GameState {

    GameState startGame();

    GameState endGame();

    GameState playTurn(Position source, Position destination);

    Map<Position, Piece> getPieces();

    double calculateScoreBy(Color color);
}
