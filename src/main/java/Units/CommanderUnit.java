package Units;

/**
 * The type Commander unit.
 * Extends CavalryUnit, since the attack and resist bonus of the Commander is equal to Cavalry.
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Instantiates a new Commander unit.
     * Constructor 1
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Commander unit.
     * Constructor 2
     * Inherits methods from CavalryUnit
     *
     * @param name   the name
     * @param health the health
     */
    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }
}
