package chess.domain.piece;

import java.util.Map;

public class InitPawn extends Pawn {

    private static final Map<Color, InitPawn> INSTANCE = Map.of(
            Color.WHITE, new InitPawn(Color.WHITE),
            Color.BLACK, new InitPawn(Color.BLACK)
    );

    private static final int MAX_UNIT_MOVE = 2;

    private InitPawn(Color color) {
        super(color);
    }

    @Override
    public boolean isInitPawn() {
        return true;
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

    public static InitPawn getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
