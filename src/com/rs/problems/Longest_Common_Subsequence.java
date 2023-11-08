package com.rs.problems;

import com.rs.help.U;

import java.util.Arrays;

public class Longest_Common_Subsequence {
    public int solve(String A, String B) {
        int mat[][] = new int[A.length()+1][B.length()+1];
        for (int mat1[]: mat) {
            Arrays.fill(mat1, -1);
        }

        return solve(A, B, mat);
    }

    public int solve(String A, String B, int[][] mat) {

        if ((A == B && A == null) || (A != null ^ B != null) || (A.equals("") || B.equals(""))) {
            return 0;
        }
        if(mat[A.length()][B.length()] != -1){
            return mat[A.length()][B.length()];
        }
        if (A.charAt(A.length() - 1) == B.charAt(B.length() - 1)) {
            mat[A.length()][B.length()] = 1 + solve(A.substring(0, A.length() - 1), B.substring(0, B.length() - 1), mat);
            return mat[A.length()][B.length()];
        }
        mat[A.length()][B.length()] = Math.max(solve(A, B.substring(0, B.length() - 1), mat), solve(A.substring(0, A.length() - 1), B, mat));
        return mat[A.length()][B.length()];
    }

    public int solveTop(String A, String B) throws RuntimeException{
        int mat[][] = new int[A.length()+1][B.length()+1];
        Arrays.fill(mat[0], 0);

        for (int i = 0; i < B.length(); i++) {
            mat[i][0] = 0;
        }

        for(int i = 1; i < A.length(); i++) {
            for(int j = 1; j < B.length(); j++) {
                if(A.charAt(i-1) == B.charAt(j-1)){
                    mat[i][j] = 1 + mat[i-1][j-1];
                }else {
                    mat[i][j] = Math.max(mat[i][j-1], mat[i-1][j]);
                }
            }
        }
        System.out.println(U.toS(mat));
        return mat[A.length()][B.length()];
    }
    
    public static void main(String[] args){ 
        Longest_Common_Subsequence o = new Longest_Common_Subsequence();
        System.out.println(o.solveTop("abc", "abc"));
    }
}
