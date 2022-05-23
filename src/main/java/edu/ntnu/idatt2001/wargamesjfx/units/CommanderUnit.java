package edu.ntnu.idatt2001.wargamesjfx.units;

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
    public CommanderUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Commander unit.
     * Constructor 2
     * Inherits methods from CavalryUnit
     *
     * @param name the name of the unit
     */
    public CommanderUnit(String name) {
        super(180, name, 15, 8);
    }
}
