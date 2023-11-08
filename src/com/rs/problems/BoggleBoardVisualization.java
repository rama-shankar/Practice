package com.rs.problems;

import javax.swing.*;
import java.awt.*;

public class BoggleBoardVisualization extends JPanel {

    private char[][] board;

    public BoggleBoardVisualization(char[][] board) {
        this.board = board;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 40;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.drawString(String.valueOf(board[i][j]), j * cellSize + 15, i * cellSize + 25);
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H'},
            {'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P'}
        };

        JFrame frame = new JFrame("Boggle Board");
        BoggleBoardVisualization boardPanel = new BoggleBoardVisualization(board);
        frame.add(boardPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
