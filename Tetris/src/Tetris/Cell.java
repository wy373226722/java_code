package Tetris;

import java.awt.*;
import java.io.Serializable;

class Cell implements Serializable {
    private static final long serialVersionUID = 2L;
    private int row;
    private int col;
    private Image image;

    Cell(int row, int col, Image image) {
        this.row = row;
        this.col = col;
        this.image = image;
    }

    void moveLeft() {
        col--;
    }

    void moveRight() {
        col++;
    }

    void moveDown() {
        row++;
    }


    Image getImage() {
        return image;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "[" + row + "," + col + "]";
    }

    int getRow() {
        return row;
    }

    void setRow(int row) {
        this.row = row;
    }

    int getCol() {
        return col;
    }

    void setCol(int col) {
        this.col = col;
    }
}
