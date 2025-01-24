import java.util.*;

public class FanoCoding {

    static class Symbol {
        char c;
        int freq;
        String code = "";

        Symbol(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String input = scanner.nextLine();

        // Подсчет частот символов
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Создание списка символов с их частотами
        List<Symbol> symbols = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            symbols.add(new Symbol(entry.getKey(), entry.getValue()));
        }

        // Сортировка символов по убыванию частоты
        symbols.sort((a, b) -> b.freq - a.freq);

        // Построение кодов Шеннона-Фано
        buildCodes(symbols, 0, symbols.size() - 1);

        // Вывод кодов
        System.out.println("Символы и их коды:");
        for (Symbol s : symbols) {
            System.out.println("'" + s.c + "' : " + s.code);
        }

        // Создание словаря символов и их кодов
        Map<Character, String> codeMap = new HashMap<>();
        for (Symbol s : symbols) {
            codeMap.put(s.c, s.code);
        }

        // Кодирование исходного текста
        StringBuilder encodedText = new StringBuilder();
        for (char c : input.toCharArray()) {
            encodedText.append(codeMap.get(c));
        }

        System.out.println("Закодированный текст:");
        System.out.println(encodedText.toString());
    }

    // Рекурсивная функция для построения кодов
    static void buildCodes(List<Symbol> symbols, int start, int end) {
        if (start >= end) {
            return;
        }

        // Найти точку разделения
        int split = findSplit(symbols, start, end);

        // Добавить '0' к левым символам
        for (int i = start; i <= split; i++) {
            symbols.get(i).code += "0";
        }

        // Добавить '1' к правым символам
        for (int i = split + 1; i <= end; i++) {
            symbols.get(i).code += "1";
        }

        // Рекурсивный вызов для левой и правой частей
        buildCodes(symbols, start, split);
        buildCodes(symbols, split + 1, end);
    }

    // Функция для нахождения точки разделения
    static int findSplit(List<Symbol> symbols, int start, int end) {
        int totalFreq = 0;
        for (int i = start; i <= end; i++) {
            totalFreq += symbols.get(i).freq;
        }

        int sum = 0;
        for (int i = start; i <= end - 1; i++) {
            sum += symbols.get(i).freq;
            if (sum >= totalFreq / 2) {
                return i;
            }
        }

        return start;
    }
}
