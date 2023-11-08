package com.rs.problems;

import java.util.Arrays;

public class AvgOfKInArray {
    /**
     * Returns the average of all contiguous sub-arrays
     * of size k in the given integer array, starting with
     * the first element.
     *
     * @param arr Integer array.
     * @param k   Number of contiguous elements in the sub-array.
     * @return Array of averages.
     */
    public double[] averages(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return null;
        }

        double[] avgs = new double[arr.length - k + 1];
        double sum = 0D;
        int t = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (i >= k - 1) {
                avgs[t] = sum / k;
                sum -= arr[t++];
            }

        }

        return avgs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new AvgOfKInArray().averages(new int[]{1,3,-2,6,4}, 4)));
    }
}