module com.game.void_seekers {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.void_seekers to javafx.fxml;
    exports com.game.void_seekers;
    exports com.game.void_seekers.item.base;
    opens com.game.void_seekers.item.base to javafx.fxml;
}