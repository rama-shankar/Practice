package com.rs.problems;

public class SubsetsumTD {


    private boolean subset(int[] arr, int w, int n) {
        boolean subset[][] = new boolean[w + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            subset[0][i] = true;
        }
        for (int i = 0; i <= w; i++) {
            subset[w][0] = false;
        }

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= n; j++) {
                subset[i][j] = subset[i][j - 1];
                if (i >= arr[j - 1]){
                    subset[i][j] = subset[i][j] || subset[i - arr[j - 1]][j - 1];
                }
            }
        }

        return subset[w][n];

    }

    public static void main(String[] args) {
        SubsetsumTD o = new SubsetsumTD();
        System.out.println(o.subset(new int[]{2, 5, 4}, 7, 3));//220

    }
}