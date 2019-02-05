package project2;

import project2.Cell;
import project2.GameStatus;

import java.util.*;

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
    public Cell getCell(int row, int col) {
        Cell iCell = new Cell(row, col);
    }
}