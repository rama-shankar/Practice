package com.rs.problems;

import com.rs.help.StopWatch;

import java.util.HashSet;
import java.util.Set;

public class HappyNumbers {

    static int sw[] = new int[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81};
    static int CH[] = new int[100000001];
    static Set<Integer> ch = new HashSet<>();

    public static int isHappyNumber(int num) {
        int rem = 0, sum = 0;
        while (num > 0) {
            rem = num % 10;
            sum = sum + sw[rem];
            num = num / 10;
        }
        return sum;
    }
    private static int nextB(int num) {
        int rem = 0, sum = 0;
        while (num > 0) {
            rem = num % 0;
            sum = sum + (rem * rem);
            num = num / 10;
        }
        return sum;
    }
    static long solve(int l, int r) {
        int ch[] = new int[1000001];
        long sum = 0;
        for (int i = l; i <= r; i++) {
            int rs = i;
            while (rs != 1 && rs != 4) {
                if (ch[rs] == 1) {
                    rs = 1;
                }
                rs = isHappyNumber(rs);
            }
            if (rs == 1) {
                sum += i;
                ch[i] = 1;
                ch[isHappyNumber(i)] = 1;
            }

        }

        return sum;
    }



    public static void main(String[] args) {


        StopWatch s = new StopWatch();

        System.out.println(solve(1, 1000000));
        s.stop();
    }
}  