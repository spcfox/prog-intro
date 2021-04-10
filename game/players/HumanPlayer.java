package game.players;

import game.FastScanner;
import game.Move;
import game.PlayerException;
import game.position.Position;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position) throws PlayerException {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(position.getTurn() + "'s move");
            out.println("Enter row and column");
            int row, column;
            if (!in.hasNext()) {
                throw new PlayerException("The input stream has ended");
            }
            try {
                row = Integer.parseInt(in.next()) - 1;
                column = Integer.parseInt(in.next()) - 1;
            } catch (NumberFormatException e) {
                out.println("Incorrect input, try again");
                continue;
            }
            final Move move = new Move(row, column, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
