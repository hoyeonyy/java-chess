package chess.domain.score;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class Score {

    public double calculateScoreByColor(List<Piece> survivePiece, Color color, int countPawnInSameFile) {
        double totalScore = survivePiece.stream()
                .mapToDouble(PieceScore::addScore)
                .sum();
        return totalScore - (countPawnInSameFile * 0.5);
    }
}
