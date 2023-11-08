package com.rs.problems;

public class CountSetBit {
    int count(int n) {
        int count  = 0;
        while (n > 0) {
            count += n & 1;
            n = n >> 1;
        }

        return count;
    }

    public static void main(String[] args) {
        CountSetBit o = new CountSetBit();
        System.out.println(o.count(6));//110
    }
}
