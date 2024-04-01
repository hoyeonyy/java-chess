package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    private static final Map<Color, Knight> INSTANCE = Map.of(
            Color.WHITE, new Knight(Color.WHITE),
            Color.BLACK, new Knight(Color.BLACK)
    );

    private static final int MAX_UNIT_MOVE = 1;

    private Knight(Color color) {
        super(color, List.of());
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isMovable(Position source, Position destination) {
        return source.isOnKnightRoute(destination);
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }

    public static Knight getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
