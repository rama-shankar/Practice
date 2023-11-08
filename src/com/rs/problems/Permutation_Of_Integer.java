package com.rs.problems;

import com.rs.help.U;

import java.util.Arrays;
import java.util.List;

public class Permutation_Of_Integer {

    List<Integer> solve(int number) {
        return null;
    }

    public void generate(int size, int[] arr) {
        if (size == 1) {
            System.out.println(Arrays.toString(arr));
        } else {
            generate(size - 1, arr);
            for (int i = 0; i < size - 1; i++) {
                if (size % 2 == 0) {
                    U.swap(arr, i, size - 1);
                } else {
                    U.swap(arr, 0, size - 1);
                }
                generate(size, arr);
            }
        }
    }

    private void swap(int i, int i1) {

    }

    public static void main(String[] args) {
        Permutation_Of_Integer o = new Permutation_Of_Integer();
        o.generate(4, new int[]{1,2,3,4});
    }
}
