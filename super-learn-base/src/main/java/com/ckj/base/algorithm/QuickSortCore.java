package com.ckj.base.algorithm;

/**
 * @author chris.c
 * @Description
 * @Date 2021/6/21
 * @Time 4:15 PM
 **/
public class QuickSortCore {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partion = partion(arr, low, high);
            quickSort(arr, low, partion - 1);
            quickSort(arr, partion + 1, high);
        }
    }

    public static int partion(int[] arr, int low, int high) {
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < arr[high]) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, high, i + 1);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {6, 3, 5, 4, 8, 1, 7};
        QuickSortCore.quickSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.print(j + ",");
        }
        System.out.println();
    }

}
