package ru.sudo.sudokusolver.util;

import javafx.scene.control.TextField;

public class ArrayUtils {

    public static char[][] fromTextFieldsToChars(TextField[][] tfArr) {
        int rowSize = tfArr.length;
        int colSize = tfArr[0].length;
        char[][] chArr = new char[rowSize][colSize];
        for (int r = 0; r < rowSize; ++r) {
            for (int c = 0; c < colSize; ++c) {
                chArr[r][c] = tfArr[r][c].getText().isEmpty() ? '.' : tfArr[r][c].getText().toCharArray()[0];
            }
        }
        return chArr;
    }

    public static TextField[][] fromCharsToTextFields(char[][] chArr) {
        int rowSize = chArr.length;
        int colSize = chArr[0].length;
        TextField[][] tfArr = new TextField[rowSize][colSize];
        for (int r = 0; r < rowSize; ++r) {
            for (int c = 0; c < colSize; ++c) {
                tfArr[r][c].setText(String.valueOf(chArr[r][c]));
            }
        }
        return tfArr;
    }
    public static void fromCharsToTextFields(char[][] chArr, TextField[][] tfArr) {
        int rowSize = chArr.length;
        int colSize = chArr[0].length;
        for (int r = 0; r < rowSize; ++r) {
            for (int c = 0; c < colSize; ++c) {
                tfArr[r][c].setText(String.valueOf(chArr[r][c]));
            }
        }
    }

    public static void resetTextFields(TextField[][] tfArr) {
        int rowSize = tfArr.length;
        int colSize = tfArr[0].length;
        for (int r = 0; r < rowSize; ++r) {
            for (int c = 0; c < colSize; ++c) {
                tfArr[r][c].setText("");
            }
        }
    }
}
