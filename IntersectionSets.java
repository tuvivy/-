import java.util.*;

public class IntersectionSets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество множеств:");
        int n = scanner.nextInt();

        List<Set<Integer>> listOfSets = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Введите количество элементов в множестве " + (i + 1) + ":");
            int size = scanner.nextInt();

            Set<Integer> set = new HashSet<>();
            System.out.println("Введите элементы множества " + (i + 1) + ":");

            for (int j = 0; j < size; j++) {
                int element = scanner.nextInt();
                set.add(element);
            }

            listOfSets.add(set);
        }

        if (listOfSets.isEmpty()) {
            System.out.println("Нет множеств для пересечения.");
            return;
        }

        Set<Integer> intersection = new HashSet<>(listOfSets.get(0));

        for (int i = 1; i < listOfSets.size(); i++) {
            intersection.retainAll(listOfSets.get(i));
        }

        System.out.println("Пересечение заданных множеств:");
        System.out.println(intersection);
    }
}
