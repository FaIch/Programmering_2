package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.*;
import edu.ntnu.idatt2001.wargamesjfx.IO.*;

import edu.ntnu.idatt2001.wargamesjfx.Units.Unit;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;


public class MainController{
    @FXML ChoiceBox<String> terrain, existingArmies1, existingArmies2;

    @FXML Label army1Name, army1Total, army1Infantry, army1Ranged, army1Cavalry, army1Commander, army1Mage, army1Banner,
            army1Dragon, army2Name, army2Total, army2Infantry, army2Ranged, army2Cavalry, army2Commander, army2Mage,
            army2Banner, army2Dragon, warningLabel;

    @FXML Button fight, reset, army1PathButton, army2PathButton;
    @FXML ImageView image;
    @FXML VBox army1Box, army2Box;
    @FXML TableView<Unit> unitTableArmy1, unitTableArmy2;
    @FXML TableColumn<Unit, String> army1UnitName, army2UnitName;
    @FXML TableColumn<Unit, Integer> army1UnitHealth, army2UnitHealth;
    private Army armyOne;
    private Army armyTwo;
    private final FileChooser fileChooser = new FileChooser();
    private String path1;
    private String path2;
    private Battle battle;


    /**
     * Upon initializing the scene, the choiceboxes are set with correct values, and listeners are added to the objects
     */
    @FXML
    public void initialize() {
        terrain.getItems().addAll("Hill", "Plains", "Forest");
        terrain.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue)->
                image.setImage(new Image("file:src/main/resources/edu/ntnu/idatt2001/wargamesjfx/images/" + newValue
                        + ".jpg"))));
        existingArmies1.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
                chooseArmy1FromExisting(newValue)));
        existingArmies2.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
                chooseArmy2FromExisting(newValue)));

        army1UnitName.setCellValueFactory((TableColumn.CellDataFeatures<Unit, String> unit)
                -> new SimpleObjectProperty<>(unit.getValue().getName()));
        army1UnitHealth.setCellValueFactory((TableColumn.CellDataFeatures<Unit, Integer> unit)
                -> new SimpleObjectProperty<>(unit.getValue().getHealth()));

        army2UnitName.setCellValueFactory((TableColumn.CellDataFeatures<Unit, String> unit)
                -> new SimpleObjectProperty<>(unit.getValue().getName()));
        army2UnitHealth.setCellValueFactory((TableColumn.CellDataFeatures<Unit, Integer> unit)
                -> new SimpleObjectProperty<>(unit.getValue().getHealth()));

        try {
            existingArmies1.getItems().addAll(ArmyCSVRead.getArmies());
            existingArmies2.getItems().addAll(ArmyCSVRead.getArmies());
        }catch (Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    /**
     * Opens up file explorer for the user, if the file matches the allowed file format, the army is loaded
     * Stores the path of the file for easy resetting of the army
     */
    @FXML
    void loadArmy1FromFile() {
        File file = fileChooser.showOpenDialog(null);
        try {
            armyOne = ArmyCSVRead.readArmyFromCSV(file);
            setArmyOneStats();
            path1 = file.getAbsolutePath();
            warningLabel.setText("Unbalanced battles may take place when loading external armies");
            army1PathButton.setDisable(false);
        }catch (Exception e){
            warningLabel.setText(e.getMessage());
        }
    }

    /**
     * Opens up file explorer for the user, if the file matches the allowed file format, the army is loaded
     * Stores the path of the file for easy resetting of the army
     */
    @FXML
    void loadArmy2FromFile() {
        File file = fileChooser.showOpenDialog(null);
        try {
            armyTwo = ArmyCSVRead.readArmyFromCSV(file);
            setArmyTwoStats();
            path2 = file.getAbsolutePath();
            warningLabel.setText("Unbalanced battles may take place when loading external armies");
            army2PathButton.setDisable(false);
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
    }

    /**
     * Switches scene to createNewArmy scene
     */
    @FXML
    public void createNewArmy() {
        try {
            ViewSwitcher.switchTo(View.NEWARMY);
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
    }

    /**
     * The method for simulating a fight between the armies. Checks that all required data is set and creates a battle
     *
     * The simulation is done on a new thread, and is communicating with the UI thread by using observer design pattern
     * and the JavaFX method: Platform.runLater
     *
     * When the fight is done, a winner is set and is displayed to the user
     */
    @FXML
    void fight() {
        if (terrain.getValue() == null) {
            warningLabel.setText("You must choose terrain");
        } else if (armyOne == null || armyTwo == null) {
            warningLabel.setText("You must choose armies");
        }
        else {
            try {
                //Observer pattern credit: https://www.youtube.com/watch?v=a2aB9n472U0
                battle = new Battle(armyOne, armyTwo, Terrain.valueOf(terrain.getValue()));
                battle.addListener(this::updateArmies);
                warningLabel.setText("Battle is ongoing!");
                new Thread(() -> {
                    try {
                        battle.simulate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setWinner();
                }).start();
            }catch (Exception e){
                warningLabel.setText(e.getMessage());
            }
            fight.setDisable(true);
            reset.setDisable(false);
        }
    }

    /**
     * Sets army from stored path. Used when resetting armies
     */
    @FXML
    void setArmy1FromPath() {
        File file = new File(path1);
        try {
            armyOne = ArmyCSVRead.readArmyFromCSV(file);
            setArmyOneStats();
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }


    }

    /**
     * Sets army from stored path. Used when resetting armies
     */
    @FXML
    void setArmy2FromPath() {
        File file = new File(path2);
        try {
            armyTwo = ArmyCSVRead.readArmyFromCSV(file);
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
        setArmyTwoStats();
    }

    /**
     * Method for letting the user see the path of the file the army is stored from
     */
    @FXML
    void viewArmy1Path(){
        viewPath(path1, armyOne.getName());
    }

    /**
     * Method for letting the user see the path of the file the army is stored from
     */
    @FXML
    void viewArmy2Path(){
        viewPath(path2, armyTwo.getName());
    }

    /**
     * Resets the armies, and enables correct buttons. Also resets the color for displaying winner.
     */
    @FXML
    void resetArmies() {
        setArmy1FromPath();
        setArmy2FromPath();
        fight.setDisable(false);
        reset.setDisable(true);
        army1Box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        army2Box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        warningLabel.setText("Armies have been reset!");
    }

    /**
     * Method for using an existing army. Army chosen from choicebox, stats and path set.
     * @param armyName name of the army that is chosen
     */
    private void chooseArmy1FromExisting(String armyName) {
        if (armyName == null){
            warningLabel.setText("You must choose an army");
        }
        else {
            try {
                File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                        "files/" + armyName + ".csv");
                armyOne = ArmyCSVRead.readArmyFromCSV(file);
                setArmyOneStats();
                path1 = file.getAbsolutePath();
                warningLabel.setText("Army chosen");
                army1PathButton.setDisable(false);
            }catch (IOException e){
                warningLabel.setText(e.getMessage());
            }
        }
    }

    /**
     * Method for using an existing army. Army chosen from choicebox, stats and path set.
     * @param armyName name of the army that is chosen
     */
    private void chooseArmy2FromExisting(String armyName) {
        if (armyName == null) {
            warningLabel.setText("You must choose an army");
        }
        else {
            try {
                File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                        "files/" + armyName + ".csv");
                armyTwo = ArmyCSVRead.readArmyFromCSV(file);
                setArmyTwoStats();
                path2 = file.getAbsolutePath();
                warningLabel.setText("Army chosen");
                army2PathButton.setDisable(false);
            }catch (Exception e){
                warningLabel.setText(e.getMessage());
            }
        }
    }

    /**
     * Method for viewing path of army, displayed using an information alert.
     * @param path of the army to be viewed
     * @param armyName name of the army
     */
    private void viewPath(String path, String armyName){
        warningLabel.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Army Path");
        alert.setHeaderText(armyName);
        alert.setContentText("Path: \n" + path);
        alert.showAndWait();
    }

    /**
     * Observer method for updating GUI when a unit dies. Allowing the user to watch the battle as it is happening
     */
    private void updateArmies() {
        Platform.runLater(() -> {
            setArmyOneStats();
            setArmyTwoStats();

        });
    }

    /**
     * Method for displaying winner on GUI, sets green background color of the winner army, and displays a message
     */
    private void setWinner(){
        Platform.runLater(() -> {
            if (armyOne.hasUnits()){
                army1Box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                warningLabel.setText(armyOne.getName() + " won the battle!");
            }else{
                army2Box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                warningLabel.setText(armyTwo.getName() + " won the battle!");
            }
        });
    }

    /**
     * Method for setting the stats of ArmyOne. Sets the text of GUI components matching with the information about
     * the given army
     */
    private void setArmyOneStats(){
        army1Name.setText(armyOne.getName());
        army1Total.setText(String.valueOf(armyOne.getNumberOfUnits()));
        army1Infantry.setText(String.valueOf(armyOne.getInfantryUnits().size()));
        army1Ranged.setText(String.valueOf(armyOne.getRangedUnits().size()));
        army1Cavalry.setText(String.valueOf(armyOne.getCavalryUnits().size()));
        army1Commander.setText(String.valueOf(armyOne.getCommanderUnits().size()));
        army1Mage.setText(String.valueOf(armyOne.getMageUnits().size()));
        army1Banner.setText(String.valueOf(armyOne.getBannerUnits().size()));
        army1Dragon.setText(String.valueOf(armyOne.getDragonUnits().size()));

        ObservableList<Unit> unitData = FXCollections.observableList(armyOne.getAllUnits());
        unitTableArmy1.setItems(unitData);

    }

    /**
     * Method for setting the stats of ArmyTwo. Sets the text of GUI components matching with the information about
     * the given army
     */
    private void setArmyTwoStats(){
        army2Name.setText(armyTwo.getName());
        army2Total.setText(String.valueOf(armyTwo.getNumberOfUnits()));
        army2Infantry.setText(String.valueOf(armyTwo.getInfantryUnits().size()));
        army2Ranged.setText(String.valueOf(armyTwo.getRangedUnits().size()));
        army2Cavalry.setText(String.valueOf(armyTwo.getCavalryUnits().size()));
        army2Commander.setText(String.valueOf(armyTwo.getCommanderUnits().size()));
        army2Mage.setText(String.valueOf(armyTwo.getMageUnits().size()));
        army2Banner.setText(String.valueOf(armyTwo.getBannerUnits().size()));
        army2Dragon.setText(String.valueOf(armyTwo.getDragonUnits().size()));

        ObservableList<Unit> unitData = FXCollections.observableList(armyTwo.getAllUnits());
        unitTableArmy2.setItems(unitData);
    }
}