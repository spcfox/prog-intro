package game.boards;

import game.Move;
import game.Result;
import game.position.MnkPosition;
import game.position.Position;
import game.position.UnmodifiedMnkPosition;

import java.util.SortedMap;

public abstract class MnkBoard implements Board {
    protected final MnkPosition position;

    public MnkBoard(MnkPosition position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return new UnmodifiedMnkPosition(position);
    }

    @Override
    public Result makeMove(final Move move) {
        if (!position.isValid(move)) {
            return Result.LOSE;
        }

        position.setCell(move.getRow(), move.getColumn(), move.getValue());

        int horizontal = findMaxDistantPlayersCell(move, 1, 0)
                + findMaxDistantPlayersCell(move, -1, 0) + 1;
        int vertical = findMaxDistantPlayersCell(move, 0, 1)
                + findMaxDistantPlayersCell(move, 0, -1) + 1;
        int diagonal1 = findMaxDistantPlayersCell(move, 1, 1)
                + findMaxDistantPlayersCell(move, -1, -1) + 1;
        int diagonal2 = findMaxDistantPlayersCell(move, 1, -1)
                + findMaxDistantPlayersCell(move, -1, 1) + 1;

        if (horizontal >= position.getK() || vertical >= position.getK()
                || diagonal1 >= position.getK() || diagonal2 >= position.getK()) {
            return Result.WIN;
        }

        if (position.getEmpties() == 0) {
            return Result.DRAW;
        }

        if (horizontal >= 4 || vertical >= 4 || diagonal1 >= 4 || diagonal2 >= 4) {
            return Result.EXTRA;
        }

        position.nextPlayer();
        return Result.UNKNOWN;
    }

    private int findMaxDistantPlayersCell(Move move, int directionX, int directionY) {
        int column = move.getColumn();
        int row = move.getRow();
        for (int i = 1; i < position.getK(); i++) {
            column += directionX;
            row += directionY;
            if (row < 0 || row >= position.getRows() || column < 0 || column >= position.getColumns()
                    || position.getCell(row, column) != move.getValue()) {
                return i - 1;
            }
        }
        return position.getK() - 1;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
