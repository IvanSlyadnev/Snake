import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple {
    private int x;
    private int y;
    private Image image;
    private int DOT_SIZE;

    public Apple(int DOT_SIZE) {
        this.DOT_SIZE = DOT_SIZE;
    }

    public void loadImage(String image_address) {
        ImageIcon imageIcon = new ImageIcon(image_address);
        this.image = imageIcon.getImage();
    }

    public void create(Snake snake){
        /*int [] y_ = new int[snake.y.length];

        if (snake.score == 21) {
            snake.score = 21;
        }
        int xc = 0;
        int yc = 0;
        int [] kors = new int[20];
        for (int i = 0; i < kors.length; i++) {
            kors[i] = i;
        }

        int [] x_ = new int[snake.x.length];
        for (int i = 0; i < snake.x.length; i++) {
            if (snake.x[i] != 0 && !checkInArray(x_,snake.x[i]/snake.DOT_SIZE)) {
                x_[xc] = snake.x[i]/snake.DOT_SIZE;
                xc++;
            }
            if (snake.y[i] != 0) {
                y_[yc] = snake.y[i]/snake.DOT_SIZE;
                yc++;
            }
        }

        int [] xApple = new int[20];
        int [] yApple = new int[20];

        xc = 0; yc = 0;

        for (int i = 0; i < kors.length; i++) {
            if (!checkInArray(x_, kors[i])) {
               xApple[xc] = kors[i];
               xc++;
            }
            if (!checkInArray(y_, kors[i])) {
                yApple[yc] = kors[i];
                yc++;
            }
        }

        this.x = xApple[new Random().nextInt(xc)]*DOT_SIZE;
        this.y = yApple[new Random().nextInt(yc)]*DOT_SIZE;
*/
        this.x = new Random().nextInt(19) * DOT_SIZE;
        this.y = new Random().nextInt(19) * DOT_SIZE;

    }

    private boolean checkInArray(int[] mass, int value) {
        for (int i = 0; i < mass.length; i++) {
            if (mass[i] == value) return true;
        }
        return false;
    }

    public int getX(){
        return this.x;
    }

    public int getY () {
        return this.y;
    }

    public Image getImage () {
        return this.image;
    }


}
