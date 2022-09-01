import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameField extends JPanel implements ActionListener {
    private int speed = 300;
    private final int SIZE = 425;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private final int dots = 3;
    public  FileWorker fileWorker = new FileWorker("file.txt");
    private Snake snake = new Snake(dots, DOT_SIZE, ALL_DOTS);
    private final Apple apple = new Apple(DOT_SIZE);
    private final Image background;
    private boolean stop = false;
    public boolean inGame = true;
    public Timer timer;
    public GameField () {
        background = Toolkit.getDefaultToolkit().createImage("fon.png");
        //setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(new FieldKeyListener());
        initGame();
    }

    public void initGame() {
        timer = new Timer(speed, this);
        timer.start();
        snake = new Snake(dots, DOT_SIZE, ALL_DOTS);
        apple.create(snake);
        loadImages();
    }

    public void loadImages() {
        snake.loadImage("dot.png", "snake.png", "tail.png");
    }

    public void checkApple() {
        if (snake.x[0] == apple.getX() && snake.y[0] == apple.getY()) {
            snake.dots++;
            snake.score++;
            apple.create(snake);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        String info = "Apples " + Integer.toString(snake.score) + "; Lives " + Integer.toString(snake.getLives());
        if (inGame) {
            g.setColor(Color.RED);
            g.drawString(info, 0, 10);
            g.drawImage(apple.getImage(), apple.getX(), apple.getY(), this);
            g.drawImage(snake.getHead(), snake.x[0], snake.y[0], this);
            for (int i =1; i < snake.dots-1; i++) {
                g.drawImage(snake.getImage(), snake.x[i], snake.y[i], this);
            }
            g.drawImage(snake.getTail(), snake.x[snake.dots-1], snake.y[snake.dots-1], this);
        } else {
            String mes = "";
            try{
                int record = fileWorker.read();
                if (record < snake.score) {
                    fileWorker.write(snake.score);
                    mes = "Congretulations!!! You made new record";
                }
            } catch (IOException e) {
                System.out.println("The mistake for file working");
            }
            timer.stop();
            String str = "Game over" + "you result is " + Integer.toString(snake.score);
            g.setColor(Color.BLACK);
            g.drawString(str, 125, SIZE/2);
            g.setColor(Color.RED);
            g.drawString(mes, 50, SIZE/3);

        }
    }

    public int getTime (int score) {
        return speed - score * 5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {//выполняется при тикании таймера
        if (!stop) {
            if (inGame) {
                timer.stop();
                timer = new Timer(getTime(snake.score), this);
                timer.start();
                checkApple();
                //System.out.println(snake.getX()[0] + " - " +  snake.getY()[0] + " : " + apple.getX() + " - " + apple.getY());
                if (snake.checkCollision(320)) {
                    snake.minus_live();
                    if (snake.getLives() == 0) {
                        inGame = false;
                    }
                }
                snake.throughWalls(320);
                snake.move();
            }
            repaint();
        }
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key =e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT && snake.direction != "left") {
                snake.direction = "right";
            }
            if (key == KeyEvent.VK_LEFT && snake.direction != "right") {
                snake.direction = "left";
            }
            if (key == KeyEvent.VK_UP && snake.direction != "down") {
                snake.direction = "up";
            }
            if (key == KeyEvent.VK_DOWN && snake.direction != "up") {
                snake.direction = "down";
            }
            if (key == KeyEvent.VK_SPACE && !inGame) {
                inGame = true;
                initGame();
            }
            else if (key == KeyEvent.VK_SPACE && inGame && !stop){
                stop = true;
            }
            else if (key == KeyEvent.VK_SPACE && inGame && stop){
                stop = false;
            }
            if (key == KeyEvent.VK_S) {
                int []x_ = new int[ALL_DOTS];
                int []y_ = new int[ALL_DOTS];
                int c = 0;
                for (int i = snake.dots; i >=0; i--) {
                    x_[c] = snake.x[i];
                    y_[c] = snake.y[i];
                    c++;
                }
                snake.setCoordinates(x_, y_);
                snake.setDirection();
            }
        }

    }

}
