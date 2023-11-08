package com.rs.problems;

import java.util.Arrays;

public class Count_paths_on_a_board {
    /**
     * Returns the number of ways to traverse a board of size m x n
     * from the top left cell to its bottom right cell, using down
     * and right directions of movement.
     *
     * @param m Number of rows of cells on the board.
     * @param n Number of columns of cells on the board.
     * @return Number of paths from the top left to bottom right.
     */

    public long countPaths(int m, int n) {
        long[][] mat = new long[1000][1000];
        for (long[] row : mat) {
            Arrays.fill(row, -1);
        }
        return countPaths(m, n, mat);
    }

    public long countPaths(int m, int n, long[][] mat) {
        if (m < 1 || n < 1) {
            return 0;
        }
        if (m == n && m == 1) {
            return 1;
        }
        if (mat[m][n] != -1) {
            return mat[m][n];
        }
        long up = countPaths(m - 1, n, mat);
        long left = countPaths(m, n - 1, mat);
        mat[m][n] = up + left;
        return mat[m][n];
    }
    
    public static void main(String[] args){ 
        Count_paths_on_a_board o = new Count_paths_on_a_board();
        System.out.println(o.countPaths(2,2));
    }
}