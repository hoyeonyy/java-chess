package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoImpl implements PieceDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveAllPieces(Map<Position, Piece> pieces) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO piece (file, `rank`, type, color) VALUES (?, ?, ?, ?)")) {
            removePieces();
            for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                Position position = entry.getKey();
                Piece piece = entry.getValue();
                statement.setString(1, position.getFileName());
                statement.setInt(2, position.getRankNumber());
                statement.setString(3, PieceMapper.convertToPieceName(piece));
                statement.setString(4, PieceMapper.convertColor(piece));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("말을 저장하는 데 실패했습니다.");
        }
    }

    @Override
    public Map<Position, Piece> findPieces() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT file, `rank`, type, color FROM piece")) {
            ResultSet resultSet = statement.executeQuery();
            return mapToPieces(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("말을 조회하는 데 실패했습니다.");
        }
    }

    private Map<Position, Piece> mapToPieces(ResultSet resultSet) throws SQLException {
        Map<Position, Piece> pieces = new HashMap<>();
        while (resultSet.next()) {
            File file = File.from(resultSet.getString("file"));
            Rank rank = Rank.from(resultSet.getInt("rank"));
            Position position = Position.of(file, rank);
            Piece piece = PieceMapper.mapToPiece(resultSet.getString("color"), resultSet.getString("type"));
            pieces.put(position, piece);
        }
        return pieces;
    }

    @Override
    public void removePieces() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM piece")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("기물을 삭제하는 데 실패했습니다.");
        }
    }
}
