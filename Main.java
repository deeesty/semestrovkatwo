import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        int[] arr = new int[random.nextInt(9000) + 1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(99000) + 100;
        }

        AVLTree<Integer> tree = new AVLTree<>();

        long[] addTimes = new long[arr.length];
        int[] addCounts = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];

            long startTime = System.nanoTime();
            tree.add(value);
            long endTime = System.nanoTime();

            addTimes[i] = endTime - startTime;
            addCounts[i] = 1;
        }

        long[] searchTimes = new long[100];
        int[] searchCounts = new int[100];
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(arr.length);
            int value = arr[index];

            long startTime = System.nanoTime();
            tree.contains(value);
            long endTime = System.nanoTime();

            searchTimes[i] = endTime - startTime;
            searchCounts[i] = 1;
        }

        long[] removeTimes = new long[1000];
        int[] removeCounts = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(arr.length);
            int value = arr[index];

            long startTime = System.nanoTime();
            tree.remove(value);
            long endTime = System.nanoTime();

            removeTimes[i] = endTime - startTime;
            removeCounts[i] = 1;
        }

        long avgAddTime = calculateAverages(addTimes);
        int avgAddCount = calculateAverage(addCounts);
        long avgSearchTime = calculateAverages(searchTimes);
        int avgSearchCount = calculateAverage(searchCounts);
        long avgRemoveTime = calculateAverages(removeTimes);
        int avgRemoveCount = calculateAverage(removeCounts);

        System.out.println("Среднее время добавления: " + avgAddTime + " нс");
        System.out.println("Среднее количество операций добавления: " + avgAddCount);
        System.out.println("Среднее время поиска: " + avgSearchTime + " нс");
        System.out.println("Среднее количество операций поиска: " + avgSearchCount);
        System.out.println("Среднее время удаления: " + avgRemoveTime + " нс");
        System.out.println("Среднее количество операций удаления: " + avgRemoveCount);
        System.out.println(arr.length);
    }

    private static long calculateAverages(long[] times) {
        long sum = 0;
        for (long time : times) {
            sum += time;
        }
        return sum / times.length;
    }

    private static int calculateAverage(int[] counts) {
        int sum = 0;
        for (int count : counts) {
            sum += count;
        }
        return sum / counts.length;
    }
}
