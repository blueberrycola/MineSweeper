package project2;

public class Cell {
    private int mineCount;
    private boolean isFlagged;
    private boolean isExposed;
    private boolean isMine;
    //Overriding JButton.isSelected because it makes the image icons grey which is boring
    private boolean isSelected;

    public Cell() {
        mineCount = 0;
        isFlagged = false;
        isExposed = false;
        isMine = false;
    }
    public Cell(int mineCount, boolean isFlagged, boolean isExposed, boolean isMine) {
        this.mineCount = mineCount;
        this.isFlagged = isFlagged;
        this.isExposed = isExposed;
        this.isMine = isMine;
    }
    public boolean isExposed() {
        if(this.isExposed) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFlagged() {
        if(this.isFlagged) {
            return true;
        } else {
            return false;
        }
    }




    public boolean isMine() {
        if(this.isMine) {
            return true;
        } else {
            return false;
        }

    }

    public void setMine(boolean flag) { this.isMine = flag; }

    public void setExposed(boolean flag) { this.isExposed = flag; }

    public void setFlagged(boolean flag) { this.isFlagged = flag; }






}
