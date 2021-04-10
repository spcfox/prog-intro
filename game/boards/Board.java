package game.boards;

import game.Move;
import game.Result;
import game.position.Position;

public interface Board {
    Position getPosition();
    Result makeMove(Move move);
}
