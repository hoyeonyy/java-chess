package chess;

import chess.dao.ChessGameDao;
import chess.dao.ChessGameDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.domain.game.GameState;
import chess.domain.game.InitGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionDto;
import chess.view.display.BoardDisplayConverter;
import chess.view.display.RankDisplay;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;
    private GameState gameState;
    private final ChessGameDao chessGameDao;

    public ChessGame() {
        inputView = new InputView();
        outputView = new OutputView();
        gameState = InitGame.createInitGame();
        chessGameDao = new ChessGameDaoImpl(new PieceDaoImpl());
    }

    public ChessGame(GameState gameState) {
        inputView = new InputView();
        outputView = new OutputView();
        this.gameState = gameState;
        chessGameDao = new ChessGameDaoImpl(new PieceDaoImpl());
    }

    public void run() {
        outputView.printInitMessage();

        playGame(new BoardDisplayConverter());
    }

    private void playGame(BoardDisplayConverter converter) {
        Command command = inputView.readCommand();
        if (command.isStart()) {
            startGame(converter);
            playGame(converter);
        }
        if (command.isEnd()) {
            endGame();
            return;
        }
        if (command.isMove()) {
            move(converter);
            playGame(converter);
        }
        if (command.isStatus()) {
            printStatus();
            playGame(converter);
        }
        if (command.isLoad()) {
            loadGame(converter);
            playGame(converter);
        }
    }

    private void startGame(BoardDisplayConverter converter) {
        printBoard(converter, gameState.getPieces());
        gameState = gameState.startGame();
        chessGameDao.createChessGame(gameState);
    }

    private void endGame() {
        gameState = gameState.endGame();
    }

    private void move(BoardDisplayConverter converter) {
        Position source = readPosition();
        Position destination = readPosition();
        gameState = gameState.playTurn(source, destination);
        printBoard(converter, gameState.getPieces());
        chessGameDao.createChessGame(gameState);
    }

    private void loadGame(BoardDisplayConverter converter) {
        gameState = chessGameDao.findGame();
        printBoard(converter, gameState.getPieces());
    }

    private Position readPosition() {
        PositionDto positionDto = inputView.readPosition();
        File file = File.from(positionDto.fileName());
        Rank rank = Rank.from(positionDto.rankNumber());
        return Position.of(file, rank);
    }

    private void printBoard(BoardDisplayConverter converter, Map<Position, Piece> pieces) {
        List<RankDisplay> rankDisplays = converter.convert(pieces);
        outputView.printBoard(rankDisplays);
    }

    private void printStatus() {
        double whiteScore = gameState.calculateScoreBy(Color.WHITE);
        double blackScore = gameState.calculateScoreBy(Color.BLACK);

        outputView.printScore(whiteScore, blackScore);
    }
}
