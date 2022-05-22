package edu.ntnu.idatt2001.wargamesjfx.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.Objects;

/**
 * The type View switcher.
 * Used for scene switching
 */
public class ViewSwitcher {

    private static Scene scene;

    /**
     * Sets scene.
     *
     * @param scene the scene
     */
    public static void setScene(Scene scene) {ViewSwitcher.scene = scene;}

    /**
     * Switch to the chosen scene
     * sets root
     *
     * @param view the view to be set
     * @throws IOException the io exception
     */
    public static void switchTo(View view) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFileName())));
            scene.setRoot(root);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}