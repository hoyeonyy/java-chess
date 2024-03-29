package chess.domain.score;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class Score {

    public double calculateScoreByColor(List<Piece> survivePiece, Color color) {
        double score = 0.0;
        for (Piece piece : survivePiece) {
            score = score + PieceScore.addScore(piece);
        }
        return score;
    }
}
