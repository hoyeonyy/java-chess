package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("보드 정보와 색을 입력받아 점수를 계산한다.")
    void calculateScoreByColor() {
        Score score = new Score();
        Board board = new Board(Map.of(
                Position.of(File.A, Rank.ONE), new Rook(Color.WHITE), // 5
                Position.of(File.B, Rank.ONE), new Knight(Color.WHITE), // 2.5
                Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE), // 3
                Position.of(File.D, Rank.ONE), new Queen(Color.WHITE), // 9
                Position.of(File.E, Rank.ONE), new King(Color.WHITE), // 0

                Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK),
                Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK),
                Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK),
                Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK),
                Position.of(File.E, Rank.EIGHT), new King(Color.BLACK)
        ));
        List<Piece> survivePiece = board.survivePieceByColor(Color.WHITE);

        double whiteScore = score.calculateScoreByColor(survivePiece, Color.WHITE, 0);
        assertThat(whiteScore).isEqualTo(19.5);
    }

    @Test
    @DisplayName("같은 파일에 Pawn이 2개 이상 있으면 점수를 0.5 감소한다.")
    void calculateScoreByColorWithPawn() {
        Score score = new Score();
        Board board = new Board(Map.of(
                Position.of(File.A, Rank.ONE), new MovedPawn(Color.WHITE),
                Position.of(File.A, Rank.TWO), new MovedPawn(Color.WHITE)
        ));
        List<Piece> survivePiece = board.survivePieceByColor(Color.WHITE);
        double whiteScore = score.calculateScoreByColor(survivePiece, Color.WHITE,
                board.countPawnInSameFile(Color.WHITE));
        assertThat(whiteScore).isEqualTo(1.0);
    }
}
