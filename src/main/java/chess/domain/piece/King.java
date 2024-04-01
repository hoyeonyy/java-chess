package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    private static final List<Direction> KING_DIRECTION = List.of(
            Direction.POSITIVE_FILE_POSITIVE_RANK,
            Direction.POSITIVE_FILE_NEGATIVE_RANK,
            Direction.NEGATIVE_FILE_POSITIVE_RANK,
            Direction.NEGATIVE_FILE_NEGATIVE_RANK,
            Direction.POSITIVE_FILE_SAME_RANK,
            Direction.NEGATIVE_FILE_SAME_RANK,
            Direction.SAME_FILE_POSITIVE_RANK,
            Direction.SAME_FILE_NEGATIVE_RANK
    );
    private static final Map<Color, King> INSTANCE = Map.of(
            Color.WHITE, new King(Color.WHITE, KING_DIRECTION),
            Color.BLACK, new King(Color.BLACK, KING_DIRECTION)
    );

    private static final int MAX_UNIT_MOVE = 1;

    private King(Color color) {
        super(color, KING_DIRECTION);
    }

    private King(Color color, List<Direction> directions) {
        super(color, directions);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int getMaxUnitMove() {
        return MAX_UNIT_MOVE;
    }

    public static King getInstance(Color color) {
        return INSTANCE.get(color);
    }
}
