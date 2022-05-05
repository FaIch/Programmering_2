package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVRead;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVWrite;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateNewArmyController {
    @FXML TextField armyName;
    @FXML TextField infantryName;
    @FXML TextField rangedName;
    @FXML TextField cavalryName;
    @FXML TextField commanderName;
    @FXML TextField mageName;
    @FXML TextField bannerName;
    @FXML TextField dragonName;
    @FXML ChoiceBox infNr;
    @FXML ChoiceBox ranNr;
    @FXML ChoiceBox cavNr;
    @FXML ChoiceBox comNr;
    @FXML ChoiceBox mageNr;
    @FXML ChoiceBox bannerNr;
    @FXML ChoiceBox dragonNr;
    @FXML Button createNewArmyButton;
    @FXML Button yesButton;
    @FXML Button noButton;
    @FXML Label warningLabel;
    @FXML Label infOut;
    @FXML Label ranOut;
    @FXML Label cavOut;
    @FXML Label comOut;
    @FXML Label mageOut;
    @FXML Label bannerOut;
    @FXML Label dragonOut;
    @FXML Label totalOut;
    @FXML ImageView infantryInfo;


    private int nrOfInf = 0;
    private int nrOfRan = 0;
    private int nrOfCav = 0;
    private int nrOfCom = 0;
    private int nrOfMage = 0;
    private int nrOfBanner = 0;
    private int nrOfDragon = 0;
    private int totalUnits = 0;
    private int numberToRemove = 0;
    private int money = 10000;
    private int costOfInf = 0;
    private int costOfRan = 0;
    private int costOfCav = 0;
    private int costOfCom = 0;
    private int costOfMage = 0;
    private int costOfBanner = 0;
    private int costOfDragon = 0;


    @FXML
    public void initialize(){
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("1","5","10","25","50"));
        ArrayList<ChoiceBox> boxes = new ArrayList<>(Arrays.asList(infNr,ranNr,cavNr,comNr,mageNr,bannerNr,
                dragonNr));
        for (ChoiceBox box : boxes){
            box.getItems().addAll(numbers);
        }

        Tooltip.install(infantryInfo, new Tooltip("Health: 100, Attack : 15, Armor: 10, \n Bonus in forest"));

    }

    @FXML
    void createNewArmy() {
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
            mageName.setText("");
            bannerName.setText("");
            dragonName.setText("");
            warningLabel.setText("Army added successfully");
        }
    }

    @FXML
    void addInf(){
        nrOfInf += Integer.parseInt(infNr.getValue().toString());
        totalUnits += Integer.parseInt(infNr.getValue().toString());
        money -= nrOfInf * costOfInf;
        setAllOut();
    }

    @FXML
    void addRan(){
        nrOfRan += Integer.parseInt(ranNr.getValue().toString());
        totalUnits += Integer.parseInt(ranNr.getValue().toString());
        money -= nrOfRan * costOfRan;
        setAllOut();
    }

    @FXML
    void addCav(){
        nrOfCav += Integer.parseInt(cavNr.getValue().toString());
        totalUnits += Integer.parseInt(cavNr.getValue().toString());
        money -= nrOfCav * costOfCav;
        setAllOut();
    }

    @FXML
    void addCom(){
        nrOfCom += Integer.parseInt(comNr.getValue().toString());
        totalUnits += Integer.parseInt(comNr.getValue().toString());
        money -= nrOfCom * costOfCom;
        setAllOut();
    }

    @FXML
    void addMage(){
        nrOfMage += Integer.parseInt(mageNr.getValue().toString());
        totalUnits += Integer.parseInt(mageNr.getValue().toString());
        money -= nrOfMage * costOfMage;
        setAllOut();
    }

    @FXML
    void addBanner(){
        nrOfBanner += Integer.parseInt(bannerNr.getValue().toString());
        totalUnits += Integer.parseInt(bannerNr.getValue().toString());
        money -= nrOfBanner * costOfBanner;
        setAllOut();
    }

    @FXML
    void addDragon(){
        nrOfDragon += Integer.parseInt(dragonNr.getValue().toString());
        totalUnits += Integer.parseInt(dragonNr.getValue().toString());
        money -= nrOfDragon * costOfDragon;
        setAllOut();
    }

    @FXML
    void removeInf(){
        numberToRemove = Integer.parseInt(infNr.getValue().toString());
        if (nrOfInf >= numberToRemove){
            money += numberToRemove * costOfInf;
            nrOfInf -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfInf;
            money += nrOfInf * costOfInf;
            nrOfInf = 0;
            setAllOut();
        }
    }

    @FXML
    void removeRan(){
        numberToRemove = Integer.parseInt(ranNr.getValue().toString());
        if (nrOfRan >= numberToRemove) {
            nrOfRan -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfRan;
            nrOfRan = 0;
            setAllOut();
        }
    }

    @FXML
    void removeCav(){
        numberToRemove = Integer.parseInt(cavNr.getValue().toString());
        if (nrOfCav >= numberToRemove) {
            nrOfCav -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfCav;
            nrOfCav = 0;
            setAllOut();
        }
    }

    @FXML
    void removeCom(){
        numberToRemove = Integer.parseInt(comNr.getValue().toString());
        if (nrOfCom >= numberToRemove) {
            nrOfCom -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfCom;
            nrOfCom = 0;
            setAllOut();
        }
    }

    @FXML
    void removeMage(){
        numberToRemove = Integer.parseInt(mageNr.getValue().toString());
        if (nrOfMage >= numberToRemove) {
            nrOfMage -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfMage;
            nrOfMage = 0;
            setAllOut();
        }
    }

    @FXML
    void removeBanner(){
        numberToRemove = Integer.parseInt(bannerNr.getValue().toString());
        if (nrOfBanner >= numberToRemove) {
            nrOfBanner -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfBanner;
            nrOfBanner = 0;
            setAllOut();
        }
    }

    @FXML
    void removeDragon(){
        numberToRemove = Integer.parseInt(dragonNr.getValue().toString());
        if (nrOfDragon >= numberToRemove) {
            nrOfDragon -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfDragon;
            nrOfDragon = 0;
            setAllOut();
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
        String nameOfMage = mageName.getText();
        String nameOfBanner = bannerName.getText();
        String nameOfDragon = dragonName.getText();

        Army newArmy = new Army(nameOfArmy);
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit,nameOfInf, nrOfInf));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RangedUnit,nameOfRan, nrOfRan));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CavalryUnit,nameOfCav, nrOfCav));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CommanderUnit,nameOfCom, nrOfCom));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.MageUnit,nameOfMage, nrOfMage));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.BannerUnit,nameOfBanner, nrOfBanner));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.DragonUnit,nameOfDragon, nrOfDragon));
        return newArmy;
    }

    private void setAllOut(){
        totalOut.setText(String.valueOf(totalUnits));
        infOut.setText(String.valueOf(nrOfInf));
        ranOut.setText(String.valueOf(nrOfRan));
        cavOut.setText(String.valueOf(nrOfCav));
        comOut.setText(String.valueOf(nrOfCom));
        mageOut.setText(String.valueOf(nrOfMage));
        bannerOut.setText(String.valueOf(nrOfBanner));
        dragonOut.setText(String.valueOf(nrOfDragon));
    }
}
