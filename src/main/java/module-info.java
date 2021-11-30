module com.mnemoc.mnemoc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mnemoc to javafx.fxml;
    exports com.mnemoc;
    exports com.mnemoc.Controller;
    opens com.mnemoc.Controller to javafx.fxml;
    exports com.mnemoc.Models;
    opens com.mnemoc.Models to javafx.fxml;
    exports com.mnemoc.Utils;
    opens com.mnemoc.Utils to javafx.fxml;
}