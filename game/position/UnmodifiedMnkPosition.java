package game.position;

import game.Cell;

public class UnmodifiedMnkPosition extends MnkPosition {
    public UnmodifiedMnkPosition(MnkPosition position) {
        super(position);
    }

    @Override
    protected void generateCells() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCell(int row, int column, Cell value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nextPlayer() {
        throw new UnsupportedOperationException();
    }
}
