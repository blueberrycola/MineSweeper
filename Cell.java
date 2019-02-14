package project2;

public class Cell {
    private int mineCount;
    private boolean isFlagged;
    private boolean isExposed;
    private boolean isMine;
    private int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        int mineCount = 0;
    }
    public boolean isExposed() {
        return false;
    }

    public boolean isFlagged() {
        return false;
    }

    public boolean isMine() {
        return false;
    }

    public void setMine(boolean flag) {

    }





}
