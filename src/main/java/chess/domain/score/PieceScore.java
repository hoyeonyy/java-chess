package chess.domain.score;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

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
}
