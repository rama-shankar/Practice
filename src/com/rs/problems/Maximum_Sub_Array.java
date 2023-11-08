package com.rs.problems;

public class Maximum_Sub_Array {

    public int maxValue(int[] a, int n) {
        if (n == 0) {
            return a[n];
        }
        return Math.max(maxValue(a, n - 1), a[n] + maxValue(a, n - 1));
    }

    public static void main(String[] args) {
        Maximum_Sub_Array o = new Maximum_Sub_Array();
        int [] in = new int[]{-5, 4, 6, 10, 4, -1};
        System.out.println(o.maxValue(in, 5));
    }

}
