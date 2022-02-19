package Battle;
import Units.*;
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;

    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    public Army simulate(){
        while (armyOne.getNumberOfUnits() > 0 && armyTwo.getNumberOfUnits() > 0){
            Unit armyOneRandomUnit = armyOne.getRandomUnit();
            Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
            armyOne.getRandomUnit().attack(armyTwoRandomUnit);
            armyTwo.getRandomUnit().attack(armyOneRandomUnit);
            if (armyTwoRandomUnit.getHealth() <= 0){
                armyTwo.removeUnit(armyTwoRandomUnit);
            }
            if (armyOneRandomUnit.getHealth() <= 0){
                armyOne.removeUnit(armyOneRandomUnit);
            }
        }
        if (armyOne.getNumberOfUnits() > 0){
            return armyOne;
        }
        return armyTwo;
    }

    @Override
    public String toString() {
        return "Winner of battle is: \n" + simulate().getName() + "\nNumber of units remaining: \n"
                + simulate().getNumberOfUnits();
    }
}
