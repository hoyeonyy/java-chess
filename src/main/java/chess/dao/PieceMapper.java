package chess.dao;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.InitPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;

public enum PieceMapper {

    BLACK_INIT_PAWN("black", "initpawn", InitPawn.getInstance(Color.BLACK)),
    BLACK_MOVED_PAWN("black", "movedpawn", MovedPawn.getInstance(Color.BLACK)),
    BLACK_ROOK("black", "rook", Rook.getInstance(Color.BLACK)),
    BLACK_KNIGHT("black", "knight", Knight.getInstance(Color.BLACK)),
    BLACK_BISHOP("black", "bishop", Bishop.getInstance(Color.BLACK)),
    BLACK_QUEEN("black", "queen", Queen.getInstance(Color.BLACK)),
    BLACK_KING("black", "king", King.getInstance(Color.BLACK)),

    WHITE_INIT_PAWN("white", "initpawn", InitPawn.getInstance(Color.WHITE)),
    WHITE_MOVED_PAWN("white", "movedpawn", MovedPawn.getInstance(Color.WHITE)),
    WHITE_ROOK("white", "rook", Rook.getInstance(Color.WHITE)),
    WHITE_KNIGHT("white", "knight", Knight.getInstance(Color.WHITE)),
    WHITE_BISHOP("white", "bishop", Bishop.getInstance(Color.WHITE)),
    WHITE_QUEEN("white", "queen", Queen.getInstance(Color.WHITE)),
    WHITE_KING("white", "king", King.getInstance(Color.WHITE)),
    ;

    private final String color;
    private final String pieceName;
    private final Piece piece;

    PieceMapper(String color, String pieceName, Piece piece) {
        this.color = color;
        this.pieceName = pieceName;
        this.piece = piece;
    }

    public static Piece mapToPiece(String color, String pieceName) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.hasColorAndPieceName(color, pieceName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."))
                .piece;
    }

    public static String convertToPieceName(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.piece == piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."))
                .pieceName;
    }

    public static String convertColor(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.piece == piece)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."))
                .color;
    }

    private boolean hasColorAndPieceName(String color, String pieceName) {
        return this.color.equals(color) && this.pieceName.equals(pieceName);
    }
}
