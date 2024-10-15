import java.io.IOException;
import java.util.Map;

// Основной класс для шифрования и дешифрования
public class CaesarCipher {
    private final FileHandler fileHandler;
    private final Alphabet alphabet;
    private final FrequencyAnalyzer frequencyAnalyzer;

    // Конструктор
    public CaesarCipher() {
        this.fileHandler = new FileHandler();
        this.alphabet = new Alphabet();
        this.frequencyAnalyzer = new FrequencyAnalyzer();
    }

    // Метод для шифрования текста
    public void encrypt(String inputFile, String outputFile, int key) throws IOException {
        validateInput(inputFile, outputFile, key);
        String content = fileHandler.readFile(inputFile);
        StringBuilder encryptedContent = new StringBuilder();
        for (char c : content.toCharArray()) {
            encryptedContent.append(alphabet.shiftCharacter(c, key));
        }
        fileHandler.writeFile(outputFile, encryptedContent.toString());
    }

    // Метод для расшифровки текста
    public void decrypt(String inputFile, String outputFile, int key) throws IOException {
        validateInput(inputFile, outputFile, key);
        String content = fileHandler.readFile(inputFile);
        StringBuilder decryptedContent = new StringBuilder();
        for (char c : content.toCharArray()) {
            decryptedContent.append(alphabet.shiftCharacter(c, -key));
        }
        fileHandler.writeFile(outputFile, decryptedContent.toString());
    }

    // Метод для brute force расшифровки
    public void bruteForce(String inputFile, String outputFile, String optionalSampleFile) throws IOException {
        String content = fileHandler.readFile(inputFile);
        String sampleContent = optionalSampleFile != null ? fileHandler.readFile(optionalSampleFile) : null;

        for (int key = 0; key < alphabet.length(); key++) {
            StringBuilder decryptedContent = new StringBuilder();
            for (char c : content.toCharArray()) {
                decryptedContent.append(alphabet.shiftCharacter(c, -key));
            }
            if (sampleContent != null && decryptedContent.toString().contains(sampleContent)) {
                fileHandler.writeFile(outputFile, decryptedContent.toString());
                return;
            }
        }

        fileHandler.writeFile(outputFile, "Brute force не смог найти правильный ключ.");
    }

    // Метод для статистического анализа расшифровки
    public void statisticalAnalysis(String inputFile, String outputFile, String optionalSampleFile) throws IOException {
        String content = fileHandler.readFile(inputFile);
        String sampleContent = optionalSampleFile != null ? fileHandler.readFile(optionalSampleFile) : null;

        if (sampleContent == null) {
            throw new IllegalArgumentException("Файл с образцом необходим для статистического анализа.");
        }

        Map<Character, Integer> sampleFrequency = frequencyAnalyzer.calculateFrequency(sampleContent);
        double[] sampleVector = frequencyAnalyzer.normalizeVector(sampleFrequency);

        double minDistance = Double.MAX_VALUE;
        int bestKey = 0;

        for (int key = 0; key < alphabet.length(); key++) {
            StringBuilder decryptedContent = new StringBuilder();

            for (char c : content.toCharArray()) {
                decryptedContent.append(alphabet.shiftCharacter(c, -key));
            }

            Map<Character, Integer> decryptedFrequency = frequencyAnalyzer.calculateFrequency(decryptedContent.toString());
            double[] decryptedVector = frequencyAnalyzer.normalizeVector(decryptedFrequency);

            double distance = frequencyAnalyzer.calculateDistance(sampleVector, decryptedVector);

            if (distance < minDistance) {
                minDistance = distance;
                bestKey = key;
            }
        }

        decrypt(inputFile, outputFile, bestKey);
    }

    // Метод для валидации входных данных
    private void validateInput(String inputFile, String outputFile, int key) {
        if (!fileHandler.fileExists(inputFile)) {
            throw new IllegalArgumentException("Входной файл не существует: " + inputFile);
        }
        if (key < 0 || key >= alphabet.length()) {
            throw new IllegalArgumentException("Ключ вне диапазона: " + key);
        }
    }
}