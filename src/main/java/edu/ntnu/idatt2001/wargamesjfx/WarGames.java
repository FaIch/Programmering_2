package edu.ntnu.idatt2001.wargamesjfx;

import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WarGames extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.START);
        stage.setTitle("WARGAMES");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}