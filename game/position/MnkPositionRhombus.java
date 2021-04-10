package game.position;

import game.Cell;

public class MnkPositionRhombus extends MnkPosition {
    final int side;

    public MnkPositionRhombus(int side, int k) {
        super(2 * side - 1, 2 * side - 1, k);
        this.side = side;
    }

    @Override
    protected void generateCells() {
        int side = (rows + 1) / 2;
        empties = side * side + (side - 1) * (side - 1);
        cells = new Cell[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < rows; c++) {
                if (Math.abs(r + c - 2 * side + 2) < side
                        && Math.abs(Math.abs(r - c) - 3 * side / 2) >= side / 2 + 1) {
                    cells[r][c] = Cell.E;
                } else {
                    cells[r][c] = Cell.B;
                }
            }
        }
    }
}
