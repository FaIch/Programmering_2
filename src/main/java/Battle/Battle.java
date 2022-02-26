package Battle;
import Units.*;

/**
 * The type Battle.
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;

    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     * @throws IllegalArgumentException if any of the armies are empty upon instantiation.
     */
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

    /**
     * Simulate the battle between the armies.
     * The system is turn based, and which unit that attacks, and is attacked is random.
     * Uses modulus to decide which player who should attack, armyone attacks if the number is even
     * while armytwo attacks if the number is odd.
     *
     * @return the winning army of the battle.
     */
    public Army simulate(){
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            if (counter % 2 == 0) {
                armyOneAttack();
            }

            if (counter % 2 != 0) {
                armyTwoAttack();
            }
            counter++;
        }
        if (armyOne.getNumberOfUnits() > 0){
            return armyOne;
        }
        return armyTwo;
    }

    /**
     * Method for army one attacking army two
     * Gets a random unit from army two, attacks it with a random unit from army one.
     * If the unit from army two has no more health, it is removed from the army.
     */
    public void armyOneAttack(){
        Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
        armyOne.getRandomUnit().attack(armyTwoRandomUnit);

        if (armyTwoRandomUnit.getHealth() <= 0){
            armyTwo.removeUnit(armyTwoRandomUnit);
        }
    }

    /**
     * Method for army two attacking army one
     * Gets a random unit from army one, attacks it with a random unit from army two.
     * If the unit from army one has no more health, it is removed from the army.
     */
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
