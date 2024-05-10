package ru.sudo.sudokusolver.algorithm;

import org.junit.jupiter.api.Test;

class SolutionBacktrackingTest {

    @Test
    void solveSudoku() {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        SolutionBacktracking s = SolutionBacktracking.getInstance();
        s.solveSudoku(board);
    }
    @Test
    void solveSudoku1() {
        char[][] board = {{'5', '3', '.'}, {'6', '.', '.'}, {'.', '9', '8'}};
        SolutionBacktracking s = SolutionBacktracking.getInstance();
        s.solveSudoku(board);
    }
}