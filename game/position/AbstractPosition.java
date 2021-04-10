package game.position;

import game.Cell;
import game.Move;

public abstract class AbstractPosition implements Position {
    protected Cell[][] cells;
    protected Cell turn;
    protected final int rows;
    protected final int columns;
    protected int empties;

    public AbstractPosition(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new AssertionError("Rows and columns should be positive");
        }
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int getRows() {
        return rows;
    }
    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getEmpties() {
        return empties;
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    public void setCell(int row, int column, Cell value) {
        if (value != Cell.E && cells[row][column] == Cell.E) {
            empties--;
        }
        cells[row][column] = value;
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getColumn() && move.getColumn() < columns
                && getCell(move) == Cell.E
                && turn == move.getValue();
    }

    protected Cell getCell(Move move) {
        return getCell(move.getRow(), move.getColumn());
    }

    @Override
    public void nextPlayer() {
        turn = turn == Cell.X ? Cell.O : Cell.X;
    }
}
