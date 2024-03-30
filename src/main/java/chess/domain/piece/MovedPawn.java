package chess.domain.piece;

public class MovedPawn extends Pawn {

    private static final int MAX_UNIT_MOVE = 1;

    public MovedPawn(Color color) {
        super(color);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }
}
