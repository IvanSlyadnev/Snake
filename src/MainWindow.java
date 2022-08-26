import java.io.IOException;
import javax.swing.JFrame;

public class MainWindow extends JFrame {
    public MainWindow() {
        this.setTitle("Змейка");
        this.setDefaultCloseOperation(3);
        this.setSize(325, 350);
        this.setLocation(400, 400);
        this.add(new GameField());
        this.setVisible(true);
    }

    public static void main(String[] var0) throws IOException {
        new MainWindow();
        FileWorker var2 = new FileWorker("file.txt");
        int var3 = var2.read();
        System.out.println(var3);
    }
}
