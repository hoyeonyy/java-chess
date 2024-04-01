package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceScoreTest {

    @Test
    @DisplayName("PieceScore에 해당하는 Piece가 아닌 경우 예외를 발생한다.")
    void validatePieceType() {
        Piece piece = new Empty();
        assertThatThrownBy(() -> PieceScore.addScore(piece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 기물입니다.");
    }

    @Test
    @DisplayName("Queen의 점수를 계산한다")
    void calculateQueenScore() {
        Piece queen = Queen.getInstance(Color.WHITE);

        double score = PieceScore.addScore(queen);

        assertThat(score).isEqualTo(9);
    }

    @Test
    @DisplayName("King의 점수를 계산한다")
    void calculateKingScore() {
        Piece king = King.getInstance(Color.WHITE);

        double score = PieceScore.addScore(king);

        assertThat(score).isEqualTo(0);
    }

    @Test
    @DisplayName("Rook의 점수를 계산한다")
    void calculateRookScore() {
        Piece rook = Rook.getInstance(Color.WHITE);

        double score = PieceScore.addScore(rook);

        assertThat(score).isEqualTo(5);
    }

    @Test
    @DisplayName("Knight의 점수를 계산한다")
    void calculateKnightScore() {
        Piece knight = Knight.getInstance(Color.WHITE);

        double score = PieceScore.addScore(knight);

        assertThat(score).isEqualTo(2.5);
    }

    @Test
    @DisplayName("Bishop의 점수를 계산한다")
    void calculateBishopScore() {
        Piece bishop = Bishop.getInstance(Color.WHITE);

        double score = PieceScore.addScore(bishop);

        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("Pawn의 점수를 계산한다")
    void calculatePawnScore() {
        Piece pawn = InitPawn.getInstance(Color.WHITE);

        double score = PieceScore.addScore(pawn);

        assertThat(score).isEqualTo(1);
    }
}
