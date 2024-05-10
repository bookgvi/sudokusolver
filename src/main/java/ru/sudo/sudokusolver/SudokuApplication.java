package ru.sudo.sudokusolver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.sudo.sudokusolver.guiEventHandler.BoardController;
import ru.sudo.sudokusolver.guiEventHandler.ProcessController;

import java.io.IOException;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("board-view.fxml"));
        final int SIZE = 9;
        Scene scene = null;

        TextField[][] tfArr = new TextField[SIZE][SIZE];
        GridPane board = BoardController.createBoard(tfArr);
        GridPane buttonsPane = ProcessController.createButtonsPane(tfArr);

        Insets insets = new Insets(20, 20, 20, 20);
        VBox vbox = new VBox(board, buttonsPane);
        vbox.setSpacing(20.0);
        vbox.setPadding(insets);

        scene = new Scene(vbox);
        stage.setTitle("Sudoku!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}