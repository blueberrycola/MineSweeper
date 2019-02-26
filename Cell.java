package project2;

/**********************************************************************
 * Cell for MineSweeper
 *
 * @author Chase Johnston
 * @author Nicholas Berens
 * @version February 26, 2019
 *********************************************************************/

public class Cell {
    private int mineCount;
    private boolean isFlagged;
    private boolean isExposed;
    private boolean isMine;
    //Overriding JButton.isSelected because it makes the image icons grey which is boring
    private boolean isSelected;

    /****
     Cell constructor helps determine properties of tiles such as
     if it is flagged, if it is exposed, if it is a mine, and the
     mines neighboring the individual cell
     */
    public Cell() {
        mineCount = 0;
        isFlagged = false;
        isExposed = false;
        isMine = false;
    }

    /** Instantiation of instance variables */
    public Cell(int mineCount, boolean isFlagged, boolean isExposed, boolean isMine) {
        this.mineCount = mineCount;
        this.isFlagged = isFlagged;
        this.isExposed = isExposed;
        this.isMine = isMine;
    }

    /** Logic if the cell has been selected or not */
    public boolean isExposed() {
        if(this.isExposed) {
            return true;
        } else {
            return false;
        }
    }

    /** Logic if the cell has been flagged or not */
    public boolean isFlagged() {
        if(this.isFlagged) {
            return true;
        } else {
            return false;
        }
    }

    /** Logic if the individual cell contains a mine or not */
    public boolean isMine() {
        if(this.isMine) {
            return true;
        } else {
            return false;
        }

    }

    /** Getter for the number of neighboring mines */
    public int getMineCount() {
        return this.mineCount;
    }

    /** Setters for the conditions mentioned */
    public void setMine(boolean flag) { this.isMine = flag; }

    public void setExposed(boolean flag) { this.isExposed = flag; }

    public void setFlagged(boolean flag) { this.isFlagged = flag; }

    public void setMineCount(int count) {this.mineCount = count;}






}