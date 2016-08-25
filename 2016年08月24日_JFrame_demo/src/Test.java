import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        test1();
    }

    private static void test1() {
        JFrame frame = new JFrame("Tetris");
        MyPanel panel = new MyPanel();
        frame.add(panel);
        frame.setBounds(0, 0, 525, 570);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class MyPanel extends JPanel {
        public void paint(Graphics g) {
            System.out.println("面板");
            g.drawString("您好", 100, 100);//绘制字符串
            g.drawLine(0, 0, 100, 100);//绘制线条
            Image image = null;
            try {
                image = ImageIO.read(
                    Test.class.getResource("TETRIS.png")
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image, 0, 0, null);
        }
    }


}
