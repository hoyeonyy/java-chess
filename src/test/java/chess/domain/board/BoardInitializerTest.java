package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    @Test
    @DisplayName("보드 생성 테스트")
    void createBoardTest() {
        Map<Position, Piece> pieces = new HashMap<>();
        makeBoard(pieces);

        Board initializerBoard = BoardInitializer.createBoard();
        Board board = new Board(pieces);

        assertAll(
                () -> assertThat(pieces.get(Position.of(File.A, Rank.ONE))).isExactlyInstanceOf(Rook.class),
                () -> assertThat(pieces.get(Position.of(File.B, Rank.ONE))).isExactlyInstanceOf(Knight.class),
                () -> assertThat(pieces.get(Position.of(File.C, Rank.ONE))).isExactlyInstanceOf(Bishop.class),
                () -> assertThat(pieces.get(Position.of(File.D, Rank.ONE))).isExactlyInstanceOf(Queen.class),
                () -> assertThat(pieces.get(Position.of(File.E, Rank.ONE))).isExactlyInstanceOf(King.class),
                () -> assertThat(pieces.get(Position.of(File.F, Rank.ONE))).isExactlyInstanceOf(Bishop.class),
                () -> assertThat(pieces.get(Position.of(File.G, Rank.ONE))).isExactlyInstanceOf(Knight.class),
                () -> assertThat(pieces.get(Position.of(File.H, Rank.ONE))).isExactlyInstanceOf(Rook.class),
                () -> assertThat(pieces.get(Position.of(File.A, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.B, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.C, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.D, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.E, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.F, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.G, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.H, Rank.TWO))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.A, Rank.EIGHT))).isExactlyInstanceOf(Rook.class),
                () -> assertThat(pieces.get(Position.of(File.B, Rank.EIGHT))).isExactlyInstanceOf(Knight.class),
                () -> assertThat(pieces.get(Position.of(File.C, Rank.EIGHT))).isExactlyInstanceOf(Bishop.class),
                () -> assertThat(pieces.get(Position.of(File.D, Rank.EIGHT))).isExactlyInstanceOf(Queen.class),
                () -> assertThat(pieces.get(Position.of(File.E, Rank.EIGHT))).isExactlyInstanceOf(King.class),
                () -> assertThat(pieces.get(Position.of(File.F, Rank.EIGHT))).isExactlyInstanceOf(Bishop.class),
                () -> assertThat(pieces.get(Position.of(File.G, Rank.EIGHT))).isExactlyInstanceOf(Knight.class),
                () -> assertThat(pieces.get(Position.of(File.H, Rank.EIGHT))).isExactlyInstanceOf(Rook.class),
                () -> assertThat(pieces.get(Position.of(File.A, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.B, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.C, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.D, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.E, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.F, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.G, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class),
                () -> assertThat(pieces.get(Position.of(File.H, Rank.SEVEN))).isExactlyInstanceOf(InitPawn.class)
        );

    }

    private void makeBoard(Map<Position, Piece> pieces) {
        pieces.put(Position.of(File.A, Rank.ONE), Rook.getInstance(Color.WHITE));
        pieces.put(Position.of(File.B, Rank.ONE), Knight.getInstance(Color.WHITE));
        pieces.put(Position.of(File.C, Rank.ONE), Bishop.getInstance(Color.WHITE));
        pieces.put(Position.of(File.D, Rank.ONE), Queen.getInstance(Color.WHITE));
        pieces.put(Position.of(File.E, Rank.ONE), King.getInstance(Color.WHITE));
        pieces.put(Position.of(File.F, Rank.ONE), Bishop.getInstance(Color.WHITE));
        pieces.put(Position.of(File.G, Rank.ONE), Knight.getInstance(Color.WHITE));
        pieces.put(Position.of(File.H, Rank.ONE), Rook.getInstance(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.B, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.C, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.D, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.E, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.F, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.G, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.H, Rank.TWO), InitPawn.getInstance(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.EIGHT), Rook.getInstance(Color.BLACK));
        pieces.put(Position.of(File.B, Rank.EIGHT), Knight.getInstance(Color.BLACK));
        pieces.put(Position.of(File.C, Rank.EIGHT), Bishop.getInstance(Color.BLACK));
        pieces.put(Position.of(File.D, Rank.EIGHT), Queen.getInstance(Color.BLACK));
        pieces.put(Position.of(File.E, Rank.EIGHT), King.getInstance(Color.BLACK));
        pieces.put(Position.of(File.F, Rank.EIGHT), Bishop.getInstance(Color.BLACK));
        pieces.put(Position.of(File.G, Rank.EIGHT), Knight.getInstance(Color.BLACK));
        pieces.put(Position.of(File.H, Rank.EIGHT), Rook.getInstance(Color.BLACK));
        pieces.put(Position.of(File.A, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.B, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.C, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.D, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.E, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.F, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.G, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
        pieces.put(Position.of(File.H, Rank.SEVEN), InitPawn.getInstance(Color.BLACK));
    }
}
