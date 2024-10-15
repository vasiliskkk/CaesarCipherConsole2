import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// Класс для работы с файлами
public class FileHandler {
    // Метод для чтения файла
    public String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Метод для записи в файл
    public void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }

    // Метод для проверки существования файла
    public boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }
}