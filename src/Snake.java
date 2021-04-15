import javax.swing.*;
import java.awt.*;

public class Snake {
    public int score = 0;
    public int [] x;
    public int [] y;
    public int DOT_SIZE;
    public int dots;
    private Image image;
    private Image head;
    private Image tail;
    public String direction;

    public Snake(int dots, int DOTS_SIZE, int ALL_DOTS) {
        this.direction = "right";
        x = new int[ALL_DOTS];
        y = new int[ALL_DOTS];
        this.DOT_SIZE = DOTS_SIZE;
        this.dots = dots;

        for (int i = 0; i < dots; i++) {
            this.x[i] = 48-i*DOTS_SIZE;
            this.y[i] = 48;
        }
    }

    public void move() {

        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case "right" :
                x[0] +=DOT_SIZE;
                break;
            case "left" :
                x[0] -=DOT_SIZE;
                break;
            case "up" :
                y[0] -=DOT_SIZE;
                break;
            case "down" :
                y[0] +=DOT_SIZE;
                break;
            default:
                break;
        }
    }

    public boolean checkCollision(int SIZE) {
        for (int i = 1; i < dots; i++) {
            if (dots >= 4 && x[0] == x[i] && y[0] == y[i]) return true;
        }

        if (x[0] < 0 || x[0] >SIZE-5) return true;
        if (y[0] < 0 || y[0] >SIZE) return true;
        return false;
    }

    public void loadImage(String image_address, String head_address,String tail_address) {
        ImageIcon imageIcon = new ImageIcon(image_address);
        this.image = imageIcon.getImage();
        ImageIcon imageHead = new ImageIcon(head_address);
        this.head = imageHead.getImage();
        ImageIcon imageTail = new ImageIcon(tail_address);
        this.tail = imageTail.getImage();

    }

    public Image getImage () {
        return this.image;
    }
    public Image getHead() {
        return head;
    }
    public Image getTail () {
        return tail;
    }

}
