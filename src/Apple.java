import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple {
    private int x;
    private int y;
    private Image image;
    private int DOT_SIZE;

    private int [][] cors_ = new int[400][2];

    private String [] images = {"apple.png"};

    public Apple(int DOT_SIZE) {
        this.DOT_SIZE = DOT_SIZE;
    }

    public void loadImage(String image_address) {
        ImageIcon imageIcon = new ImageIcon(image_address);
        this.image = imageIcon.getImage();
    }

    public void create(Snake snake){
        int c = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.cors_[c][0] = j;
                this.cors_[c][1] = i;
                c++;
            }
        }

        int [][] cors = new int[snake.dots][2];
        for(int i =0; i < snake.dots; i++) {
            cors[i][0] = snake.getX()[i]/DOT_SIZE;
            cors[i][1] = snake.getY()[i]/DOT_SIZE;
        }
        int [][] free_cors = this.getFreeCoordinates(cors);
        int [] random_cor = free_cors[new Random().nextInt(free_cors.length)];

        this.x = random_cor[0] * DOT_SIZE;
        this.y = random_cor[1] * DOT_SIZE;

        this.loadImage(this.images[new Random().nextInt(this.images.length)]);
    }

    private boolean checkInArray(int[] mass, int [][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == mass[0] && array[i][1] == mass[1]) return true;
        }
        return false;
    }

    private int [][] getFreeCoordinates(int [][] cors) {
        int [][] free_cors = new int[400-cors.length][2];
        int counter = 0;
        for (int [] cor : this.cors_) {
            if (!this.checkInArray(cor, cors)) {
                free_cors[counter] = cor;
                counter++;
            }
        }
        return free_cors;
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
