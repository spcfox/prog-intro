package game.position;

import game.Cell;

import java.util.Arrays;

public class MnkPositionRect extends MnkPosition {
    public MnkPositionRect(int rows, int columns, int k) {
        super(rows, columns, k);
    }

    @Override
    protected void generateCells() {
        empties = rows * columns;
        cells = new Cell[rows][columns];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }
}
