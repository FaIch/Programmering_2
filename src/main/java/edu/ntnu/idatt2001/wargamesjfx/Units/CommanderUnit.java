package edu.ntnu.idatt2001.wargamesjfx.Units;

/**
 * The type Commander unit.
 * Extends CavalryUnit, since the attack and resist bonus of the Commander is equal to Cavalry.
 */
public class CommanderUnit extends CavalryUnit{
    /**
     * Instantiates a new Commander unit.
     * Constructor 1
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public CommanderUnit(int health, int attack, int armor) {
        super(health, attack, armor);
    }

    /**
     * Instantiates a new Commander unit.
     * Constructor 2
     * Inherits methods from CavalryUnit
     *
     */
    public CommanderUnit() {
        super(180, 15, 8);
    }
}
