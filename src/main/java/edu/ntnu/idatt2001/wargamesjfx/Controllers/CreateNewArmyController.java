package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVRead;
import edu.ntnu.idatt2001.wargamesjfx.IO.ArmyCSVWrite;
import edu.ntnu.idatt2001.wargamesjfx.scenes.View;
import edu.ntnu.idatt2001.wargamesjfx.scenes.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreateNewArmyController {
    @FXML TextField armyName;
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
    @FXML Label moneyOut;
    @FXML ImageView infantryInfo;
    @FXML ImageView rangedInfo;
    @FXML ImageView cavalryInfo;
    @FXML ImageView commanderInfo;
    @FXML ImageView mageInfo;
    @FXML ImageView bannerInfo;
    @FXML ImageView dragonInfo;


    private int nrOfInf = 0;
    private int nrOfRan = 0;
    private int nrOfCav = 0;
    private int nrOfCom = 0;
    private int nrOfMage = 0;
    private int nrOfBanner = 0;
    private int nrOfDragon = 0;
    private int totalUnits = 0;
    private int numberToRemove = 0;
    private int numberToAdd = 0;
    private int money = 10000;
    private final int costOfInf = 50;
    private final int costOfRan = 60;
    private final int costOfCav = 80;
    private final int costOfCom = 100;
    private final int costOfMage = 1000;
    private final int costOfBanner = 2000;
    private final int costOfDragon = 10;

    ArrayList<ChoiceBox> boxes;

    @FXML
    public void initialize(){
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("1","5","10","25","50"));
        boxes = new ArrayList<>(Arrays.asList(infNr,ranNr,cavNr,comNr,mageNr,bannerNr,
                dragonNr));
        for (ChoiceBox box : boxes){
            box.getItems().addAll(numbers);
        }

        Tooltip.install(infantryInfo, new Tooltip("Health: 100, Attack : 15, Armor: 10 \n Bonus in Forest"));
        Tooltip.install(rangedInfo,new Tooltip("Health: 100, Attack: 15, Armor: 8 \n Bonus in Hill, Lower attack in Forest"));
        Tooltip.install(cavalryInfo, new Tooltip("Health: 100, Attack: 20, Armor: 12 \n Bonus in Plains, good against Infantry"));
        Tooltip.install(mageInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD \n Glass cannon unit, Bonus in: TBD"));
        Tooltip.install(bannerInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD \n Boosts attack for all units in Army by X%(Unique)"));
        Tooltip.install(dragonInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD \n Immune to magic"));


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
            nrOfInf = 0;
            nrOfRan = 0;
            nrOfCav = 0;
            nrOfCom = 0;
            nrOfMage = 0;
            nrOfBanner = 0;
            nrOfDragon = 0;
            totalUnits = 0;
            money = 10000;
            for (ChoiceBox box : boxes) {
                box.valueProperty().set(null);
            }
            warningLabel.setText("Army added successfully");
            setAllOut();
        }
    }

    @FXML
    void addInf(){
        numberToAdd = Integer.parseInt(infNr.getValue().toString());
        if (numberToAdd * costOfInf <= money) {
            nrOfInf += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfInf;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addRan(){
        numberToAdd = Integer.parseInt(ranNr.getValue().toString());
        if (numberToAdd * costOfRan <= money) {
            nrOfRan += numberToAdd;
            totalUnits += Integer.parseInt(ranNr.getValue().toString());
            money -= numberToAdd * costOfRan;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addCav(){
        numberToAdd = Integer.parseInt(cavNr.getValue().toString());
        if (numberToAdd * costOfCav <= money) {
            nrOfCav += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfCav;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addCom(){
        numberToAdd = Integer.parseInt(comNr.getValue().toString());
        if (numberToAdd * costOfCom <= money) {
            nrOfCom += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfCom;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addMage(){
        numberToAdd = Integer.parseInt(mageNr.getValue().toString());
        if (numberToAdd * costOfMage <= money) {
            nrOfMage += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfMage;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addBanner(){
        numberToAdd = Integer.parseInt(bannerNr.getValue().toString());
        if (numberToAdd * costOfBanner <= money) {
            nrOfBanner += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfBanner;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
    }

    @FXML
    void addDragon(){
        numberToAdd = Integer.parseInt(dragonNr.getValue().toString());
        if (numberToAdd * costOfDragon <= money) {
            nrOfDragon += numberToAdd;
            totalUnits += numberToAdd;
            money -= numberToAdd * costOfDragon;
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money");
        }
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
            money += numberToRemove * costOfRan;
            nrOfRan -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfRan;
            money += nrOfRan * costOfRan;
            nrOfRan = 0;
            setAllOut();
        }
    }

    @FXML
    void removeCav(){
        numberToRemove = Integer.parseInt(cavNr.getValue().toString());
        if (nrOfCav >= numberToRemove) {
            money += numberToRemove * costOfCav;
            nrOfCav -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfCav;
            money += nrOfCav * costOfCav;
            nrOfCav = 0;
            setAllOut();
        }
    }

    @FXML
    void removeCom(){
        numberToRemove = Integer.parseInt(comNr.getValue().toString());
        if (nrOfCom >= numberToRemove) {
            money += numberToRemove * costOfCom;
            nrOfCom -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfCom;
            money += nrOfCav * costOfCav;
            nrOfCom = 0;
            setAllOut();
        }
    }

    @FXML
    void removeMage(){
        numberToRemove = Integer.parseInt(mageNr.getValue().toString());
        if (nrOfMage >= numberToRemove) {
            money += numberToRemove * costOfMage;
            nrOfMage -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfMage;
            money += nrOfMage * costOfMage;
            nrOfMage = 0;
            setAllOut();
        }
    }

    @FXML
    void removeBanner(){
        numberToRemove = Integer.parseInt(bannerNr.getValue().toString());
        if (nrOfBanner >= numberToRemove) {
            money += numberToRemove * costOfBanner;
            nrOfBanner -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfBanner;
            money += nrOfBanner * costOfBanner;
            nrOfBanner = 0;
            setAllOut();
        }
    }

    @FXML
    void removeDragon(){
        numberToRemove = Integer.parseInt(dragonNr.getValue().toString());
        if (nrOfDragon >= numberToRemove) {
            money += numberToRemove * costOfDragon;
            nrOfDragon -= numberToRemove;
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= nrOfDragon;
            money += nrOfDragon * costOfDragon;
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

        warningLabel.setText("Army overwritten successfully");
    }

    @FXML
    void setMainScreen() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
    }

    private Army getArmy(){
        String nameOfArmy = armyName.getText();
        Army newArmy = new Army(nameOfArmy);
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit, nrOfInf));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RangedUnit,nrOfRan));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CavalryUnit,nrOfCav));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CommanderUnit,nrOfCom));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.MageUnit, nrOfMage));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.BannerUnit, nrOfBanner));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.DragonUnit, nrOfDragon));
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
        moneyOut.setText(String.valueOf(money));
        warningLabel.setText("");
    }
}
