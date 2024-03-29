package chess.domain.game;

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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @Test
    @DisplayName("게임 시작 시 예외 발생")
    void startGameTest() {
        WhiteTurn whiteTurn = new WhiteTurn();
        Assertions.assertThatThrownBy(whiteTurn::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 이미 시작했습니다.");
    }

    @Test
    @DisplayName("게임 종료 시 EndGame 반환")
    void endGameTest() {
        WhiteTurn whiteTurn = new WhiteTurn();
        Assertions.assertThat(whiteTurn.endGame()).isInstanceOf(EndGame.class);
    }

    @Test
    @DisplayName("게임 진행 시 BlackTurn 반환")
    void playTurnTest() {
        WhiteTurn whiteGame = new WhiteTurn();
        Board board = BoardInitializer.createBoard();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.SIX);
        Assertions.assertThat(whiteGame.playTurn(board, source, destination)).isInstanceOf(BlackTurn.class);
    }

    @Test
    @DisplayName("게임 진행 시 EndGame 반환")
    void playTurnTest2() {
        WhiteTurn whiteTurn = new WhiteTurn();
        Map<Position, Piece> map = new HashMap<>();
        map.put(Position.of(File.E, Rank.SEVEN), new Rook(Color.WHITE));
        map.put(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK));
        Board board = new Board(map);

        Position source = Position.of(File.E, Rank.SEVEN);
        Position destination = Position.of(File.E, Rank.EIGHT);

        GameState gameState = whiteTurn.playTurn(board, source, destination);
        Assertions.assertThat(gameState).isInstanceOf(EndGame.class);
    }

}
