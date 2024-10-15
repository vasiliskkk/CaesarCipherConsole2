import java.util.Arrays;
// Класс для работы с алфавитом
public class Alphabet {
    // Алфавит
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    // Метод для сдвига символа
    public char shiftCharacter(char c, int shift) {
        int index = Arrays.binarySearch(ALPHABET, c);
        if (index < 0) return c; // Если символа нет в алфавите, он возвращается без изменений
        return ALPHABET[(index + shift + ALPHABET.length) % ALPHABET.length];
    }

    // Метод для получения длины алфавита
    public int length() {
        return ALPHABET.length;
    }
}