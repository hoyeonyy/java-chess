package chess.domain.piece;

import java.util.Collections;

public class Empty extends Piece {
    public Empty() {
        super(Color.EMPTY, Collections.emptyList());
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
        return 0;
    }
}
