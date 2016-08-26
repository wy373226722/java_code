package Tetris;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.imageio.ImageIO;


public class Tetris extends JPanel {
    static Image I;
    static Image J;
    static Image L;
    static Image S;
    static Image Z;
    static Image O;
    static Image T;
    static Image Q;
    private static Image game_over;
    private static Image game_pause;
    private static Image background_image;

    private Timer timer;
    private Cell[][] wall = new Cell[ROWS][COLS];// 墙
    private Tetromino nextOne;// 下一个下落的方块
    private Tetromino tetromino;// 正在下落的方块
    private int lines;// 消掉的行数
    private int score;
    private int speed;
    private int lastRecordScor;
    private int lastRecordTime;
    private int lastRecordLine;
    private int lastRecordSS;
    private int lastRecordST;
    private int lastRecordSL;
    private int count_Tetrominos;
    private long myBreak;
    private long time;
    private long periodTime;
    private long start;
    private boolean pause;
    private boolean gameOver;
    private boolean isUsedSA = false;
    private boolean isEnterRandomModel = false;
    private boolean isFirstDraw;
    private boolean breakRecordScor;
    private boolean breakRecordSpeed;
    private static int recordScor;
    private static int recordTime = 9999;
    private static int recordLine;
    private static int fastestScor;
    private static int fastestTime = 1;
    private static int fastestLine;
    private static long whenBreakR;
    private static final int ROWS = 20;// 行数
    private static final int COLS = 10;// 列数
    private static final int CELL_SIZE = 26;
    private static final int[] SPEED = {700, 500, 300, 100};
    private static final int FONT_SIZE1 = 0x10;
    private static final int FONT_SIZE = 0x20;
    private static ArrayList<Tetromino> tetrominos;


    private static void get_record() {
        try {

            System.out.println(readToString("Tetris/basic_data.json"));

//                recordScor = raf.readInt();
//                recordTime = raf.readInt();
//                recordLine = raf.readInt();
//                whenBreakR = raf.readLong();
//                fastestScor = raf.readInt();
//                fastestTime = raf.readInt();
//                fastestLine = raf.readInt();

            background_image = ImageIO.read(Tetris.class.getResource("resources/Tetris.png"));
            game_pause = ImageIO.read(Tetris.class.getResource("resources/game_pause.png"));
            game_over = ImageIO.read(Tetris.class.getResource("resources/game_over.png"));
            T = ImageIO.read(Tetris.class.getResource("resources/T.png"));
            I = ImageIO.read(Tetris.class.getResource("resources/I.png"));
            O = ImageIO.read(Tetris.class.getResource("resources/O.png"));
            L = ImageIO.read(Tetris.class.getResource("resources/L.png"));
            J = ImageIO.read(Tetris.class.getResource("resources/J.png"));
            Z = ImageIO.read(Tetris.class.getResource("resources/Z.png"));
            S = ImageIO.read(Tetris.class.getResource("resources/S.png"));
            Q = ImageIO.read(Tetris.class.getResource("resources/Q.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        if (isUsedSA) {
            g.drawImage(background_image, 0, 0, null);
            g.translate(15, 15);
            paintTetromino(g);// 画正在下落的方块
            paintWall(g);
            paintNextOne(g);
            paintScore(g);
        } else {
            g.drawImage(background_image, 0, 0, null);
            g.translate(15, 15);
            paintModel(g);
            paintScore(g);
        }
    }

    private static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long file_length = file.length();
        byte[] file_content = new byte[file_length.intValue()];
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    JOptionPane.showConfirmDialog(null, "没有写入权限。");
                } else {
                    System.out.println("已创建basic_data文件。");
                }
            } else {
                FileInputStream in = new FileInputStream(file);
                System.out.println(in.read(file_content));
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(file_content, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    private void paintScore(Graphics g) {
        Font f = getFont();
        Font font = new Font(f.getName(), Font.BOLD, FONT_SIZE);
        Font font1 = new Font(f.getName(), Font.BOLD, FONT_SIZE1);
        int x = 290;
        int y = 162;
        g.setColor(new Color(0x667799));
        g.setFont(font);
        String str = "SCORE:" + this.score;
        g.drawString(str, x, y);
        g.setFont(font1);
        y += 38;
        int speed1;
        switch (speed) {
            case 700:
                speed1 = 1;
                break;
            case 500:
                speed1 = 2;
                break;
            case 300:
                speed1 = 3;
                break;
            case 100:
                speed1 = 4;
                break;
            default:
                speed1 = 1;

        }
//        if (isUsedSA) {
//            if (pause) {
//                periodTime += time;
//                isFirstDraw = true;// }
//                str = "消行:" + this.lines + " 速度:" + speed1 + " 时间:"
//                        + (periodTime);
//                g.drawString(str, x, y);
//            } else if (isFirstDraw) {
//                start = System.currentTimeMillis();
//                time = 0;
//                str = "消行:" + this.lines + " 速度:" + speed1 + " 时间:"
//                        + (periodTime + time);
//                g.drawString(str, x, y);
//                isFirstDraw = false;
//            } else {
//                long end = System.currentTimeMillis();
//                time = (end - start) / 1000;
//                str = "消行:" + this.lines + " 速度:" + speed1 + " 时间:"
//                        + (periodTime + time);
//                g.drawString(str, x, y);
//            }
//        } else {
//            str = "消行:" + this.lines + " 速度:" + speed1 + " 时间:" + 0;
//            g.drawString(str, x, y);
//        }
        y += 26;
//        str = "转换速度所需分数:" + ((this.score / 200 + 1) * 200 - this.score);
//        g.drawString(str, x, y);
//        g.setFont(new Font(f.getName(), Font.BOLD, FONT_SIZE));
        y += 48;
        str = "[P]ause";
        if (pause) {
            g.drawImage(game_pause, 0, 0, null);
            g.translate(15, 15);
            str = "[C]ontinue";
        } else if (gameOver) {
            g.drawImage(game_over, 0, 0, null);
            g.translate(15, 15);
            str = "[S]tart!";
        }
        g.drawString(str, x, y);
        g.setFont(font1);
//        y += 36;
//        x -= 10;
//        str = "高分:";
//        g.drawString(str, x, y);
//        g.setColor(new Color(0xCC0033));
//        x += 40;
//        str = "" + recordScor;
//        g.drawString(str, x, y);
//        g.setColor(new Color(0x667799));
//        x += 35;
//        str = "用时:";
//        g.drawString(str, x, y);
//        g.setColor(new Color(0xCC0033));
//        x += 40;
//        str = "" + recordTime;
//        g.drawString(str, x, y);
//
//        g.setColor(new Color(0x667799));
//        x += 35;
//        str = "行:";
//        g.drawString(str, x, y);
//        g.setColor(new Color(0xCC0033));
//        x += 24;
//        str = "" + recordLine;
//        g.drawString(str, x, y);
//
//        g.setColor(new Color(0x667799));
//        y += 26;
//        x -= 174;
//        str = "高速:";
//        g.drawString(str, x, y);
//        g.setColor(new Color(0xCC0033));
//        x += 43;
//        str = "" + fastestScor + "/" + fastestTime;
//        g.drawString(str, x, y);
//
//        g.setColor(new Color(0x667799));
//        x += 108;
//        str = "行:";
//        g.drawString(str, x, y);
//        g.setColor(new Color(0xCC0033));
//        x += 24;
//        str = "" + fastestLine;
//        g.drawString(str, x, y);
    }

    private void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int x = (cell.getCol() + 10) * CELL_SIZE - 1;
            int y = (cell.getRow() + 1) * CELL_SIZE - 1;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintWall(Graphics g) {
        for (int row = 0; row < wall.length; row++) {
            for (int col = 0; col < wall[row].length; col++) {
                if (wall[row][col] != null) {
                    int x = col * CELL_SIZE - 1;
                    int y = row * CELL_SIZE - 1;
                    g.drawImage(wall[row][col].getImage(), x, y, null);
                }
            }
        }
    }

    private void paintTetromino(Graphics g) {
        g.setColor(new Color(0xDDD8E3));
        Cell[] cells = tetromino.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE - 1;
            int y = cell.getRow() * CELL_SIZE - 1;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    private void paintModel(Graphics g) {
        int x = CELL_SIZE;
        int y = 4 * CELL_SIZE;
        String str;
        str = "请选择模式：";
        g.drawString(str, x, y);
        y += 13;
        g.setColor(new Color(0xCC0033));
        str = "按数字键<1>进入随机模式";
        g.drawString(str, x, y);
        y += 13;
        str = "按数字键<2>进入高分高速挑战模式";
        g.drawString(str, x, y);
    }

    private void clearWall() {
        // 将墙的每一行的每个格子清理为null
        for (int row = 0; row < ROWS; row++) {
            Arrays.fill(wall[row], null);
        }
    }

    private void startAction() {// 初始化
        clearWall();
        isUsedSA = true;
        count_Tetrominos = 0;
        tetromino = tetrominos.get(count_Tetrominos++);
        nextOne = tetrominos.get(count_Tetrominos++);
        lines = 0;
        score = 0;
        speed = SPEED[0];
        time = 0;
        periodTime = 0;
        isFirstDraw = true;
        myBreak = System.currentTimeMillis();
        breakRecordScor = false;
        breakRecordSpeed = false;
        pause = false;
        gameOver = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                softDropAction();
                repaint();
            }
        }, 700, speed);
    }

    private void softDropAction() {
        if (tetrominoCanDrop()) {
            tetromino.softDrop();
        } else {
            tetrominoLandToWall();
            destroyLines();// 破坏满的行
            checkGameOver();
            tetromino = nextOne;
            nextOne = tetrominos.get(count_Tetrominos++);
        }

    }

    private void checkGameOver() {
        if (wall[0][4] == null) {
            return;
        }
        gameOver = true;
        timer.cancel();
        repaint();
    }

    private void destroyLines() {
        boolean isfull;
        int line = 0;
        for (int row = 0; row < wall.length; row++) {
            isfull = true;
            for (int col = 0; col < wall[row].length; col++) {
                if (wall[row][col] == null) {
                    isfull = false;
                    break;
                }
            }
            if (isfull) {
                for (int i = row; i > 0; i--) {
                    System.arraycopy(wall[i - 1], 0, wall[i], 0, COLS);
                }
                Arrays.fill(wall[0], null);
                line++;
            }

        }
        if (line != 0) {
            if (speed == 100) {
                int bonus;
                if (line == 1) {
                    bonus = 39;
                } else if (line == 2) {
                    bonus = 90;
                } else if (line == 3) {
                    bonus = 120;
                } else {
                    bonus = 200;
                }
                this.score += SCORE_TABLE[line] + bonus;
            } else {
                this.score += SCORE_TABLE[line];
            }
            int speed1 = SPEED[(score / 200) % SPEED.length];
            this.lines += line;
            if (speed1 != speed) {
                speed = speed1;
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        softDropAction();
                        repaint();
                    }
                }, 0, speed);
            }
            boolean isLarger = false;
            if (score > recordScor) {
                isLarger = true;
            } else if (score == recordScor) {
                if ((periodTime + time) < recordTime) {
                    isLarger = true;
                } else if ((periodTime + time) == recordTime) {
                    if (lines > recordLine) {
                        isLarger = true;
                    }
                }
            }
            if (isLarger || 1.0 / (time + periodTime) * score > ((1.0 / fastestTime) * fastestScor)) {
                timer.cancel();
                if (isLarger) {
                    breakRecordScor = true;
                    lastRecordScor = recordScor;
                    lastRecordTime = recordTime;
                    lastRecordLine = recordLine;
                    try {
                        RandomAccessFile raf = new RandomAccessFile("record.dat", "rw");
                        raf.writeInt(score);
                        recordScor = score;
                        recordTime = (int) (time + periodTime);
                        raf.writeInt(recordTime);
                        recordLine = lines;
                        raf.writeInt(lines);
                        if (whenBreakR != myBreak) {
                            whenBreakR = myBreak;
                            raf.writeLong(myBreak);
                            if (isEnterRandomModel) {
                                FileOutputStream fos;
                                try {
                                    fos = new FileOutputStream("tetrominosR.obj");
                                    ObjectOutputStream out = new ObjectOutputStream(
                                            fos);
                                    out.writeObject(tetrominoToClass());
                                    out.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        raf.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (1.0 / (time + periodTime) * score > ((1.0 / fastestTime) * fastestScor)) {
                    breakRecordSpeed = true;
                    lastRecordSS = fastestScor;
                    lastRecordST = fastestTime;
                    lastRecordSL = fastestLine;
                    try {
                        fastestScor = score;
                        fastestTime = (int) (time + periodTime);
                        fastestLine = lines;
                        RandomAccessFile raf = new RandomAccessFile(
                                "record.dat", "rw");
                        raf.seek(20);
                        raf.writeInt(fastestScor);
                        raf.writeInt(fastestTime);
                        raf.writeInt(fastestLine);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        softDropAction();
                        repaint();
                    }
                }, 0, speed);
            }
        }
    }

    private static final int[] SCORE_TABLE = {0, 1, 10, 80, 200};

    // 0 1 2 3 4
    private void tetrominoLandToWall() {
        Cell[] cells = tetromino.cells;
        for (Cell cell : cells) {
            wall[cell.getRow()][cell.getCol()] = cell;
        }
    }

    private boolean tetrominoCanDrop() {
        Cell[] cells = tetromino.cells;
        if (tetromino instanceof Q) {
            if (cells[0].getRow() < ROWS - 1) {
                int col = cells[0].getCol();
                for (int i = cells[0].getRow() + 1; i < ROWS; i++) {
                    if (wall[i][col] == null) {
                        return true;
                    }
                }
            }
        } else {
            for (Cell cell : cells) {
                int row = cell.getRow();
                int col = cell.getCol();
                if (row == ROWS - 1 || wall[row + 1][col] != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void action() {
        startAction0();
        KeyAdapter l = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_Q) {
                    System.exit(0);
                }
                if (!isUsedSA) {
                    if (key == KeyEvent.VK_1) {
                        tetrominos = new ArrayList<Tetromino>();
                        for (int i = 0; i < 9999; i++) {
                            tetrominos.add(Tetromino.randomTetromino());
                        }
                        isEnterRandomModel = true;
                        startAction();
                        return;
                    }
                } else {
                    if (gameOver) {
                        if (key == KeyEvent.VK_S) {
                            startAction0();
                        }
                        return;
                    }
                    if (pause) {
                        if (key == KeyEvent.VK_C) {
                            continueAction();
                        }
                        return;
                    }
                    switch (key) {
                        case KeyEvent.VK_RIGHT:
                            moveRightAction();
                            break;
                        case KeyEvent.VK_LEFT:
                            moveLeftAction();
                            break;
                        case KeyEvent.VK_DOWN:
                            softDropAction();
                            break;
                        case KeyEvent.VK_UP:
                            rotateRightAction();
                            break;
                        case KeyEvent.VK_Z:
                            rotateLeftAction();
                            break;
                        case KeyEvent.VK_SPACE:
                            hardDropAction();
                            break;
                        case KeyEvent.VK_P:
                            pauseAction();
                            break;
                    }
                }
                repaint();
            }
        };
        this.requestFocus();
        this.addKeyListener(l);
    }

    private void pauseAction() {
        timer.cancel();
        pause = true;
        repaint();
    }

    private void hardDropAction() {
        while (tetrominoCanDrop()) {
            tetromino.softDrop();
        }
        tetrominoLandToWall();
        destroyLines();
        checkGameOver();
        tetromino = nextOne;
        nextOne = tetrominos.get(count_Tetrominos++);
    }

    private void rotateRightAction() {
        tetromino.rotateRight();
        if (coincide()) {
            tetromino.rotateLeft();
        }
    }

    private void rotateLeftAction() {
        tetromino.rotateLeft();
        if (coincide()) {
            tetromino.rotateRight();
        }
    }

    private void moveLeftAction() {
        tetromino.moveLeft();
        if (coincide()) {
            tetromino.moveRight();
        }
    }

    private void moveRightAction() {
        tetromino.moveRight();
        if (outOfBound() || coincide()) {
            tetromino.moveLeft();
        }
    }

    private void continueAction() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                softDropAction();
                repaint();
            }
        }, 700, speed);
        pause = false;
        repaint();
    }

    private boolean outOfBound() {
        Cell[] cells = tetromino.cells;
        for (Cell cell : cells) {
            if (cell.getCol() < 0 || cell.getCol() > COLS) {
                return true;
            }
        }
        return false;
    }

    private boolean coincide() {
        Cell[] cells = tetromino.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (row < 0 || row >= ROWS || col < 0 || col >= COLS
                    || wall[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    private void startAction0() {
        isUsedSA = false;
        isEnterRandomModel = false;
        if (recordScor == 0) {
            isEnterRandomModel = true;
            tetrominos = new ArrayList<Tetromino>();
            for (int i = 0; i < 9999; i++) {
                tetrominos.add(Tetromino.randomTetromino());
            }
            startAction();
        }
        repaint();
    }

    private ArrayList<Class> tetrominoToClass() {
        return tetrominos.stream().map(tetromino1 -> tetromino1.getClass()).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Tetris tetris = new Tetris();
        frame.add(tetris);
        frame.setSize(525, 550);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        get_record();
        tetris.action();
    }
}
