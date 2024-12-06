import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class BubbleSortTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSingleElementArray() {
        int[] arr = {5};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testUnsortedArray() {
        int[] arr = {3, 1, 4, 2, 5};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testAllElementsEqual() {
        int[] arr = {2, 2, 2, 2, 2};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{2, 2, 2, 2, 2}, arr);
    }

    @Test
    public void testNegativeNumbers() {
        int[] arr = {-1, -3, -2, 0, 1};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{-3, -2, -1, 0, 1}, arr);
    }

    @Test
    public void testMixedNumbers() {
        int[] arr = {3, -1, 4, 1, -2, 0};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{-2, -1, 0, 1, 3, 4}, arr);
    }

    @Test
    public void testLargeArray() {
        int[] arr = {10, 4, 3, 5, 1, 2, 8, 6, 7, 9};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {1, 3, 2, 2, 4, 1};
        BubbleSort.bubbleSort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 2, 3, 4}, arr);
    }
}