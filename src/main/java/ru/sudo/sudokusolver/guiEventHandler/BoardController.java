package ru.sudo.sudokusolver.guiEventHandler;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(true);
                tf.setText("");
                tf.setFont(Font.font("Verdana", 14));
                tf.setFocusTraversable(false);

                tfArr[y][x] = tf;

                // Iterate the Index using the loops
                GridPane.setRowIndex(tf, y);
                GridPane.setColumnIndex(tf, x);
                board.getChildren().add(tf);
                if (x > 0 && x % 3 == 0) {
                    int tx = x == size - 1 ? x + 1 : x;
                    Line vLine = new Line(tx, 0, tx, 50);
                    vLine.setStyle("-fx-stroke-width: 2px");
                    board.add(vLine, tx, y);
                }
                if (y > 0 && y % 3 == 0) {
                    int ty = y == size - 1 ? y + 1 : y;
                    Line hLine = new Line(0, ty - 150, 50, ty - 150);
                    hLine.setStyle("-fx-stroke-width: 2px");
                    GridPane.setValignment(hLine, VPos.TOP);
                    board.add(hLine, x, ty);
                }
            }
        }
        return board;
    }

}