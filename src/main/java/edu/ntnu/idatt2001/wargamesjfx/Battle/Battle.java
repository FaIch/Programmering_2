package edu.ntnu.idatt2001.wargamesjfx.Battle;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;

/**
 * The type Battle.
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;
    public Terrain terrain;
    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     * @throws IllegalArgumentException if any of the armies are empty upon instantiation.
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) throws IllegalArgumentException {
        if (!armyOne.hasUnits()){
            throw new IllegalArgumentException(armyOne.getName() + " has no units");
        }
        else if (!armyTwo.hasUnits()){
            throw new IllegalArgumentException(armyTwo.getName() + " has no units");
        }
        else {
            this.armyOne = armyOne;
            this.armyTwo = armyTwo;
            this.terrain = terrain;
        }
    }

    /**
     * Simulate the battle between the armies.
     * The system is turn based, and which unit that attacks, and the unit that is attacked is random.
     * Uses modulus to decide which player who should attack, armyOne attacks if the number is even
     * while armyTwo attacks if the number is odd.
     *
     * @return the winning army of the battle.
     */
    public Army simulate(){
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            if (counter % 2 == 0) {
                attack(armyTwo);
            }
            else {
                attack(armyOne);
            }
            counter++;
        }
        if (armyOne.getNumberOfUnits() > 0){
            return armyOne;
        }
        return armyTwo;
    }

    /**
     * Method for attacking, a random unit from one army attacks a random unit from the other,
     * if the health of the unit that is attacked is below or equal zero, the unit is removed from the army.
     * @param army the army that is attacked, decided by the counter in the simulate method.
     */
    public void attack(Army army){
        try {
            if (army.equals(armyTwo)) {
                Unit armyOneRandomUnit = armyOne.getRandomUnit();
                armyTwo.getRandomUnit().attack(armyOneRandomUnit, this.terrain);

                if (armyOneRandomUnit.getHealth() <= 0) {
                    armyOne.removeUnit(armyOneRandomUnit);
                }
            } else {
                Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
                armyOne.getRandomUnit().attack(armyTwoRandomUnit, this.terrain);

                if (armyTwoRandomUnit.getHealth() <= 0) {
                    armyTwo.removeUnit(armyTwoRandomUnit);
                }
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }




    @Override
    public String toString() {
        return "Winner of battle is: \n" + this.simulate().getName() + "\nNumber of units remaining: \n"
                + this.simulate().getNumberOfUnits();
    }
}
