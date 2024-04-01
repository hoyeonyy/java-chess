package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    private static final List<Direction> BISHOP_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK
    );
    private static final Map<Color, Bishop> INSTANCE = Map.of(
            Color.WHITE, new Bishop(Color.WHITE, BISHOP_DIRECTION),
            Color.BLACK, new Bishop(Color.BLACK, BISHOP_DIRECTION)
    );

    private static final int MAX_UNIT_MOVE = 7;

    private Bishop(Color color) {
        super(color, BISHOP_DIRECTION);
    }

    private Bishop(Color color, List<Direction> directions) {
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

    public static Bishop getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
