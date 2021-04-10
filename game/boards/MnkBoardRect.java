package game.boards;

import game.position.MnkPositionRect;

public class MnkBoardRect extends MnkBoard {
    public MnkBoardRect(int m, int n, int k) {
        super(new MnkPositionRect(m, n, k));
    }
}
