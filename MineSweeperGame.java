package project2;
import java.util.*;
import java.lang.*;

public class MineSweeperGame {
    private Cell[][] board;
    private GameStatus status;
    private int totalMineCount = 10;
    private Random rng;
    private int mineCount;
    private boolean flagged, exposed, isMine;

    public MineSweeperGame() {
        board = new Cell[10][10];

    }
}