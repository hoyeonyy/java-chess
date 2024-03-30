package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @Test
    @DisplayName("게임 시작 시 예외 발생")
    void startGameTest() {
        BlackTurn blackTurn = new BlackTurn(BoardInitializer.createBoard());
        assertThatThrownBy(blackTurn::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 이미 시작했습니다.");
    }

    @Test
    @DisplayName("게임 종료 시 EndGame 반환")
    void endGameTest() {
        BlackTurn blackTurn = new BlackTurn(BoardInitializer.createBoard());
        assertThat(blackTurn.endGame()).isInstanceOf(EndGame.class);
    }

    @Test
    @DisplayName("게임 진행 시 WhiteTurn 반환")
    void playTurnTest() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.B, Rank.SEVEN), new Rook(Color.BLACK));
        pieces.put(Position.of(File.B, Rank.SIX), new Rook(Color.WHITE));
        BlackTurn blackTurn = new BlackTurn(new Board(pieces));

        Position source = Position.of(File.B, Rank.SEVEN);
        Position destination = Position.of(File.B, Rank.SIX);
        assertThat(blackTurn.playTurn(source, destination)).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("King을 잡았을 시 EndGame 반환")
    void playTurnCatchKingTest() {
        Map<Position, Piece> map = new HashMap<>();
        map.put(Position.of(File.E, Rank.SEVEN), new Rook(Color.BLACK));
        map.put(Position.of(File.E, Rank.EIGHT), new King(Color.WHITE));
        BlackTurn blackTurn = new BlackTurn(new Board(map));

        Position source = Position.of(File.E, Rank.SEVEN);
        Position destination = Position.of(File.E, Rank.EIGHT);

        GameState gameState = blackTurn.playTurn(source, destination);
        assertThat(gameState).isInstanceOf(EndGame.class);
    }

}
