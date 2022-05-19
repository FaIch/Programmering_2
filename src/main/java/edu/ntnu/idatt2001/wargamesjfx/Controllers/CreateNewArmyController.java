package edu.ntnu.idatt2001.wargamesjfx.Controllers;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Army;
import edu.ntnu.idatt2001.wargamesjfx.Factory.*;
import edu.ntnu.idatt2001.wargamesjfx.IO.*;
import edu.ntnu.idatt2001.wargamesjfx.scenes.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;

public class CreateNewArmyController {
    @FXML TextField armyName;
    @FXML Text costOfInfantryOut, costOfRangedOut, costOfCavalryOut, costOfCommanderOut, costOfMageOut, costOfBannerOut,
                costOfDragonOut;
    @FXML ChoiceBox infNr, ranNr, cavNr, comNr, mageNr, bannerNr, dragonNr;
    @FXML Label warningLabel, infOut, ranOut, cavOut, comOut, mageOut, bannerOut, dragonOut, totalOut, moneyOut;
    @FXML ImageView infantryInfo, rangedInfo, cavalryInfo, commanderInfo, mageInfo, bannerInfo, dragonInfo, nameInfo;
    @FXML Button addInfantryBtn, addRangedBtn, addCavalryBtn, addCommanderBtn, addMageBtn, addBannerBtn, addDragonBtn,
                removeInfantryBtn, removeRangedBtn, removeCavalryBtn, removeCommanderBtn, removeMageBtn, removeBannerBtn
                ,removeDragonBtn, checkNameBtn, createNewArmyButton, yesButton, noButton;

    private int totalUnits = 0;
    private int money = 100000;
    private final int costOfInf = 125;
    private final int costOfRan = 130;
    private final int costOfCav = 170;
    private final int costOfCom = 225;
    private final int costOfMage = 150;
    private final int costOfBanner = 250;
    private final int costOfDragon = 500;
    private final List<Integer> costList = new ArrayList<>(Arrays.asList(costOfInf, costOfRan, costOfCav,
            costOfCom, costOfMage, costOfBanner, costOfDragon));
    private final List<Integer> numberOfXUnitList = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));

    List<ChoiceBox> numberOfUnitChoiceBoxes;
    List<Button> addButtons;
    List<Button> removeButtons;
    List<Text> costOutList;

    @FXML
    public void initialize(){
        List<String> numbers = new ArrayList<>(Arrays.asList("1","5","10","25","50"));
        numberOfUnitChoiceBoxes = new ArrayList<>(Arrays.asList(infNr,ranNr,cavNr,comNr,mageNr,bannerNr,
                dragonNr));

        for (ChoiceBox box : numberOfUnitChoiceBoxes){
            box.getItems().addAll(numbers);
        }
        addButtons = new ArrayList<>(Arrays.asList(addInfantryBtn, addRangedBtn, addCavalryBtn, addCommanderBtn,
                addMageBtn, addBannerBtn, addDragonBtn));

        removeButtons = new ArrayList<>(Arrays.asList(removeInfantryBtn, removeRangedBtn, removeCavalryBtn,
                removeCommanderBtn, removeMageBtn, removeBannerBtn, removeDragonBtn));

        costOutList = new ArrayList<>(Arrays.asList(costOfInfantryOut, costOfRangedOut, costOfCavalryOut,
                costOfCommanderOut, costOfMageOut, costOfBannerOut, costOfDragonOut));
        moneyOut.setText(String.valueOf(money));
        for (int i = 0; i < addButtons.size(); i++){
            int finalI = i;
            addButtons.get(i).setOnAction(e -> addUnit(finalI));
            removeButtons.get(i).setOnAction(e -> removeUnit(finalI));
            costOutList.get(i).setText(String.valueOf(costList.get(i)));
        }

        createToolTip(infantryInfo,"Health: 100, Attack : 15, Armor: 10 \n Bonus in Forest");

        createToolTip(rangedInfo,"Health: 100, Attack: 15, Armor: 8 " +
                "\n attack bonus in Hill, lower attack in Forest");

        createToolTip(cavalryInfo, "Health: 100, Attack: 20, Armor: 12 " +
                "\n Bonus in Plains, good against Infantry");

        createToolTip(mageInfo,"Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Glass cannon unit, Bonus in: TBD");

        createToolTip(bannerInfo,"Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Boosts attack for all units in Army by 20%(Unique)");

        createToolTip(dragonInfo,"Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Immune to magic, disadvantage in forest, advantage in plains");

        createToolTip(nameInfo,"Name of army " +
                "\n To edit army: Type already existing army name");
    }

    @FXML
    void checkName(){
        Army armyNameCheck = new Army(armyName.getText());
        try {
            if (!ArmyCSVRead.isNewArmy(armyNameCheck)){
                warningLabel.setText("Army name taken, do you wish to edit existing army?");
                yesButton.setDisable(false);
                yesButton.setVisible(true);
                noButton.setVisible(true);
                noButton.setDisable(false);
                createNewArmyButton.setDisable(true);
                setDisableBoxesAndButtons(true);
            } else{
                setDisableBoxesAndButtons(false);
                warningLabel.setText("This name is available");

            }
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
    }

    @FXML
    void createNewArmy() {
        Army army = getArmy();
        File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/" + army.getName()
                + ".csv");
        try {
            ArmyCSVWrite.writeFile(army, file, ArmyCSVRead.isNewArmy(army));
        }catch (IOException e){
            warningLabel.setText(e.getMessage());
        }
        armyName.setText("");
        armyName.setDisable(false);
        checkNameBtn.setDisable(false);
        createNewArmyButton.setText("Create new Army");
        createNewArmyButton.setDisable(true);

        for (int i = 0; i < numberOfXUnitList.size(); i++){
            numberOfXUnitList.set(i,0);
            numberOfUnitChoiceBoxes.get(i).setDisable(true);
            addButtons.get(i).setDisable(true);
            removeButtons.get(i).setDisable(true);
        }

        totalUnits = 0;
        money = 100000;

        for (ChoiceBox box : numberOfUnitChoiceBoxes) {
            box.valueProperty().set(null);
        }

        setAllOut();
        warningLabel.setText("Army added successfully");
    }

    @FXML
    void setMainScreen() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
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
        totalUnits = 0;
        Army existingArmy = ArmyCSVRead.getExistingArmy(armyName.getText());

        numberOfXUnitList.set(0, existingArmy.getInfantryUnits().size());
        numberOfXUnitList.set(1, existingArmy.getRangedUnits().size());
        numberOfXUnitList.set(2, existingArmy.getCavalryUnits().size());
        numberOfXUnitList.set(3, existingArmy.getCommanderUnits().size());
        numberOfXUnitList.set(4, existingArmy.getMageUnits().size());
        numberOfXUnitList.set(5, existingArmy.getBannerUnits().size());
        numberOfXUnitList.set(6, existingArmy.getDragonUnits().size());

        for (int i = 0; i < addButtons.size(); i++) {
            totalUnits += numberOfXUnitList.get(i);
        }
        setDisableBoxesAndButtons(false);

        setAllOut();

        yesButton.setDisable(true);
        yesButton.setVisible(false);
        noButton.setDisable(true);
        noButton.setVisible(false);
        createNewArmyButton.setText("Edit army");
        createNewArmyButton.setDisable(false);
        armyName.setDisable(true);
        checkNameBtn.setDisable(true);
        warningLabel.setText("Displaying existing army stats");
    }

    private void setDisableBoxesAndButtons(Boolean bool){
        for (int i = 0; i < numberOfUnitChoiceBoxes.size(); i++){
            numberOfUnitChoiceBoxes.get(i).setDisable(bool);
            addButtons.get(i).setDisable(bool);
            removeButtons.get(i).setDisable(bool);
            createNewArmyButton.setDisable(bool);
        }
    }

    private void addUnit(int index){
        if (checkValidNumberChoice(index)) {
            int numberToAdd = Integer.parseInt(numberOfUnitChoiceBoxes.get(index).getValue().toString());
            if (numberToAdd * costList.get(index) <= money) {
                int value = numberOfXUnitList.get(index);
                value += numberToAdd;
                numberOfXUnitList.set(index, value);
                totalUnits += numberToAdd;
                money -= numberToAdd * costList.get(index);
                setAllOut();
            } else {
                warningLabel.setText("Not enough money to add that number of units");
            }
        }else {
            warningLabel.setText("Please choose a number to add");
        }
    }

    private void removeUnit(int index){
        if (checkValidNumberChoice(index)) {
            int numberToRemove = Integer.parseInt(numberOfUnitChoiceBoxes.get(index).getValue().toString());
            if (numberOfXUnitList.get(index) >= numberToRemove) {
                money += numberToRemove * costList.get(index);
                int value = numberOfXUnitList.get(index);
                value -= numberToRemove;
                numberOfXUnitList.set(index, value);
                totalUnits -= numberToRemove;
                setAllOut();
            } else {
                totalUnits -= numberOfXUnitList.get(index);
                money += numberOfXUnitList.get(index) * costList.get(index);
                numberOfXUnitList.set(index, 0);
                setAllOut();
            }
        }else {
            warningLabel.setText("Please choose a number to add");
        }
    }

    private boolean checkValidNumberChoice(int index){
        return numberOfUnitChoiceBoxes.get(index).getValue() != null;
    }


    private Army getArmy(){
        String nameOfArmy = armyName.getText();
        Army newArmy = new Army(nameOfArmy);

        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit, numberOfXUnitList.get(0), "Infantry"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RangedUnit,numberOfXUnitList.get(1), "Ranged"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CavalryUnit,numberOfXUnitList.get(2), "Cavalry"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CommanderUnit,numberOfXUnitList.get(3), "Commander"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.MageUnit, numberOfXUnitList.get(4), "Mage"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.BannerUnit, numberOfXUnitList.get(5), "Banner"));
        newArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.DragonUnit, numberOfXUnitList.get(6), "Dragon"));

        return newArmy;
    }

    private void setAllOut(){
        totalOut.setText(String.valueOf(totalUnits));

        infOut.setText(String.valueOf(numberOfXUnitList.get(0)));
        ranOut.setText(String.valueOf(numberOfXUnitList.get(1)));
        cavOut.setText(String.valueOf(numberOfXUnitList.get(2)));
        comOut.setText(String.valueOf(numberOfXUnitList.get(3)));
        mageOut.setText(String.valueOf(numberOfXUnitList.get(4)));
        bannerOut.setText(String.valueOf(numberOfXUnitList.get(5)));
        dragonOut.setText(String.valueOf(numberOfXUnitList.get(6)));

        moneyOut.setText(String.valueOf(money));
        warningLabel.setText("");
    }

    private void createToolTip(Node node, String text){
        Tooltip tooltip = new Tooltip(text);
        tooltip.setStyle("-fx-font: 14px Arial;");
        Tooltip.install(node, tooltip);
    }
}
