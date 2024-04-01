package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public interface PieceDao {
    void saveAllPieces(Map<Position, Piece> pieces);

    Map<Position, Piece> findPieces();

    void removePieces();
}
