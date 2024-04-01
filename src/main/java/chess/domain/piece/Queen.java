package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    private static final List<Direction> QUEEN_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK,
            Direction.POSITIVE_FILE_SAME_RANK,
            Direction.NEGATIVE_FILE_SAME_RANK,
            Direction.SAME_FILE_POSITIVE_RANK,
            Direction.SAME_FILE_NEGATIVE_RANK
    );

    private static final Map<Color, Queen> INSTANCE = Map.of(
            Color.WHITE, new Queen(Color.WHITE, QUEEN_DIRECTION),
            Color.BLACK, new Queen(Color.BLACK, QUEEN_DIRECTION)
    );

    private static final int MAX_UNIT_MOVE = 7;

    private Queen(Color color) {
        super(color, QUEEN_DIRECTION);
    }

    private Queen(Color color, List<Direction> directions) {
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

    public static Queen getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
