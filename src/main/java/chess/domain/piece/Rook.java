package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {

    private static final List<Direction> ROOK_DIRECTION = List.of(
            Direction.POSITIVE_FILE_SAME_RANK,
            Direction.NEGATIVE_FILE_SAME_RANK,
            Direction.SAME_FILE_POSITIVE_RANK,
            Direction.SAME_FILE_NEGATIVE_RANK
    );
    private static final Map<Color, Rook> INSTANCE = Map.of(
            Color.WHITE, new Rook(Color.WHITE, ROOK_DIRECTION),
            Color.BLACK, new Rook(Color.BLACK, ROOK_DIRECTION)
    );

    private static final int MAX_UNIT_MOVE = 7;

    private Rook(Color color) {
        super(color, ROOK_DIRECTION);
    }

    private Rook(Color color, List<Direction> directions) {
        super(color, directions);
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
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }

    public static Rook getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
