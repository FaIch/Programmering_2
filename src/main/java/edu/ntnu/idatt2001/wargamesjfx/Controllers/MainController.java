package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import com.dlsc.formsfx.model.validators.StringLengthValidator;
import edu.ntnu.idatt2001.wargamesjfx.Battle.*;
import edu.ntnu.idatt2001.wargamesjfx.IO.*;
import edu.ntnu.idatt2001.wargamesjfx.WarGames;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML ChoiceBox terrain;
    @FXML ChoiceBox existingArmies1;
    @FXML ChoiceBox existingArmies2;
    @FXML HBox backGround;
    @FXML Label army1Name;
    @FXML Label army1Total;
    @FXML Label army1Inf;
    @FXML Label army1Ran;
    @FXML Label army1Cav;
    @FXML Label army1Com;
    @FXML Label army2Name;
    @FXML Label army2Total;
    @FXML Label army2Inf;
    @FXML Label army2Ran;
    @FXML Label army2Cav;
    @FXML Label army2Com;
    @FXML Button fight;
    @FXML Button reset;

    private Army armyOne;
    private Army armyTwo;
    private ArmyCSVRead reader = new ArmyCSVRead();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        terrain.getItems().addAll("Hill", "Plains", "Forest", "Random");
        existingArmies1.getItems().addAll(reader.getArmies());
        existingArmies2.getItems().addAll(reader.getArmies());
    }

    @FXML
    void loadArmy1() throws IOException {
        String armyChosen = existingArmies1.getValue().toString();
        armyOne = reader.readArmyFromCSV(new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                "files/" + armyChosen + ".csv"));
        setTeamOneStats();
        if (armyTwo != null && terrain.getValue() != null){
            fight.setDisable(false);
        }
    }

    @FXML
    void loadArmy2() throws IOException {
        String armyChosen = existingArmies2.getValue().toString();
        armyTwo = reader.readArmyFromCSV(new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/" +
                "files/" + armyChosen + ".csv"));
        setTeamTwoStats();
        if (armyOne != null && terrain.getValue() != null){
            fight.setDisable(false);
        }
    }

    @FXML
    public void createNewArmy() throws IOException {
        ViewSwitcher.switchTo(View.NEWARMY);
    }

    @FXML
    void fight(){
        Battle battle = new Battle(armyOne,armyTwo,terrain.getValue().toString());
        battle.simulate();
        setTeamOneStats();
        setTeamTwoStats();
        fight.setDisable(true);
        reset.setDisable(false);
    }

    @FXML
    void reset() throws IOException {
        loadArmy1();
        loadArmy2();
        fight.setDisable(false);
        reset.setDisable(true);
    }

    private void setTeamOneStats(){
        army1Name.setText(armyOne.getName());
        army1Total.setText(String.valueOf(armyOne.getNumberOfUnits()));
        army1Inf.setText(String.valueOf(armyOne.getInfantryUnits().size()));
        army1Ran.setText(String.valueOf(armyOne.getRangedUnits().size()));
        army1Cav.setText(String.valueOf(armyOne.getCavalryUnits().size()));
        army1Com.setText(String.valueOf(armyOne.getCommanderUnits().size()));
    }

    private void setTeamTwoStats(){
        army2Name.setText(armyTwo.getName());
        army2Total.setText(String.valueOf(armyTwo.getNumberOfUnits()));
        army2Inf.setText(String.valueOf(armyTwo.getInfantryUnits().size()));
        army2Ran.setText(String.valueOf(armyTwo.getRangedUnits().size()));
        army2Cav.setText(String.valueOf(armyTwo.getCavalryUnits().size()));
        army2Com.setText(String.valueOf(armyTwo.getCommanderUnits().size()));
    }
}