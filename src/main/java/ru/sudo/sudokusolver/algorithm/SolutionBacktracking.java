package ru.sudo.sudokusolver.algorithm;

import java.util.Arrays;

public class SolutionBacktracking {
    private static final SolutionBacktracking INSTANCE = new SolutionBacktracking();

    private int ROW;
    private int COL;
    char[][] cloneBoard;
    boolean isFinish = false;

    private SolutionBacktracking() {
        // empty constructor
    }

    public static SolutionBacktracking getInstance() {
        return new SolutionBacktracking();
    }

    public void solveSudoku(char[][] board) {
        ROW = board.length;
        COL = board[0].length;
        cloneBoard = cloneBoard(board);
        backtrack(board, 0);
    }

    private char[][] cloneBoard(char[][] board) {
        char[][] b = new char[ROW][COL];
        for (int i = 0; i < ROW; ++i) {
            b[i] = Arrays.copyOf(board[i], ROW);
        }
        return b;
    }

    private void backtrack(char[][] board, int row) {
        if (row >= ROW) {
            isFinish = true;
            return;
        }
        for (int c = 0; c < COL; ++c) {
            if (c > 0 && board[row][c - 1] == '.') return;
            if (board[row][c] != '.') continue;
            for (int digit = 1; digit < 10; ++digit) {
//                int digit = getValid(board, row, c);
                if (isValidDigit(board, row, c, digit)) {
                    persistDigit(board, row, c, digit);
                    backtrack(board, row);
                    if (!isFinish) {
                        remove(board, row, c);
                    }
                }
            }
        }
        if (board[row][COL - 1] != '.') {
            backtrack(board, row + 1);
        }
    }

    private void remove(char[][] board, int row, int col) {
        if (cloneBoard[row][col] == '.') {
            board[row][col] = '.';
        }
    }

    private void persistDigit(char[][] board, int row, int col, int digit) {
        board[row][col] = Character.forDigit(digit, 10);
    }

    private boolean isValidDigit(char[][] board, int row, int col, int digit) {
        return board[row][col] == '.' && !numPresentInLargeSquare(digit, row, col, board) && !numPresentInSmallSquare(digit, row, col, board);
    }

    private boolean numPresentInLargeSquare(int digit, int row, int col, char[][] board) {
        for (int c = 0; c < COL; ++c) {
            if (Character.digit(board[row][c], 10) == digit) {
                return true;
            }
        }
        for (int r = 0; r < ROW; ++r) {
            if (Character.digit(board[r][col], 10) == digit) {
                return true;
            }
        }
        return false;
    }

    private boolean numPresentInSmallSquare(int digit, int row, int col, char[][] board) {
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);
        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 3; ++c) {
                if (Character.digit(board[startRow + r][startCol + c], 10) == digit) {
                    return true;
                }
            }
        }
        return false;
    }
}
