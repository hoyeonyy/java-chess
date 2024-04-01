package chess.domain.piece;

import java.util.Map;

public class MovedPawn extends Pawn {

    private static final Map<Color, MovedPawn> INSTANCE = Map.of(
            Color.WHITE, new MovedPawn(Color.WHITE),
            Color.BLACK, new MovedPawn(Color.BLACK)
    );

    private static final int MAX_UNIT_MOVE = 1;

    private MovedPawn(Color color) {
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

    public static MovedPawn getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
