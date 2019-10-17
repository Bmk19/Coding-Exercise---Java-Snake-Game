package Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {
    public JFrame jframe;

    public static Snake snake;

    public RenderPanel renderPanel;
    //Create a timer to call renderPanel to repaint every tick.
    public Timer timer = new Timer(20, this);

    public ArrayList<Point> snakeparts = new ArrayList<Point>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score, tailLength = 10, time;

    public Point head, apple;

    public Random random;

    public boolean over = false, paused;

    public Dimension dim;

    //Create a constructor for this class.
    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 700);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getWidth() / 2); //Centers the panel in the screen(1980x1080)
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        StartGame();
    }

    public void StartGame()
    {
        over = false;
        paused = false;
        score = 0;
        time = 0;
        tailLength = 5;
        direction = DOWN;
        head = new Point(0, -1);
        random = new Random();
        snakeparts.clear();
        apple = new Point(random.nextInt(65), random.nextInt(68));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        renderPanel.repaint();
        ticks++;

        if (ticks % 5 == 0 && head != null && !over && !paused) {
            time++;
            snakeparts.add(new Point(head.x, head.y));
            System.out.println(head.x + ", " + head.y); //Unnecessary line of code.
            // Just used to track the head locaiton.
            //Add directions to the snake movement.

            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                }
            }
            if (direction == DOWN) {
                if (head.y + 1 <= 65 && noTailAt(head.x, head.y + 1)) {
                    head = (new Point(head.x, head.y + 1));
                } else {
                    over = true;
                }
            }
            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }
            if (direction == RIGHT) {
                if (head.x + 1 <= 68 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }
            if (snakeparts.size() > tailLength) {
                snakeparts.remove(0);
            }
            if (apple != null) //Check if the head has collided with the cherry
            {
                if (head.equals(apple)) {
                    score += 10;
                    tailLength++;
                    apple.setLocation(random.nextInt(65), random.nextInt(68));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeparts){
            if(point.equals(new Point(x, y))){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i == KeyEvent.VK_DOWN && direction != UP)
            direction = DOWN;
        if(i == KeyEvent.VK_UP && direction != DOWN)
            direction = UP;
        if(i == KeyEvent.VK_LEFT && direction != RIGHT)
            direction = LEFT;
        if(i == KeyEvent.VK_RIGHT && direction != LEFT)
        direction = RIGHT;
        if(i == KeyEvent.VK_SPACE) {
            if(over)
                StartGame();
            else
                paused = !paused;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
