package ru.sudo.sudokusolver.guiEventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.Map;
import java.util.function.BiFunction;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.DOWN;


public class BoardController {

    private BoardController() {
        //empty constructor
    }

    public static GridPane createBoard(TextField[][] tfArr) {
        GridPane board = new GridPane();
        int size = tfArr.length;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                GridPane tfGridPane = new GridPane();
                tfGridPane.setPadding(new Insets(3, 2, 2, 3));

                // Create a new TextField in each Iteration
                TextField tf = createTextField();

                // Create navigation by arrow keys
                createNavigationWithArrows(tf, tfArr);

                // persist values for further manipulations with data
                tfArr[y][x] = tf;

                tfGridPane.getChildren().add(tf);
                board.add(tfGridPane, x, y);

                if (x % 3 == 0 || x == size - 1) {
                    int tx = x == size - 1 ? x + 1 : x;
                    Line vLine = new Line(tx, 0, tx, 53);
                    vLine.setStyle("-fx-stroke-width: 2px");
                    board.add(vLine, tx, y);
                }
                if (y % 3 == 0 || y == size - 1) {
                    int ty = y == size - 1 ? y + 1 : y;
                    Line hLine = new Line(0, ty - 150, 53, ty - 150);
                    hLine.setStyle("-fx-stroke-width: 2px");
                    GridPane.setValignment(hLine, VPos.TOP);
                    board.add(hLine, x, ty);
                }
            }
        }
        return board;
    }

    private static TextField createTextField() {
        TextField tf = new TextField();
        tf.setPrefHeight(50);
        tf.setPrefWidth(50);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(true);
        tf.setText("");
        tf.setFont(Font.font("Verdana", 14));
        tf.setFocusTraversable(false);

        setValidator(tf);

        return tf;
    }

    private static void setValidator(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && (!newValue.matches("\\d?") || Integer.parseInt(newValue) == 0)) {
                String val = !oldValue.isEmpty() && oldValue.matches("\\d?") && Integer.parseInt(oldValue) != 0 ? oldValue : "";
                tf.setText(val);
            }
        });
    }

    private static void createNavigationWithArrows(TextField tf, TextField[][] tfArr) {
        Map<KeyCode, BiFunction<Integer, Integer, Boolean>> navigationMap = getKeyCodeBiFunctionMap(tfArr);
        tf.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            Node src = (Node) event.getSource();
            Node gridPane = src.getScene().getFocusOwner().getParent();
            if (event.getCode().isArrowKey()) {
                Node parentGridPane = gridPane.getParent();
                if (parentGridPane instanceof GridPane) {
                    int row = GridPane.getRowIndex(gridPane);
                    int col = GridPane.getColumnIndex(gridPane);

                    navigationMap.get(event.getCode()).apply(row, col);
                }
            }
//            event.consume();
        });

    }

    private static Map<KeyCode, BiFunction<Integer, Integer, Boolean>> getKeyCodeBiFunctionMap(TextField[][] tfArr) {
        int size = tfArr.length;
        return Map.of(
                LEFT, (row, col) -> {
                    tfArr[row][(col + size - 1) % size].requestFocus();
                    return true;
                },
                RIGHT, (row, col) -> {
                    tfArr[row][(col + 1) % size].requestFocus();
                    return true;
                },
                UP, (row, col) -> {
                    tfArr[(row + size - 1) % size][col].requestFocus();
                    return true;
                },
                DOWN, (row, col) -> {
                    tfArr[(row + 1) % size][col].requestFocus();
                    return true;
                }
        );
    }

}