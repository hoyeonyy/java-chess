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

class WhiteTurnTest {

    @Test
    @DisplayName("게임 시작 시 예외 발생")
    void startGameTest() {
        WhiteTurn whiteTurn = new WhiteTurn(BoardInitializer.createBoard());
        assertThatThrownBy(whiteTurn::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 이미 시작했습니다.");
    }

    @Test
    @DisplayName("게임 종료 시 EndGame 반환")
    void endGameTest() {
        WhiteTurn whiteTurn = new WhiteTurn(BoardInitializer.createBoard());
        assertThat(whiteTurn.endGame()).isInstanceOf(EndGame.class);
    }

    @Test
    @DisplayName("게임 진행 시 BlackTurn 반환")
    void playTurnTest() {
        WhiteTurn whiteGame = new WhiteTurn(BoardInitializer.createBoard());
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.SIX);
        assertThat(whiteGame.playTurn(source, destination)).isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("게임 진행 시 EndGame 반환")
    void playTurnTest2() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.E, Rank.SEVEN), Rook.getInstance(Color.WHITE));
        pieces.put(Position.of(File.E, Rank.EIGHT), King.getInstance(Color.BLACK));
        WhiteTurn whiteTurn = new WhiteTurn(new Board(pieces));

        Position source = Position.of(File.E, Rank.SEVEN);
        Position destination = Position.of(File.E, Rank.EIGHT);

        GameState gameState = whiteTurn.playTurn(source, destination);
        assertThat(gameState).isInstanceOf(EndGame.class);
    }

}
