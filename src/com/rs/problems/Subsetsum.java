package com.rs.problems;

public class Subsetsum {

    int[][] mat = new int[4][8];
    private boolean subset(int[] arr, int w, int n) {
        if(w > 0 && n == 0){
            return false;
        }
        if(w <= 0 && n >= 0){
            return true;
        }

        if(arr[n-1] > w){
            return subset(arr, w, n-1);
        }

         return subset(arr, w, n-1) ||  subset(arr, w - arr[n-1], n-1);

    }

    public static void main(String[] args) {
        Subsetsum o = new Subsetsum();
        System.out.println(o.subset(new int[]{2, 5, 4}, 7, 3));//220

    }
}