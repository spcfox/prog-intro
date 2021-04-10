package game;

import game.boards.*;
import game.players.*;

public class Main {
    public static void main(String[] args) {
        final Board board = new MnkBoardRhombus(4, 5);
//        final Board board = new MnkBoardRect(3, 4, 4);
        final Game game = new Game(true, new RandomPlayer(), new RandomPlayer());
        System.out.println(board);

        try {
            int result = game.play(board);
            System.out.println("Result: " + result);
        } catch (PlayerException e) {
            System.out.println("An error has occurred and the game cannot be continued");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
