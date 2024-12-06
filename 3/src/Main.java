import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину массива: ");
        var n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("Исходный массив: " + Arrays.toString(arr));

        BubbleSort.bubbleSort(arr);

        System.out.println("Отсортированный массив: " + Arrays.toString(arr));

        scanner.close();
    }
}
