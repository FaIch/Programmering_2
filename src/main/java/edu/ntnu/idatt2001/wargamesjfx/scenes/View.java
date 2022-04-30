package edu.ntnu.idatt2001.wargamesjfx.scenes;

public enum View {
    START("start_screen.fxml"),
    MAIN("main_screen.fxml"),
    NEWARMY("create_new_army_screen.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
