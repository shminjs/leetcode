package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/30.
 * 79
 */
public class Exist {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        boolean flag = false;
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (checkWord(board, i, j, word, 0)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }
        return flag;
    }

    private boolean checkWord(char[][] board, int i, int j, String word, int ind) {
        if (!visited[i][j] && ind < word.length() && board[i][j] == word.charAt(ind)) {
            if (ind + 1 == word.length()) return true;
            visited[i][j] = true;
            boolean result = (i-1>=0 && checkWord(board, i-1, j, word, ind+1)) || (j-1>=0 && checkWord(board, i, j-1, word, ind+1)) || (i+1 < board.length && checkWord(board, i+1, j, word, ind+1)) || (j+1 < board[0].length && checkWord(board, i, j+1, word, ind+1));
            if (!result) visited[i][j] = false;
            return result;
        } else {
            return false;
        }
    }
}
