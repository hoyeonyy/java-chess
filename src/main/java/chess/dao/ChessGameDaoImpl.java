package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.BlackTurn;
import chess.domain.game.EndGame;
import chess.domain.game.GameState;
import chess.domain.game.InitGame;
import chess.domain.game.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ChessGameDaoImpl implements ChessGameDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final PieceDao pieceDao;

    public ChessGameDaoImpl(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

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
    public void createChessGame(GameState gameState) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO chess_game (state) VALUES (?)")) {
            statement.setString(1, gameState.getStateName());
            removeGame();
            statement.executeUpdate();
            pieceDao.saveAllPieces(gameState.getPieces());
        } catch (SQLException e) {
            throw new IllegalStateException("게임을 생성하는 데 실패했습니다.");
        }
    }

    @Override
    public GameState findGame() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT state FROM chess_game")) {
            return retrieveChessGameFrom(statement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("게임을 조회하는 데 실패했습니다.");
        }
    }

    private GameState retrieveChessGameFrom(ResultSet resultSet) throws SQLException {
        String state = "";
        if (resultSet.next()) {
            state = resultSet.getString("state");
        }
        Map<Position, Piece> pieces = pieceDao.findPieces();
        if (state.equals("end")) {
            return new EndGame(new Board(pieces));
        }
        if (state.equals("white")) {
            return new WhiteTurn(new Board(pieces));
        }
        if (state.equals("black")) {
            return new BlackTurn(new Board(pieces));
        }
        return InitGame.createInitGame();
    }

    @Override
    public void removeGame() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM chess_game")) {
            pieceDao.removePieces();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("게임을 삭제하는 데 실패했습니다.");
        }
    }
}
