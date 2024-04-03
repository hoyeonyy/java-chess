package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.BlackTurn;
import chess.domain.game.EndGame;
import chess.domain.game.GameState;
import chess.domain.game.InitGame;
import chess.domain.game.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class GameStateMapper {

    public static GameState getGameState(String state, Map<Position, Piece> pieces) {
        if (state.equals("end")) {
            return new EndGame(new Board(pieces));
        }
        if (state.equals("white")) {
            return new WhiteTurn(new Board(pieces));
        }
        if (state.equals("black")) {
            return new BlackTurn(new Board(pieces));
        }
        return InitGame.createInitGame();
    }
}
