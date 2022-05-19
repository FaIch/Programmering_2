package edu.ntnu.idatt2001.wargamesjfx.scenes;

/**
 * The enum View, used for switching scenes in GUI
 */
public enum View {
    /**
     * Main view.
     */
    MAIN("main_screen.fxml"),
    /**
     * Newarmy view.
     */
    NEWARMY("create_new_army_screen.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

}
