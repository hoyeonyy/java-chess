package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitGameTest {

    @Test
    @DisplayName("게임 시작 시 WhiteTurn 반환")
    void startGameTest() {
        InitGame initGame = new InitGame();
        Assertions.assertThat(initGame.startGame()).isInstanceOf(WhiteTurn.class);

    }

    @Test
    @DisplayName("게임 종료 시 EndGame 반환")
    void endGameTest() {
        InitGame initGame = new InitGame();
        Assertions.assertThat(initGame.endGame()).isInstanceOf(EndGame.class);
    }

    @Test
    @DisplayName("게임 진행 시 예외 발생")
    void playTurnTest() {
        InitGame initGame = new InitGame();
        Board board = BoardInitializer.createBoard();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.SIX);

        Assertions.assertThatThrownBy(() -> initGame.playTurn(board, source, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 시작하지 않았습니다.");
    }

}
