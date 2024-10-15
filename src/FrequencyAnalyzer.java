import java.util.HashMap;
import java.util.Map;

// Класс для работы с частотным анализом
public class FrequencyAnalyzer {
    // Метод для расчета частоты символов
    public Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        return frequency;
    }

    // Метод для нормализации вектора частот
    public double[] normalizeVector(Map<Character, Integer> frequency) {
        double[] vector = new double[frequency.size()];
        int total = frequency.values().stream().mapToInt(Integer::intValue).sum();
        int i = 0;
        for (int count : frequency.values()) {
            vector[i++] = (double) count / total;
        }
        return vector;
    }

    // Метод для расчета расстояния между векторами
    public double calculateDistance(double[] vector1, double[] vector2) {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += Math.pow(vector1[i] - vector2[i], 2);
        }
        return Math.sqrt(sum);
    }
}