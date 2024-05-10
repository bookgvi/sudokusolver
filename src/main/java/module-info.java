module ru.sudo.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.sudo.sudokusolver to javafx.fxml;
    exports ru.sudo.sudokusolver;
    exports ru.sudo.sudokusolver.guiEventHandler;
    opens ru.sudo.sudokusolver.guiEventHandler to javafx.fxml;
}