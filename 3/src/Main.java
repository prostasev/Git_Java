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

        sortArray(arr);

        System.out.println("Отсортированный массив: " + Arrays.toString(arr));

        scanner.close();
    }

    public static void sortArray(int[] arr) {
        BubbleSort.bubbleSort(arr);
    }
}
class BubbleSort {

    static void bubbleSort(int[] arr) {
        int n;
        n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {

                    var temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
