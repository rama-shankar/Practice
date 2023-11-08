package com.rs.ita;

import com.rs.help.U;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class InsertionSort {

    public void sort(int a[], BiPredicate function) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && function.test(a[j], a[j -1])) {
                U.swap(a, j, j - 1);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        InsertionSort o = new InsertionSort();
        int a[] = new int[]{4, 5, 3, 51, 5, 6, 1};
        BiPredicate<Integer, Integer> inc = (Integer x, Integer y) -> x < y;
        BiPredicate<Integer, Integer> dec = (Integer x, Integer y) -> x > y;
        o.sort(a, inc);
        System.out.println(U.toS(a));
        o.sort(a, dec);
        System.out.println(U.toS(a));
        List<Integer> aa = new LinkedList<>();
        aa.add(3);
        aa.add(2);
        aa.add(1);
        Collections.sort(aa);
    }
}
