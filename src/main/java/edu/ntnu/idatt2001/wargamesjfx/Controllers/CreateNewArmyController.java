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
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateNewArmyController {
    @FXML TextField armyName;
    @FXML Text costOfInfantryOut;
    @FXML Text costOfRangedOut;
    @FXML Text costOfCavalryOut;
    @FXML Text costOfCommanderOut;
    @FXML Text costOfMageOut;
    @FXML Text costOfBannerOut;
    @FXML Text costOfDragonOut;
    @FXML ChoiceBox infNr;
    @FXML ChoiceBox ranNr;
    @FXML ChoiceBox cavNr;
    @FXML ChoiceBox comNr;
    @FXML ChoiceBox mageNr;
    @FXML ChoiceBox bannerNr;
    @FXML ChoiceBox dragonNr;
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
    @FXML ImageView nameInfo;
    @FXML Button addInfantryBtn;
    @FXML Button addRangedBtn;
    @FXML Button addCavalryBtn;
    @FXML Button addCommanderBtn;
    @FXML Button addMageBtn;
    @FXML Button addBannerBtn;
    @FXML Button addDragonBtn;
    @FXML Button removeInfantryBtn;
    @FXML Button removeRangedBtn;
    @FXML Button removeCavalryBtn;
    @FXML Button removeCommanderBtn;
    @FXML Button removeMageBtn;
    @FXML Button removeBannerBtn;
    @FXML Button removeDragonBtn;
    @FXML Button checkNameBtn;
    @FXML Button createNewArmyButton;
    @FXML Button yesButton;
    @FXML Button noButton;

    private int totalUnits = 0;
    private int money = 10000;
    private final int costOfInf = 125;
    private final int costOfRan = 125;
    private final int costOfCav = 140;
    private final int costOfCom = 200;
    private final int costOfMage = 115;
    private final int costOfBanner = 175;
    private final int costOfDragon = 250;
    private final List<Integer> costList = new ArrayList<>(Arrays.asList(costOfInf, costOfRan, costOfCav,
            costOfCom, costOfMage, costOfBanner, costOfDragon));
    private final List<Integer> numberOfXUnitList = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));

    List<ChoiceBox> boxes;
    List<Button> addButtons;
    List<Button> removeButtons;
    List<Text> costOutList;

    @FXML
    public void initialize(){
        List<String> numbers = new ArrayList<>(Arrays.asList("1","5","10","25","50"));
        boxes = new ArrayList<>(Arrays.asList(infNr,ranNr,cavNr,comNr,mageNr,bannerNr,
                dragonNr));

        for (ChoiceBox box : boxes){
            box.getItems().addAll(numbers);
        }
        addButtons = new ArrayList<>(Arrays.asList(addInfantryBtn, addRangedBtn, addCavalryBtn, addCommanderBtn,
                addMageBtn, addBannerBtn, addDragonBtn));

        removeButtons = new ArrayList<>(Arrays.asList(removeInfantryBtn, removeRangedBtn, removeCavalryBtn,
                removeCommanderBtn, removeMageBtn, removeBannerBtn, removeDragonBtn));

        costOutList = new ArrayList<>(Arrays.asList(costOfInfantryOut, costOfRangedOut, costOfCavalryOut,
                costOfCommanderOut, costOfMageOut, costOfBannerOut, costOfDragonOut));

        for (int i = 0; i < addButtons.size(); i++){
            int finalI = i;
            addButtons.get(i).setOnAction(e -> addUnit(finalI));
            removeButtons.get(i).setOnAction(e -> removeUnit(finalI));
            costOutList.get(i).setText(String.valueOf(costList.get(i)));
        }


        Tooltip.install(infantryInfo, new Tooltip("Health: 100, Attack : 15, Armor: 10 " +
                "\n Bonus in Forest"));

        Tooltip.install(rangedInfo,new Tooltip("Health: 100, Attack: 15, Armor: 8 " +
                "\n Bonus in Hill, Lower attack in Forest"));

        Tooltip.install(cavalryInfo, new Tooltip("Health: 100, Attack: 20, Armor: 12 " +
                "\n Bonus in Plains, good against Infantry"));

        Tooltip.install(mageInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Glass cannon unit, Bonus in: TBD"));

        Tooltip.install(bannerInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Boosts attack for all units in Army by X%(Unique)"));

        Tooltip.install(dragonInfo,new Tooltip("Health: TBD, Attack: TBD, Armor: TBD " +
                "\n Immune to magic, disadvantage in forest, advantage in plains"));

        Tooltip.install(nameInfo, new Tooltip("Name of army " +
                "\n To edit army: Type already existing army name"));


    }

    @FXML
    void checkName(){
        Army armyNameCheck = new Army(armyName.getText());
        if (!ArmyCSVRead.isNewArmy(armyNameCheck)){
            warningLabel.setText("Army name taken, do you wish to edit existing army?");
            yesButton.setDisable(false);
            yesButton.setVisible(true);
            noButton.setVisible(true);
            noButton.setDisable(false);
            createNewArmyButton.setDisable(true);
        }
        else{
            for (int i = 0; i < boxes.size(); i++){
                boxes.get(i).setDisable(false);
                addButtons.get(i).setDisable(false);
                removeButtons.get(i).setDisable(false);
                createNewArmyButton.setDisable(false);
                warningLabel.setText("This name is available");
            }
        }
    }

    @FXML
    void createNewArmy() {
        Army army = getArmy();
        File file = new File("src/main/resources/edu/ntnu/idatt2001/wargamesjfx/files/" + army.getName()
                + ".csv");

        if (!ArmyCSVRead.isNewArmy(army)){
            try {
                ArmyCSVWrite.writeFile(army, file, false);
            } catch (IOException e) {
                warningLabel.setText(e.getMessage());
            }
        }
        else {
            try {
                ArmyCSVWrite.writeFile(army,file,true);
            }catch (IOException exception){
                warningLabel.setText(exception.getMessage());
            }
        }
        armyName.setText("");
        armyName.setDisable(false);
        checkNameBtn.setDisable(false);
        createNewArmyButton.setText("Create new Army");
        createNewArmyButton.setDisable(true);

        for (int i = 0; i < numberOfXUnitList.size(); i++){
            numberOfXUnitList.set(i,0);
            boxes.get(i).setDisable(true);
            addButtons.get(i).setDisable(true);
            removeButtons.get(i).setDisable(true);
        }

        totalUnits = 0;
        money = 10000;

        for (ChoiceBox box : boxes) {
            box.valueProperty().set(null);
        }

        warningLabel.setText("Army added successfully");
        setAllOut();
    }

    void addUnit(int index){
        int numberToAdd = Integer.parseInt(boxes.get(index).getValue().toString());

        if (numberToAdd * costList.get(index) <= money){
            int value = numberOfXUnitList.get(index);
            value += numberToAdd;
            numberOfXUnitList.set(index, value);
            totalUnits += numberToAdd;
            money -= numberToAdd * costList.get(index);
            setAllOut();
        }
        else {
            warningLabel.setText("Not enough money to add that number of units");
        }
    }

    void removeUnit(int index){
        int numberToRemove = Integer.parseInt(boxes.get(index).getValue().toString());

        if (numberOfXUnitList.get(index) >= numberToRemove){
            money += numberToRemove * costList.get(index);
            int value = numberOfXUnitList.get(index);
            value -= numberToRemove;
            numberOfXUnitList.set(index, value);
            totalUnits -= numberToRemove;
            setAllOut();
        }
        else {
            totalUnits -= numberOfXUnitList.get(index);
            money += numberOfXUnitList.get(index) * costList.get(index);
            numberOfXUnitList.set(index, 0);
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
            addButtons.get(i).setDisable(false);
            removeButtons.get(i).setDisable(false);
            boxes.get(i).setDisable(false);
            totalUnits += numberOfXUnitList.get(i);
        }

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

    @FXML
    void setMainScreen() throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
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
}
