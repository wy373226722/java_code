package Tetris;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

class Tetromino implements Serializable {
    private static final long serialVersionUID = 1L;
    Cell[] cells;
    State[] states;

    public void moveRight() {
        for (Cell cell : cells) {
            cell.moveRight();
        }
    }

    public void moveLeft() {
        for (Cell cell : cells) {
            cell.moveLeft();
        }
    }

    public void softDrop() {
        for (Cell cell : cells) {
            cell.moveDown();
        }
    }

    private int index = 28000;

    public void rotateRight() {
        index++;
        State state = states[index % states.length];
        Cell o = cells[0];
        cells[1].setRow(o.getRow() + state.row1);
        cells[1].setCol(o.getCol() + state.col1);
        cells[2].setRow(o.getRow() + state.row2);
        cells[2].setCol(o.getCol() + state.col2);
        cells[3].setRow(o.getRow() + state.row3);
        cells[3].setCol(o.getCol() + state.col3);
    }

    public void rotateLeft() {
        index--;
        State state = states[index % states.length];
        Cell o = cells[0];
        cells[1].setRow(o.getRow() + state.row1);
        cells[1].setCol(o.getCol() + state.col1);
        cells[2].setRow(o.getRow() + state.row2);
        cells[2].setCol(o.getCol() + state.col2);
        cells[3].setRow(o.getRow() + state.row3);
        cells[3].setCol(o.getCol() + state.col3);
    }

    static Tetromino randomTetromino() {
        Random random = new Random();
        int shape = random.nextInt(8);
        switch (shape) {
            case 0:
                return new T();
            case 1:
                return new I();
            case 2:
                return new O();
            case 3:
                return new L();
            case 4:
                return new J();
            case 5:
                return new Z();
            case 6:
                return new S();// 可以通过增加相应形状来改变出现频率
            case 7:
                return new Q();
//            case 8:
//                return new X();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Tetromino [cells=" + Arrays.toString(cells) + "]";
    }

    class State {
        int row0, col0, row1, col1, row2, col2, row3, col3;

        State(int row0, int col0, int row1, int col1, int row2,
              int col2, int row3, int col3) {
            super();
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }
    }
}

class T extends Tetromino {
    T() {
        cells = new Cell[]{new Cell(0, 4, Tetris.T),
                new Cell(0, 3, Tetris.T), new Cell(0, 5, Tetris.T),
                new Cell(1, 4, Tetris.T)};
        states = new State[]{new State(0, 0, 0, -1, 0, 1, 1, 0),
                new State(0, 0, -1, 0, 1, 0, 0, -1),
                new State(0, 0, 0, 1, 0, -1, -1, 0),
                new State(0, 0, 1, 0, -1, 0, 0, 1)};
    }

    public T(Cell[] cells, State[] states) {
        this.cells = cells;
        this.states = states;
    }
}

class I extends Tetromino {
    I() {
        cells = new Cell[]{new Cell(0, 4, Tetris.I),
                new Cell(0, 3, Tetris.I), new Cell(0, 5, Tetris.I),
                new Cell(0, 6, Tetris.I)};
        states = new State[]{new State(0, 0, 0, -1, 0, 1, 0, 2),
                new State(0, 0, -1, 0, 1, 0, 2, 0)};
    }

    public I(Cell[] cells, State[] states) {
        this.cells = cells;
        this.states = states;
    }
}

class O extends Tetromino {
    O() {
        cells = new Cell[]{new Cell(0, 4, Tetris.O),
                new Cell(0, 5, Tetris.O), new Cell(1, 4, Tetris.O),
                new Cell(1, 5, Tetris.O)};
        states = new State[]{new State(0, 0, 0, 1, 1, 0, 1, 1)};
    }

    public O(Cell[] cells, State[] states) {
        this.cells = cells;
        this.states = states;
    }
}

class L extends Tetromino {
    L() {
        cells = new Cell[]{new Cell(0, 4, Tetris.L),
                new Cell(0, 3, Tetris.L), new Cell(0, 5, Tetris.L),
                new Cell(1, 3, Tetris.L)};
        states = new State[]{new State(0, 0, 0, -1, 0, 1, 1, -1),
                new State(0, 0, -1, 0, 1, 0, -1, -1),
                new State(0, 0, 0, 1, 0, -1, -1, 1),
                new State(0, 0, 1, 0, -1, 0, 1, 1)};
    }

    public L(Cell[] cells, State[] states) {
        this.cells = cells;
        this.states = states;
    }
}

class J extends Tetromino {
    J() {
        cells = new Cell[]{new Cell(0, 4, Tetris.J),
                new Cell(0, 3, Tetris.J), new Cell(0, 5, Tetris.J),
                new Cell(1, 5, Tetris.J)};
        states = new State[]{new State(0, 0, 0, -1, 0, 1, 1, 1),
                new State(0, 0, -1, 0, 1, 0, 1, -1),
                new State(0, 0, 0, 1, 0, -1, -1, -1),
                new State(0, 0, 1, 0, -1, 0, -1, 1)};
    }
}

class Z extends Tetromino {
    Z() {
        cells = new Cell[]{new Cell(0, 4, Tetris.Z),
                new Cell(0, 3, Tetris.Z), new Cell(1, 4, Tetris.Z),
                new Cell(1, 5, Tetris.Z)};
        states = new State[]{new State(0, 0, 0, -1, 1, 0, 1, 1),
                new State(0, 0, -1, 0, 0, -1, 1, -1)};
    }
}

class S extends Tetromino {
    S() {
        cells = new Cell[]{new Cell(0, 4, Tetris.S),
                new Cell(0, 5, Tetris.S), new Cell(1, 3, Tetris.S),
                new Cell(1, 4, Tetris.S)};
        states = new State[]{new State(0, 0, 0, 1, 1, -1, 1, 0),
                new State(0, 0, -1, 0, 1, 1, 0, 1)};
    }
}

class Q extends Tetromino {
    Q() {
        cells = new Cell[]{new Cell(0, 4, Tetris.Q)};
    }

    @Override
    public void moveRight() {
        super.moveRight();
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
    }

    @Override
    public void softDrop() {
        super.softDrop();
    }

    @Override
    public void rotateRight() {
    }

    @Override
    public void rotateLeft() {
    }
}

