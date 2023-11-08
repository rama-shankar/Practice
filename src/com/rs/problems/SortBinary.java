package com.rs.problems;

import com.rs.help.U;

public class SortBinary {
    
    public int minSort2(int[] A){
        if(A.length < 3) return 0;
        int p = 0;
        int move = 0;
        for (int i = 1; i < A.length; i++) {
            int j = i;
            while (j > 0 && A[j] <= p && A[j-1] > p){
                U.swap(A, j , j-1);
                move++;
                j--;
            }
        }
        System.out.println(U.toS(A));
        return move;
    }

    public int minSort(int[] A){
        if(A.length < 3) return 0;
        int p = 0;
        int move = 0;
        for (int i =  A.length - 2; i >=0; i--) {
            int j = i;
            while (j < A.length && A[j] <= p && A[j+1] > p){
                U.swap(A, j , j+1);
                move++;
                j++;
            }
        }
        System.out.println(U.toS(A));
        return move;
    }
    
    public static void main(String[] args){ 
        SortBinary o = new SortBinary();
        System.out.println(o.minSort(new int[]{1,1,1,0,1,0}));
    }
}
