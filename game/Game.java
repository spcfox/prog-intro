package game;

import game.boards.Board;
import game.players.Player;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException("Players should be not null");
        }
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) throws PlayerException {
        Player currentPlayer = player1;
        int no = 1;
        while (true) {
            final Result result = move(board, currentPlayer, no);
            switch (result) {
                case WIN:
                    return no;
                case LOSE:
                    return 3 - no;
                case DRAW:
                    return 0;
                case UNKNOWN:
                    if (no == 1) {
                        no = 2;
                        currentPlayer = player2;
                    } else {
                        no = 1;
                        currentPlayer = player1;
                    }
            }
        }
    }

    private Result move(final Board board, final Player player, final int no) throws PlayerException {
        final Move move = player.move(board.getPosition());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
        } else if (result == Result.DRAW) {
            log("Draw");
        }
        return result;
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
