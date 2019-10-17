package Snake;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
    public static Color green = new Color(1666073);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0, 0, 800, 700);
        Snake snake = Snake.snake;
        g.setColor(Color.GRAY);
        for (Point point : snake.snakeparts) {//Create an iteration of every object in ArrayList of snakeparts.
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,
                    Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);
        g.setColor(Color.RED);
        g.fillRect(snake.apple.x * Snake.SCALE, snake.apple.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);
        String scoreString = "Score: " + snake.score / 10 + ", " + "Tail Length: " + snake.tailLength +
                ", " + "Time: " + snake.time / 10;
        g.setColor(Color.WHITE);
                g.drawString(scoreString, (int) (getWidth()/2 - scoreString.length() * 2.5f), 10 );
    }
}
