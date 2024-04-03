package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceMapperTest {

    @Test
    @DisplayName("이름과 색깔을 입력받아 Piece 객체를 반환한다.")
    void mapToPiece() {
        assertAll(
                () -> assertThat(PieceMapper.mapToPiece("black", "initpawn")).isInstanceOf(InitPawn.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "movedpawn")).isInstanceOf(MovedPawn.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "rook")).isInstanceOf(Rook.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "knight")).isInstanceOf(Knight.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "bishop")).isInstanceOf(Bishop.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "queen")).isInstanceOf(Queen.class),
                () -> assertThat(PieceMapper.mapToPiece("black", "king")).isInstanceOf(King.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "initpawn")).isInstanceOf(InitPawn.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "movedpawn")).isInstanceOf(MovedPawn.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "rook")).isInstanceOf(Rook.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "knight")).isInstanceOf(Knight.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "bishop")).isInstanceOf(Bishop.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "queen")).isInstanceOf(Queen.class),
                () -> assertThat(PieceMapper.mapToPiece("white", "king")).isInstanceOf(King.class)
        );
    }

    @Test
    @DisplayName("이름과 색깔이 없다면 예외를 발생한다.")
    void mapToPiece_Exception() {
        assertAll(
                () -> assertThatThrownBy(() -> PieceMapper.mapToPiece("black", "pawn"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("존재하지 않는 기물입니다."),
                () -> assertThatThrownBy(() -> PieceMapper.mapToPiece("white", "pawn"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("존재하지 않는 기물입니다.")
        );
    }

    @Test
    @DisplayName("Piece 객체를 입력받아 이름을 반환한다.")
    void convertToPieceName() {
        assertAll(
                () -> assertThat(PieceMapper.convertToPieceName(InitPawn.getInstance(Color.WHITE))).isEqualTo(
                        "initpawn"),
                () -> assertThat(PieceMapper.convertToPieceName(MovedPawn.getInstance(Color.WHITE))).isEqualTo(
                        "movedpawn"),
                () -> assertThat(PieceMapper.convertToPieceName(Rook.getInstance(Color.WHITE))).isEqualTo("rook"),
                () -> assertThat(PieceMapper.convertToPieceName(Knight.getInstance(Color.WHITE))).isEqualTo("knight"),
                () -> assertThat(PieceMapper.convertToPieceName(Bishop.getInstance(Color.BLACK))).isEqualTo("bishop"),
                () -> assertThat(PieceMapper.convertToPieceName(Queen.getInstance(Color.BLACK))).isEqualTo("queen"),
                () -> assertThat(PieceMapper.convertToPieceName(King.getInstance(Color.BLACK))).isEqualTo("king")
        );
    }

    @Test
    @DisplayName("Piece를 입력받아 Color를 반환한다.")
    void convertColor() {
        assertAll(
                () -> assertThat(PieceMapper.convertColor(InitPawn.getInstance(Color.WHITE))).isEqualTo("white"),
                () -> assertThat(PieceMapper.convertColor(MovedPawn.getInstance(Color.WHITE))).isEqualTo("white"),
                () -> assertThat(PieceMapper.convertColor(Rook.getInstance(Color.WHITE))).isEqualTo("white"),
                () -> assertThat(PieceMapper.convertColor(Knight.getInstance(Color.WHITE))).isEqualTo("white"),
                () -> assertThat(PieceMapper.convertColor(Bishop.getInstance(Color.BLACK))).isEqualTo("black"),
                () -> assertThat(PieceMapper.convertColor(Queen.getInstance(Color.BLACK))).isEqualTo("black"),
                () -> assertThat(PieceMapper.convertColor(King.getInstance(Color.BLACK))).isEqualTo("black")
        );
    }
}
