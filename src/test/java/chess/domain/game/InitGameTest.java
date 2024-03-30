package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitGameTest {

    @Test
    @DisplayName("게임 시작 시 WhiteTurn 반환")
    void startGameTest() {
        InitGame initGame = new InitGame(BoardInitializer.createBoard());
        assertThat(initGame.startGame()).isInstanceOf(WhiteTurn.class);

    }

    @Test
    @DisplayName("게임 종료 시 EndGame 반환")
    void endGameTest() {
        InitGame initGame = new InitGame(BoardInitializer.createBoard());
        assertThat(initGame.endGame()).isInstanceOf(EndGame.class);
    }

    @Test
    @DisplayName("게임 진행 시 예외 발생")
    void playTurnTest() {
        InitGame initGame = new InitGame(BoardInitializer.createBoard());
        Board board = BoardInitializer.createBoard();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.SIX);

        assertThatThrownBy(() -> initGame.playTurn(source, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 시작하지 않았습니다.");
    }

}
