package dc;

public class inversion_count {

    public static long mergeSortAndCount(int[] arr, int l, int r) {
        long count = 0;
        if (l < r) {
            int mid = (l + r) / 2;

            // Count inversions in left half
            count += mergeSortAndCount(arr, l, mid);

            // Count inversions in right half
            count += mergeSortAndCount(arr, mid + 1, r);

            // Count split inversions
            count += mergeAndCount(arr, l, mid, r);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int l, int m, int r) {
        int[] left = java.util.Arrays.copyOfRange(arr, l, m + 1);
        int[] right = java.util.Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        long swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i); // Elements remaining in left
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];

        return swaps;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4, 1};
        long result = mergeSortAndCount(arr, 0, arr.length - 1);
        System.out.println("Number of inversions: " + result);
    }
}
