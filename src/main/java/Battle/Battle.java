package Battle;
import Units.*;
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;
    public Battle(Army armyOne, Army armyTwo) throws IllegalArgumentException {
        if (!armyOne.hasUnits()){
            throw new IllegalArgumentException(armyOne.getName() + " has no units");
        }
        else if (!armyTwo.hasUnits()){
            throw new IllegalArgumentException(armyTwo.getName() + " has no units");
        }
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    public Army simulate(){
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()){
            if (counter % 2 == 0) {
                armyOneAttack();
                if (armyTwo.hasUnits()) {
                    armyTwoAttack();
                }
            }
            if (counter % 2 != 0){
                armyTwoAttack();
                if (armyOne.hasUnits()){
                    armyOneAttack();
                }
            }
            counter++;
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
        return "Winner of battle is: \n" + this.simulate().getName() + "\nNumber of units remaining: \n"
                + this.simulate().getNumberOfUnits();
    }
}
