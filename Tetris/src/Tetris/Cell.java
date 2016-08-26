package Tetris;

import java.awt.*;
import java.io.Serializable;

/**
 * 这代表一个方格的类，方格有坐标，其上的贴图属性，及向左，向右，向下移动属性
 */
class Cell implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2L;
    private int row; //相当于方格x坐标
    private int col; //相当于方格y坐标
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
