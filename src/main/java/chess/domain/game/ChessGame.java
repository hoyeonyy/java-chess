package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
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

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;
    private GameState gameState;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitMessage();
        Board board = BoardInitializer.createBoard();
        BoardDisplayConverter converter = new BoardDisplayConverter();
        gameState = new InitGame(board);

        playGame(board, converter);
    }

    private void playGame(Board board, BoardDisplayConverter converter) {
        Command command = inputView.readCommand();
        if (command.isStart()) {
            startGame(board, converter);
            playGame(board, converter);
        }
        if (command.isEnd()) {
            endGame();
            return;
        }
        if (command.isMove()) {
            move(board, converter);
            playGame(board, converter);
        }
        if (command.isStatus()) {
            printStatus(board);
            playGame(board, converter);
        }
    }

    private void startGame(Board board, BoardDisplayConverter converter) {
        printBoard(converter, board);
        gameState = gameState.startGame();
    }

    private void endGame() {
        gameState = gameState.endGame();
    }

    private void move(Board board, BoardDisplayConverter converter) {
        Position source = readPosition();
        Position destination = readPosition();
        gameState = gameState.playTurn(source, destination);
        printBoard(converter, board);
    }

    private Position readPosition() {
        PositionDto positionDto = inputView.readPosition();
        File file = File.from(positionDto.fileName());
        Rank rank = Rank.from(positionDto.rankNumber());
        return Position.of(file, rank);
    }

    private void printBoard(BoardDisplayConverter converter, Board board) {
        List<RankDisplay> rankDisplays = converter.convert(board.pieces());
        outputView.printBoard(rankDisplays);
    }

    private void printStatus(Board board) {
        double whiteScore = board.calculateScoreByColor(Color.WHITE);
        double blackScore = board.calculateScoreByColor(Color.BLACK);

        outputView.printScore(whiteScore, blackScore);
    }
}
