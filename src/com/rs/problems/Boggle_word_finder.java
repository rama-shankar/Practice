package com.rs.problems;

import com.rs.help.U;

public class Boggle_word_finder {
    public boolean findWord(char[][] board, String word) {
        if (word == null || word.equals("") || board.length == 0) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if (dfs(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        visited[i][j] = true;

        if (dfs(board, word, i - 1, j, index + 1, visited) ||
                dfs(board, word, i + 1, j, index + 1, visited) ||
                dfs(board, word, i, j - 1, index + 1, visited) ||
                dfs(board, word, i, j + 1, index + 1, visited)) {
            return true;
        }

        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        Boggle_word_finder sol = new Boggle_word_finder();
        U.printM(U.toCharM("A,F,A,J;S,I,V,A;E,R,O,C;C,X,E,K;O,D,F,S;D,E,E,E"));
        System.out.println(sol.findWord(U.toCharM("A,F,A,J;S,I,V,A;E,R,O,C;C,X,E,K;O,D,F,S;D,E,E,E"), "FIRECODE"));
    }
}
