package com.shminjs.leetcode.medium;

import sun.security.util.ByteArrayLexOrder;

import java.io.ObjectInputValidation;

/**
 * Created by shimin on 2017/7/11.
 * 289
 * Game of Life
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int result = 0;
                result  += (i-1>=0 && j-1>=0) ? (board[i-1][j-1] >= 2 ? 1 : 0) : 0;
                result  += (i-1>=0) ? (board[i-1][j] >=2 ? 1 : 0) : 0;
                result  += (i-1>=0 && j+1<n) ? (board[i-1][j+1] >= 2 ? 1 : 0) : 0;
                result  += (j+1<n) ? board[i][j+1] : 0;
                result  += (i+1 < m && j+1 < n) ? board[i+1][j+1] : 0;
                result  += (i + 1 < m) ? board[i+1][j] : 0;
                result  += (i+1 < m && j-1 >= 0) ? board[i+1][j-1] : 0;
                result  += (j - 1 >= 0) ? (board[i][j-1] >= 2 ? 1 : 0) : 0;
                if (board[i][j] == 1) {
                    if (result < 2) {
                        board[i][j] = 2;
                    } else if (result == 2 || result == 3) {
                        board[i][j] = 3;
                    } else {
                        board[i][j] = 2;
                    }
                } else {
                    if (result == 3) {
                        board[i][j] = 1;
                    } else {
                        board[i][j] = 0;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] >= 2) {
                    board[i][j] = board[i][j] - 2;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{1, 1}, {1, 0}};
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.gameOfLife(board);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
