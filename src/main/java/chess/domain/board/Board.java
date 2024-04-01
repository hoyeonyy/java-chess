package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.score.PieceScore;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece move(Position source, Position destination) {
        validatePieceExistsAt(source);
        validateAllyPieceNotOnDestination(source, destination);
        validateNoPiecesBetween(source, destination);

        Piece piece = pieces.get(source);
        if (hasEnemyPieceOn(destination, piece)) {
            validateAttackable(source, destination, piece);
            return replacePiece(source, destination, piece);
        }
        validateMovable(source, destination, piece);
        replacePiece(source, destination, piece);
        return new Empty();
    }

    private void validatePieceExistsAt(Position source) {
        if (!pieces.containsKey(source)) {
            throw new IllegalArgumentException("출발 칸에 기물이 없습니다.");
        }
    }

    private void validateAllyPieceNotOnDestination(Position source, Position destination) {
        Piece sourcePiece = pieces.get(source);
        if (!pieces.containsKey(destination)) {
            return;
        }
        Piece destinationPiece = pieces.get(destination);
        if (destinationPiece.hasSameColorWith(sourcePiece)) {
            throw new IllegalArgumentException("도착 칸에 자신의 기물이 있습니다.");
        }
    }

    private void validateNoPiecesBetween(Position source, Position destination) {
        if (source.isOnKnightRoute(destination)) {
            return;
        }
        Path path = Path.createExcludingBothEnds(source, destination);
        if (path.hasPieceOnRoute(pieces)) {
            throw new IllegalArgumentException("경로에 기물이 있습니다.");
        }
    }

    private boolean hasEnemyPieceOn(Position destination, Piece piece) {
        return pieces.containsKey(destination) &&
                pieces.get(destination).hasDifferentColorWith(piece);
    }

    private void validateAttackable(Position source, Position destination, Piece piece) {
        if (!piece.isAttackable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private void validateMovable(Position source, Position destination, Piece piece) {
        if (!piece.isMovable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private Piece replacePiece(Position source, Position destination, Piece piece) {
        pieces.remove(source);
        Piece removePiece = pieces.get(destination);
        if (piece.isInitPawn()) {
            pieces.put(destination, MovedPawn.getInstance(piece.getColor()));
            return removePiece;
        }
        pieces.put(destination, piece);
        return removePiece;
    }

    public double calculateScoreByColor(Color color) {
        List<Piece> survivePiece = survivePieceByColor(color);
        int countPawnInSameFile = countPawnInSameFile(color);
        double totalScore = survivePiece.stream()
                .mapToDouble(PieceScore::addScore)
                .sum();
        return totalScore - (countPawnInSameFile * 0.5);
    }

    private List<Piece> survivePieceByColor(Color color) {
        return pieces.values().stream()
                .filter(piece -> piece.hasColorOf(color))
                .collect(Collectors.toList());
    }

    private int countPawnInSameFile(Color color) {
        return Arrays.stream(File.values())
                .mapToInt(file -> countDuplicatePawnInFile(file, color))
                .sum();
    }

    private int countDuplicatePawnInFile(File file, Color color) {
        List<Position> positionsSameFile = Position.getPositionsSameFile(file);
        int countPawn = countPawn(positionsSameFile, color);
        if (countPawn >= 2) {
            return countPawn;
        }
        return 0;
    }

    private int countPawn(List<Position> positionsSameFile, Color color) {
        return (int) positionsSameFile.stream()
                .map(position -> pieces.getOrDefault(position, new Empty()))
                .filter(piece -> piece.isPawn() && piece.hasColorOf(color))
                .count();
    }

    public Map<Position, Piece> pieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
