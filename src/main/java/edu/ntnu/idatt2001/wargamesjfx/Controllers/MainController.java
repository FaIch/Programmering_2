package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.*;
import edu.ntnu.idatt2001.wargamesjfx.IO.*;
import edu.ntnu.idatt2001.wargamesjfx.Utilities;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML ChoiceBox terrain;
    @FXML ChoiceBox existingArmies1;
    @FXML ChoiceBox existingArmies2;
    @FXML Label army1Name;
    @FXML Label army1Total;
    @FXML Label army1Inf;
    @FXML Label army1Ran;
    @FXML Label army1Cav;
    @FXML Label army1Com;
    @FXML Label army1Mage;
    @FXML Label army1Banner;
    @FXML Label army1Dragon;
    @FXML Label army2Name;
    @FXML Label army2Total;
    @FXML Label army2Inf;
    @FXML Label army2Ran;
    @FXML Label army2Cav;
    @FXML Label army2Com;
    @FXML Label army2Mage;
    @FXML Label army2Banner;
    @FXML Label army2Dragon;
    @FXML Button fight;
    @FXML Button reset;
    @FXML Label pathArmy1;
    @FXML Label pathArmy2;
    @FXML Label warningLabel;
    @FXML ImageView image;

    private Stage stage;
    private Army armyOne;
    private Army armyTwo;
    private final FileChooser fileChooser = new FileChooser();
    private String path1;
    private String path2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain.getItems().addAll("Hill", "Plains", "Forest");
        terrain.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue)->
                image.setImage(new Image(Utilities.getImagePath((String) newValue)))));
        existingArmies1.getItems().addAll(ArmyCSVRead.getArmies());
        existingArmies2.getItems().addAll(ArmyCSVRead.getArmies());
    }


    @FXML
    void chooseExistingArmy1() throws IOException {
        if (existingArmies1.getValue() == null){
            warningLabel.setText("You must choose an army");
        }
        else {
            String armyChosen = existingArmies1.getValue().toString();
            File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                    "files/" + armyChosen + ".csv");
            armyOne = ArmyCSVRead.readArmyFromCSV(file);
            setArmyOneStats();
            path1 = file.getAbsolutePath();
            pathArmy1.setText(path1);
            warningLabel.setText("");
        }
    }

    @FXML
    void chooseExistingArmy2() throws IOException {
        if (existingArmies2.getValue() == null) {
            warningLabel.setText("You must choose an army");
        }
        else {
            String armyChosen = existingArmies2.getValue().toString();
            File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                    "files/" + armyChosen + ".csv");
            armyTwo = ArmyCSVRead.readArmyFromCSV(file);
            setArmyTwoStats();
            path2 = file.getAbsolutePath();
            pathArmy2.setText(path2);
            warningLabel.setText("");
        }
    }

    @FXML
    void loadArmyFromFile1() throws IOException {
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
    void loadArmyFromFile2() throws IOException {
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
        Battle battle = null;
        try {
            battle = new Battle(armyOne, armyTwo, Terrain.valueOf(terrain.getValue().toString()));
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
        Objects.requireNonNull(battle).simulate();
        setArmyOneStats();
        setArmyTwoStats();
        fight.setDisable(true);
        reset.setDisable(false);
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
    void reset() throws IOException {
        setArmy1FromPath();
        setArmy2FromPath();
        fight.setDisable(false);
        reset.setDisable(true);
    }

    private void setArmyOneStats(){
        army1Name.setText(armyOne.getName());
        army1Total.setText(String.valueOf(armyOne.getNumberOfUnits()));
        army1Inf.setText(String.valueOf(armyOne.getInfantryUnits().size()));
        army1Ran.setText(String.valueOf(armyOne.getRangedUnits().size()));
        army1Cav.setText(String.valueOf(armyOne.getCavalryUnits().size()));
        army1Com.setText(String.valueOf(armyOne.getCommanderUnits().size()));
        army1Mage.setText(String.valueOf(armyOne.getMageUnits().size()));
        army1Banner.setText(String.valueOf(armyOne.getBannerUnits().size()));
        army1Dragon.setText(String.valueOf(armyOne.getDragonUnits().size()));
    }

    private void setArmyTwoStats(){
        army2Name.setText(armyTwo.getName());
        army2Total.setText(String.valueOf(armyTwo.getNumberOfUnits()));
        army2Inf.setText(String.valueOf(armyTwo.getInfantryUnits().size()));
        army2Ran.setText(String.valueOf(armyTwo.getRangedUnits().size()));
        army2Cav.setText(String.valueOf(armyTwo.getCavalryUnits().size()));
        army2Com.setText(String.valueOf(armyTwo.getCommanderUnits().size()));
        army2Mage.setText(String.valueOf(armyTwo.getMageUnits().size()));
        army2Banner.setText(String.valueOf(armyTwo.getBannerUnits().size()));
        army2Dragon.setText(String.valueOf(armyTwo.getDragonUnits().size()));
    }
}