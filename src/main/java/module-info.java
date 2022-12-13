module com.game.void_seekers {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

//    exports com.game.void_seekers.application;
    exports com.game.void_seekers.item.base;
    exports com.game.void_seekers.item.derived;
    exports com.game.void_seekers.character.base;
    exports com.game.void_seekers.unrelated;
//    exports com.game.void_seekers.character.derived;
    exports com.game.void_seekers.interfaces;
    exports com.game.void_seekers.tools;
    opens com.game.void_seekers.item.base to javafx.fxml;
    opens com.game.void_seekers.item.derived to javafx.fxml;
    opens com.game.void_seekers.interfaces to javafx.fxml;
    exports com.game.void_seekers.application;
    opens com.game.void_seekers.application to javafx.fxml;
    exports Test;
}