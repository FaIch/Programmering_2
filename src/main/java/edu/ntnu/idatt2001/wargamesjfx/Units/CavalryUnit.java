package edu.ntnu.idatt2001.wargamesjfx.Units;
/**
 * The type Cavalry unit.
 * Counter as a variable decides the attackBonus of the given unit, based on how many times the unit has attacked.
 */
public class CavalryUnit extends Unit {
    private int counter;

    /**
     * Instantiates a new Cavalry unit.
     * Constructor 1
     * Extends the unit class, and therefore inherits methods i.e "super".
     * Will not be used as much, since the attack and armor of the unit are predetermined
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Instantiates a new Cavalry unit.
     * Constructor 2
     * Inherits methods
     * Constructor that will see most use, where the user decides name and health of unit
     * and the attack and armor stats are already defined
     *
     * @param name   the name of the unit
     * @param health the health of the unit
     */
    public CavalryUnit(String name, int health){
        super(name, health, 20,12);
    }

    /**
     * Increases counter, used for calculating the attack bonus of the unit. Increases when the getAttackBonus method
     * is called in the Unit superclass, ensuring correct attackBonus based on how many times the unit has attacked.
     */
    public void increaseCounter(){
        counter++;
    }

    /**
     * Method for attack bonus for CavalryUnits
     * A cavalry unit will have an attack bonus of 6 on the first strike, afterwards it will be constant, at 2.
     * @return the current attack bonus of the unit
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 0;
        if (counter == 0){
            attackBonus = 6;
        }
        else{
            attackBonus = 2;
        }
        increaseCounter();
        return  attackBonus;
    }

    /**
     * Resist bonus for this unit is constant, at 1.
     * @return the resist bonus.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
