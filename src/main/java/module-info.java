module com.number.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.number.game to javafx.fxml;
    exports com.number.game;
}