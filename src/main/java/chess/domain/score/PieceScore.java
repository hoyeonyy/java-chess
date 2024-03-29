package chess.domain.score;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceScore {

    PAWN(Pawn.class, 1),
    KNIGHT(Knight.class, 2.5),
    BISHOP(Bishop.class, 3),
    ROOK(Rook.class, 5),
    QUEEN(Queen.class, 9),
    KING(King.class, 0);

    private final Class<? extends Piece> pieceType;
    private final double score;

    PieceScore(Class<? extends Piece> pieceType, double score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static double addScore(Piece piece) {
        return Arrays.stream(values())
                .filter(value -> isSamePiece(piece, value))
                .findFirst()
                .map(PieceScore -> PieceScore.score)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 기물입니다."));
    }

    private static boolean isSamePiece(Piece piece, PieceScore value) {
        return value.pieceType.isInstance(piece);
    }
}
