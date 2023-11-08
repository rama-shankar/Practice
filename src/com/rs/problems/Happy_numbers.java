package com.rs.problems;

import java.util.HashSet;
import java.util.Set;

public class Happy_numbers {
    /**
     * Returns true if the input number is happy.
     *
     * @param n Integer to test.
     * @return True if the input integer is happy.
     */
    static Set<Integer> numbs = new HashSet<Integer>();
    public Boolean isHappy(int n) {
        if (n == 1) return true;
        if (numbs.contains(n)) return false;
        numbs.add(n);
        int num = 0;
        while (n > 0) {
            int t = n % 10;
            num += t * t;
            n /= 10;
        }
        return isHappy(num);
    }

    public static void main(String[] args) {
        Happy_numbers o = new Happy_numbers();
        System.out.println(o.isHappy(9999));
    }

}