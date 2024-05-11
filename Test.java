package Test_8;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree();
        // Генерация массива из случайной последовательности 10000 целых чисел
        int[] array = new int[10000];
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            array[i] = random.nextInt();
        }

        // Добавление элементов в структуру с измерением времени и количества итераций
        for (int i = 0; i < 10000; i++) {
            long startTime = System.nanoTime();
            tree.insert(array[i]);
            long endTime = System.nanoTime();
            System.out.println("Добавили " + array[i] + ": Время = " + (endTime - startTime) + " ns, Итераций = " + tree.getTotalInsertIterations());
        }

        int[] searchKeys = new int[100];
        for (int i = 0; i < 100; i++) {
            searchKeys[i] = array[random.nextInt(10000)];
        }

        for (int i = 0; i < 100; i++) {
            long startTime = System.nanoTime();
            tree.search(searchKeys[i]);
            long endTime = System.nanoTime();
            System.out.println("Ищем " + searchKeys[i] + ": Время = " + (endTime - startTime) + " ns, Итераций = " + tree.getTotalSearchIterations());
        }
        int[] deleteKeys = new int[1000];
        for (int i = 0; i < 1000; i++) {
            deleteKeys[i] = array[random.nextInt(10000)];
        }
        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();
            tree.delete(deleteKeys[i]);
            long endTime = System.nanoTime();
            System.out.println("Удаляем " + deleteKeys[i] + ": Время = " + (endTime - startTime) + " ns, Итераций = " + tree.getTotalDeleteIterations());
        }


        System.out.println("Среднее время вставки: " + tree.getAverageInsertTime() + " ns");
        System.out.println("Среднее время поиска: " + tree.getAverageSearchTime() + " ns");
        System.out.println("Среднее время удаления: " + tree.getAverageDeleteTime() + " ns");
        System.out.println("Среднее количество итераций вставки: " + tree.getAverageInsertIterations());
        System.out.println("Среднее количество итераций поиска: " + tree.getAverageSearchIterations());
        System.out.println("Среднее количество итераций удаления: " + tree.getAverageDeleteIterations());
    }
}

