package game.position;

import game.Cell;

import java.util.Map;

public abstract class MnkPosition extends AbstractPosition {
    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.B, ' '
    );

    protected final int k;

    public MnkPosition(int rows, int columns, int k) {
        super(rows, columns);
        if (k <= 0) {
            throw new IllegalArgumentException("m, n, k should be positive");
        }
        this.turn = Cell.X;
        this.k = k;
        generateCells();
    }

    // for unmodified copy
    protected MnkPosition(MnkPosition position) {
        super(position.getRows(), position.getColumns());
        this.turn = position.turn;
        this.empties = position.empties;
        this.k = position.getK();
        this.cells = position.cells;
    }

    protected abstract void generateCells();

    public int getK() {
        return k;
    }

    @Override
    public String toString() {
        int cellWidth = (int) Math.log10(Math.max(columns, rows)) + 2;
        final StringBuilder sb = new StringBuilder(" ".repeat(cellWidth));
        for (int c = 0; c < getColumns(); c++) {
            String strNum = Integer.toString(c + 1);
            sb.append(" ".repeat(cellWidth - strNum.length())).append(strNum);
        }
        for (int r = 0; r < getRows(); r++) {
            sb.append("\n");
            String strNum = Integer.toString(r + 1);
            sb.append(" ".repeat(cellWidth - strNum.length())).append(strNum);
            for (int c = 0; c < getColumns(); c++) {
                sb.append(" ".repeat(cellWidth - 1)).append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
