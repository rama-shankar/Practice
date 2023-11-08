package com.rs.problems;

import com.rs.help.U;

public class Number_of_islands {
    /**
     * Returns the number of islands present in the
     * input grid.
     *
     * @param grid Input grid.
     * @return Number of islands.
     */
    //1. find 1. increase counter. make 0 until you find one in all direction
    public int countIslands(int[][] grid) {
        int count = 0;


        int rn = grid.length;
        int cn = grid[0].length;

        int memo[][] = new int [rn][cn];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int a[] = search(grid, memo);
                if(a.length > 0){
                    count++;
                    filMat(a, grid, memo, rn, cn);
                }
            }
        }

        return count;
    }

    void filMat(int[] a, int [][] grid, int [][] memo, int rn, int cn){
        int crn = a[0];
        int ccn = a[1];

        if(crn >= rn || ccn >= cn || crn < 0 || ccn < 0){
            return;
        }
        if(memo[crn][ccn] == 1 || grid[crn][ccn] == 0){
            return;
        }
        memo[crn][ccn] = 1;

        filMat(new int[]{crn, ccn+1}, grid, memo, rn, cn);
        filMat(new int[]{crn + 1, ccn}, grid, memo, rn, cn);
        filMat(new int[]{crn , ccn - 1 }, grid, memo, rn, cn);
    }

    int[] search(int [][] grid, int [][] mem){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(mem[i][j] == 0 && grid[i][j] == 1){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        Number_of_islands sol = new Number_of_islands();
        U.printM(U.toM("1,1,1;0,1,0;1,1,1"));
        System.out.println(sol.countIslands(U.toM("1,1,1;0,1,0;1,1,1")));
    }
}
