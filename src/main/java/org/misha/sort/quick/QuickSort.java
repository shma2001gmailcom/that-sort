package org.misha.sort.quick;

import java.util.Arrays;


class QuickSort {
    private static int partition(int array[], int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (array[j] <= pivot) {
                ++i;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void quickSort(int array[], int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static void main(String args[]) {
        int[] data = {7, 7, 2, 1, 0, 9, 6};
        System.out.println("given: " + Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println("sorted: " + Arrays.toString(data));
    }
}

