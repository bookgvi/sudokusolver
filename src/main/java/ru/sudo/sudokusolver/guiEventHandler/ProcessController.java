package ru.sudo.sudokusolver.guiEventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.sudo.sudokusolver.algorithm.SolutionBacktracking;
import ru.sudo.sudokusolver.util.ArrayUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public class ProcessController {

    private ProcessController() {
        // empty constructor
    }
    public static GridPane createButtonsPane(TextField[][] tfArr) {
        GridPane buttonsPane = new GridPane();
        Button solveButton = createProceedButton(tfArr);
        Button resetButton = createResetButton(tfArr);
        GridPane.setColumnIndex(solveButton, 0);
        GridPane.setColumnIndex(resetButton, 1);
        buttonsPane.getChildren().add(solveButton);
        buttonsPane.getChildren().add(resetButton);
        return buttonsPane;
    }

    private static Button createProceedButton(TextField[][] tfArr) {
        Button btn = new Button();
        btn.setText("Solve");
        final TextField[][][] effectiveFinalTfArr = {tfArr};
        btn.setOnAction(actionEvent -> {
            char[][] board = ArrayUtils.fromTextFieldsToChars(tfArr);
            SolutionBacktracking.getInstance().solveSudoku(board);
            ArrayUtils.fromCharsToTextFields(board, effectiveFinalTfArr[0]);
        });
        return btn;
    }

    private static Button createResetButton(TextField[][] tfArr) {
        Button btn = new Button();
        btn.setText("Clear");
        btn.setOnAction(actionEvent -> ArrayUtils.resetTextFields(tfArr));
        return btn;
    }
}