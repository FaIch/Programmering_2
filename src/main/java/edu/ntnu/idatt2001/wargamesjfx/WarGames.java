package edu.ntnu.idatt2001.wargamesjfx;

import edu.ntnu.idatt2001.wargamesjfx.battle.Battle;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WarGames extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAIN);
        stage.setTitle("WARGAMES");
        stage.setScene(scene);
        //When the application is closed, the thread for battle will stop
        stage.setOnCloseRequest(windowEvent -> Battle.stop());
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}