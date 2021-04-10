package game.players;

import game.Move;
import game.position.Position;

public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position) {
        for (int r = 0; r < position.getRows(); r++) {
            for (int c = 0; c < position.getColumns(); c++) {
                final Move move = new Move(r, c, position.getTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
