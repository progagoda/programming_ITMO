import java.io.FileInputStream;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
            FileInputStream stream = new FileInputStream("E:/файлы итмо лабы/программирование/лаба 5/src/afterStudyGroup.json.txt");
            int length= stream.available();
            byte [] data= new byte [length];
            stream.read(data);
            String  text = new String (data);
        System.out.println(text);
    }
}
