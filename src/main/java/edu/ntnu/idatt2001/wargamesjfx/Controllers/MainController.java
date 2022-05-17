package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.*;
import edu.ntnu.idatt2001.wargamesjfx.IO.*;

import edu.ntnu.idatt2001.wargamesjfx.Utilities;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML ChoiceBox terrain, existingArmies1, existingArmies2;

    @FXML Label army1Name, army1Total, army1Infantry, army1Ranged, army1Cavalry, army1Commander, army1Mage, army1Banner, army1Dragon,
            army2Name, army2Total, army2Infantry, army2Ranged, army2Cavalry, army2Commander, army2Mage, army2Banner, army2Dragon,
            pathArmy1, pathArmy2, warningLabel;

    @FXML Button fight, reset;
    @FXML ImageView image;
    @FXML VBox army1Box, army2Box;

    private Stage stage;
    private Army armyOne;
    private Army armyTwo;
    private final FileChooser fileChooser = new FileChooser();
    private String path1;
    private String path2;
    private Battle battle;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain.getItems().addAll("Hill", "Plains", "Forest");
        terrain.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue)->
                image.setImage(new Image(Utilities.getImagePath((String) newValue)))));
        try {
            existingArmies1.getItems().addAll(ArmyCSVRead.getArmies());
            existingArmies2.getItems().addAll(ArmyCSVRead.getArmies());
        }catch (Exception e){
            warningLabel.setText(e.getMessage());
        }
    }


    @FXML
    void chooseArmy1FromExisting() {
        if (existingArmies1.getValue() == null){
            warningLabel.setText("You must choose an army");
        }
        else {
            String armyChosen = existingArmies1.getValue().toString();
            try {
                File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                        "files/" + armyChosen + ".csv");
                armyOne = ArmyCSVRead.readArmyFromCSV(file);
                setArmyOneStats();
                path1 = file.getAbsolutePath();
                pathArmy1.setText(path1);
            }catch (IOException e){
                warningLabel.setText(e.getMessage());
            }
            warningLabel.setText("Army chosen");
        }
    }

    @FXML
    void chooseArmy2FromExisting() {
        if (existingArmies2.getValue() == null) {
            warningLabel.setText("You must choose an army");
        }
        else {
            try {
                String armyChosen = existingArmies2.getValue().toString();
                File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                        "files/" + armyChosen + ".csv");
                armyTwo = ArmyCSVRead.readArmyFromCSV(file);
                setArmyTwoStats();
                path2 = file.getAbsolutePath();
                pathArmy2.setText(path2);
                warningLabel.setText("Army chosen");
            }catch (Exception e){
                warningLabel.setText(e.getMessage());
            }
        }
    }

    @FXML
    void loadArmy1FromFile() {
        File file = fileChooser.showOpenDialog(stage);
        try {
            armyOne = ArmyCSVRead.readArmyFromCSV(file);
        }catch (Exception e){
            warningLabel.setText(e.getMessage());
        }
        setArmyOneStats();
        path1 = file.getAbsolutePath();
        pathArmy1.setText(path1);
        warningLabel.setText("Unbalanced battles may take place when loading external armies");
    }

    @FXML
    void loadArmy2FromFile() {
        File file = fileChooser.showOpenDialog(stage);
        try {
            armyTwo = ArmyCSVRead.readArmyFromCSV(file);
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
        setArmyTwoStats();
        path2 = file.getAbsolutePath();
        pathArmy2.setText(path2);
        warningLabel.setText("Unbalanced battles may take place when loading external armies");
    }

    @FXML
    public void createNewArmy() throws IOException {
        ViewSwitcher.switchTo(View.NEWARMY);
    }

    @FXML
    void fight() {
        if (terrain.getValue() == null) {
            warningLabel.setText("You must choose terrain");
        } else if (armyOne == null || armyTwo == null) {
            warningLabel.setText("You must choose armies");
        }
        else {
            try {
                battle = new Battle(armyOne, armyTwo, Terrain.valueOf(terrain.getValue().toString()));
                battle.addListener(this::updateArmies);
                new Thread(() -> {
                    try {
                        battle.simulate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (armyOne.hasUnits()){
                        army1Box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        warningLabel.setText(armyOne.getName() + " won the battle!");
                    }else{
                        army2Box.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        warningLabel.setText(armyTwo.getName() + " won the battle!");
                    }
                }).start();
            }catch (Exception e){
                warningLabel.setText(e.getMessage());
            }
            fight.setDisable(true);
            reset.setDisable(false);
        }
    }

    private void updateArmies() {
        Platform.runLater(() -> {
            setArmyOneStats();
            setArmyTwoStats();
        });
    }

    @FXML
    void setArmy1FromPath() throws IOException {
        File file = new File(path1);
        armyOne = ArmyCSVRead.readArmyFromCSV(file);
        setArmyOneStats();
    }

    @FXML
    void setArmy2FromPath() throws IOException {
        File file = new File(path2);
        armyTwo = ArmyCSVRead.readArmyFromCSV(file);
        setArmyTwoStats();
    }

    @FXML
    void resetArmies() throws IOException {
        setArmy1FromPath();
        setArmy2FromPath();
        fight.setDisable(false);
        reset.setDisable(true);
        army1Box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        army2Box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

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
    }

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
    }
}