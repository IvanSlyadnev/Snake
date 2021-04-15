import java.io.*;
import java.util.Scanner;

public class FileWorker extends IOException{
    private String name;

    public FileWorker (String name) {
        this.name = name;
    }

    public void write (int score) throws IOException{
        FileWriter writer = new FileWriter(this.name);
        writer.write(Integer.toString(score));
        writer.close();
    }

    public int read () throws IOException {
        FileReader reader = new FileReader(this.name);
        Scanner scn = new Scanner(reader);
        return Integer.parseInt(scn.nextLine());
    }
}
