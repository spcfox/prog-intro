package game.players;

import game.Move;
import game.position.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position) {
        while (true) {
            int r = random.nextInt(position.getRows());
            int c = random.nextInt(position.getColumns());
            final Move move = new Move(r, c, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
