package game.boards;

import game.position.MnkPositionRhombus;

public class MnkBoardRhombus extends MnkBoard {
    public MnkBoardRhombus(int side, int k) {
        super(new MnkPositionRhombus(side, k));
    }
}
