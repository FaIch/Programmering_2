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
            armyOneAttack();
            if (armyTwo.getNumberOfUnits() > 0) {
                armyTwoAttack();
            }
        }
        if (armyOne.getNumberOfUnits() > 0){
            return armyOne;
        }
        return armyTwo;
    }

    public void armyOneAttack(){
        Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
        armyOne.getRandomUnit().attack(armyTwoRandomUnit);

        if (armyTwoRandomUnit.getHealth() <= 0){
            armyTwo.removeUnit(armyTwoRandomUnit);
        }
    }

    public void armyTwoAttack(){
        Unit armyOneRandomUnit = armyOne.getRandomUnit();
        armyTwo.getRandomUnit().attack(armyOneRandomUnit);

        if (armyOneRandomUnit.getHealth() <= 0){
            armyOne.removeUnit(armyOneRandomUnit);
        }
    }

    @Override
    public String toString() {
        return "Winner of battle is: \n" + simulate().getName() + "\nNumber of units remaining: \n"
                + simulate().getNumberOfUnits();
    }
}
