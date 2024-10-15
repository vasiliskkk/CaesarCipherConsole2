import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CaesarCipher caesarCipher = new CaesarCipher();

        System.out.println("Выберите операцию:");
        System.out.println("1. Шифрование");
        System.out.println("2. Расшифровка");
        System.out.println("3. Brute force расшифровка");
        System.out.println("4. Статистический анализ расшифровки");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Считываем оставшийся перевод строки

        System.out.println("Введите путь к входному файлу:");
        String inputFile = scanner.nextLine();

        System.out.println("Введите путь к выходному файлу:");
        String outputFile = scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    System.out.println("Введите ключ для шифрования:");
                    int encryptKey = scanner.nextInt();
                    caesarCipher.encrypt(inputFile, outputFile, encryptKey);
                    System.out.println("Текст зашифрован и сохранен в " + outputFile);
                    break;
                case 2:
                    System.out.println("Введите ключ для расшифровки:");
                    int decryptKey = scanner.nextInt();
                    caesarCipher.decrypt(inputFile, outputFile, decryptKey);
                    System.out.println("Текст расшифрован и сохранен в " + outputFile);
                    break;
                case 3:
                    System.out.println("Введите путь к файлу с образцом (опционально):");
                    String sampleFileBruteForce = scanner.nextLine();
                    caesarCipher.bruteForce(inputFile, outputFile, sampleFileBruteForce);
                    System.out.println("Текст расшифрован методом brute force и сохранен в " + outputFile);
                    break;
                case 4:
                    System.out.println("Введите путь к файлу с образцом:");
                    String sampleFileStatistical = scanner.nextLine();
                    caesarCipher.statisticalAnalysis(inputFile, outputFile, sampleFileStatistical);
                    System.out.println("Текст расшифрован методом статистического анализа и сохранен в " + outputFile);
                    break;
                default:
                    System.out.println("Неверный выбор операции.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}