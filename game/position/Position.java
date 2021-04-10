package game.position;

import game.Cell;
import game.Move;

public interface Position {
    boolean isValid(Move move);
    int getRows();
    int getColumns();
    int getEmpties();
    Cell getCell(int r, int c);
    Cell getTurn();
    void nextPlayer();
}
