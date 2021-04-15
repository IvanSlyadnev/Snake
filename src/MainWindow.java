import javax.swing.*;
import java.io.IOException;

/**
 * Created by infuntis on 15/01/17.
 */
public class MainWindow extends JFrame {

    public MainWindow(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(325,350);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) throws IOException{
        MainWindow mw = new MainWindow();
        FileWorker fileWorker = new FileWorker("file.txt");
        int rec = fileWorker.read();
        System.out.println(rec);

    }
}