package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVRead;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVWrite;
import edu.ntnu.idatt2001.wargamesjfx.Units.InfantryUnit;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CreateNewArmyController {
    @FXML TextField armyName;
    @FXML TextField infantryName;
    @FXML TextField rangedName;
    @FXML TextField cavalryName;
    @FXML TextField commanderName;
    @FXML TextField nrOfInfantry;
    @FXML TextField nrOfRanged;
    @FXML TextField nrOfCavalry;
    @FXML TextField nrOfCommander;
    @FXML Button createNewArmyButton;
    @FXML Button yesButton;
    @FXML Button noButton;
    @FXML Label warningLabel;

    @FXML
    void createNewArmy() throws IOException {
        Army newArmy = getArmy();
        if (!ArmyCSVRead.isNewArmy(newArmy)){
            warningLabel.setText("Army name taken, do you wish to overwrite existing army?");
            yesButton.setDisable(false);
            yesButton.setVisible(true);
            noButton.setVisible(true);
            noButton.setDisable(false);
            createNewArmyButton.setDisable(true);
        }
        else {
            File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/" + newArmy.getName()
                    + ".csv");
            try {
                ArmyCSVWrite.writeFile(newArmy,file,true);
            }catch (IOException exception){
                warningLabel.setText(exception.getMessage());
            }
            armyName.setText("");
            infantryName.setText("");
            rangedName.setText("");
            cavalryName.setText("");
            commanderName.setText("");
            nrOfInfantry.setText("");
            nrOfRanged.setText("");
            nrOfCavalry.setText("");
            nrOfCommander.setText("");
            warningLabel.setText("Army added successfully");
        }
    }

    @FXML
    void onNoButtonPressed(){
        armyName.setText("");
        createNewArmyButton.setDisable(false);
        yesButton.setDisable(true);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        noButton.setDisable(true);
        warningLabel.setText("");
    }

    @FXML
    void onYesButtonPressed(){
        Army newArmy = getArmy();
        File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/" + newArmy.getName()
                + ".csv");
        try {
            ArmyCSVWrite.writeFile(newArmy,file,false);
        }catch (IOException exception){
            warningLabel.setText(exception.getMessage());
        }
        armyName.setText("");
        infantryName.setText("");
        rangedName.setText("");
        cavalryName.setText("");
        commanderName.setText("");
        nrOfInfantry.setText("");
        nrOfRanged.setText("");
        nrOfCavalry.setText("");
        nrOfCommander.setText("");
        warningLabel.setText("Army overwritten successfully");
    }

    @FXML
    void setMainScreen() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
    }

    private Army getArmy(){
        String nameOfArmy = armyName.getText();
        String nameOfInf = infantryName.getText();
        String nameOfRan = rangedName.getText();
        String nameOfCav = cavalryName.getText();
        String nameOfCom = commanderName.getText();
        int nrOfInf = Integer.parseInt(nrOfInfantry.getText());
        int nrOfRan = Integer.parseInt(nrOfRanged.getText());
        int nrOfCav = Integer.parseInt(nrOfCavalry.getText());
        int nrOfCom = Integer.parseInt(nrOfCommander.getText());

        Army newArmy = new Army(nameOfArmy);
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.INFANTRY,nameOfInf,100,nrOfInf));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RANGED,nameOfRan,100,nrOfRan));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CAVALRY,nameOfCav,100,nrOfCav));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.COMMANDER,nameOfCom,180,nrOfCom));
        return newArmy;
    }
}
