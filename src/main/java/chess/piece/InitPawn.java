package chess.piece;

import chess.board.Position;

public class InitPawn extends Piece {

    private static final int MAX_UNIT_MOVE = 2;

    public InitPawn(Color color) {
        super(PieceType.PAWN, color, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return false;
//        return source.isOnSameFile(destination) && source.isForward(destination, MAX_UNIT_MOVE);
    }

    @Override
    public boolean isAttackable(Position source, Position destination) {
        return false;
    }
}
