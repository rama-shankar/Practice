package com.rs.problems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KnapsackZeroOne {

    int[][] mat = new int[4][8];
    private int maxProfit(int[] val, int[] wt, int w, int n) {
        if(w == 0 || n == 0){
            return 0;
        }
        if(wt[n-1] > w){
            mat[n-1][w] = maxProfit(val, wt, w, n-1);
            return mat[n-1][w];
        }
        mat[n-1][w] = Math.max(maxProfit(val, wt, w, n-1), val[n-1] + maxProfit(val, wt, w - wt[n-1], n-1));
        return  mat[n-1][w];
    }

    public static void main(String[] args) {
        KnapsackZeroOne o = new KnapsackZeroOne();
        System.out.println(o.maxProfit(new int[]{-5, 4,6,10, 4}, new int[]{2, 5, 4}, 7, 3));//220

    }
}