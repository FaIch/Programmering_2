module edu.ntnu.idatt2001.wargamesjfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens edu.ntnu.idatt2001.wargamesjfx to javafx.fxml;
    exports edu.ntnu.idatt2001.wargamesjfx;
    exports edu.ntnu.idatt2001.wargamesjfx.controllers;
    opens edu.ntnu.idatt2001.wargamesjfx.controllers to javafx.fxml;
}