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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ChessGameDaoImpl implements ChessGameDao {
    private static final String INSERT_FROM_CHESSGAME = "INSERT INTO chess_game (state) VALUES (?)";
    private static final String SELECT_FROM_CHESSGAME = "SELECT state FROM chess_game";
    private static final String DELETE_FROM_CHESS_GAME = "DELETE FROM chess_game";
    private final PieceDao pieceDao;

    public ChessGameDaoImpl(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    @Override
    public void createChessGame(GameState gameState) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_FROM_CHESSGAME)) {
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
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FROM_CHESSGAME)) {
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
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_FROM_CHESS_GAME)) {
            pieceDao.removePieces();
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("게임을 삭제하는 데 실패했습니다.");
        }
    }
}
