package ru.sudo.sudokusolver.guiEventHandler;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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

        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && (!newValue.matches("\\d?") || Integer.parseInt(newValue) == 0)) {
                String val = !oldValue.isEmpty() && oldValue.matches("\\d?") && Integer.parseInt(oldValue) != 0 ? oldValue : "";
                tf.setText(val);
            }
        });
        return tf;
    }
}