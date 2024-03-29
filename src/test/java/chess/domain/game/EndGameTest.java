package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndGameTest {

    @Test
    @DisplayName("게임 시작 시 예외 발생")
    void startGameTest() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 종료 되었습니다.");
    }

    @Test
    @DisplayName("게임 종료 시 예외 발생")
    void endGameTest() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::endGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 종료 되었습니다.");
    }

    @Test
    @DisplayName("게임 진행 시 예외 발생")
    void playTurnTest() {
        EndGame endGame = new EndGame();
        Board board = BoardInitializer.createBoard();
        Position source = Position.of(File.A, Rank.SEVEN);
        Position destination = Position.of(File.A, Rank.SIX);
        Assertions.assertThatThrownBy(() -> endGame.playTurn(board, source, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임은 종료 되었습니다.");
    }
}
