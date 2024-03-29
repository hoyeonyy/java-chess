package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.score.Score;
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
        gameState = new InitGame();
    }

    public void run() {
        outputView.printInitMessage();
        Board board = BoardInitializer.createBoard();
        BoardDisplayConverter converter = new BoardDisplayConverter();

        playGame(board, converter);
    }

    private void playGame(Board board, BoardDisplayConverter converter) {
        while (true) {
            Command command = inputView.readCommand();
            if (command.isStart()) {
                startGame(board, converter);
            }
            if (command.isEnd()) {
                endGame();
                return;
            }
            if (command.isMove()) {
                move(board, converter);
            }
            if (command.isStatus()) {
                printStatus(board);
            }
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
        gameState = gameState.playTurn(board, source, destination);
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
        Score score = new Score();
        double whiteScore = score.calculateScoreByColor(board.survivePieceByColor(Color.WHITE), Color.WHITE);
        double blackScore = score.calculateScoreByColor(board.survivePieceByColor(Color.BLACK), Color.BLACK);
        System.out.println(whiteScore);
        System.out.println(blackScore);
    }
}
