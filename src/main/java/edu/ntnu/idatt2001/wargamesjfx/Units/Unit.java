package edu.ntnu.idatt2001.wargamesjfx.Units;

/**
 * The type Unit.
 */
public abstract class Unit {
    private int health;
    private final int attack;
    private final int armor;

    /**
     * Instantiates a new Unit.
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     * @throws IllegalArgumentException if any of the int parameters given are below or equal to zero.
     */
    public Unit(int health, int attack, int armor) throws IllegalArgumentException {
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        if (health <= 0){
            throw new IllegalArgumentException("Health must be above zero.");
        }
    }

    /**
     * Method for attacking another unit
     * Takes into account all the stats a unit has
     *
     * @param opponent an opponent unit. Selected randomly
     */
    public void attack(Unit opponent){
            opponent.health = opponent.health - (this.attack + this.getAttackBonus()) +
                    (opponent.armor + opponent.getResistBonus());
    }


    /**
     * Gets health.
     *
     * @return the current health of the unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets attack.
     *
     * @return the attack of the unit
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets armor.
     *
     * @return the armor of the unit
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Sets health of unit
     *
     * @param health the new value of health the unit should have
     * @throws IllegalArgumentException if new health is below 1.
     */
    public void setHealth(int health) throws IllegalArgumentException{
        if (health <= 0){
            throw new IllegalArgumentException("Cannot set health less than one");
        }
        this.health = health;
    }

    @Override
    public String toString() {
        return "Health: " + this.health +
                "\nAttack: " + this.attack +
                "\nArmor:" + this.armor + "\n";
    }

    /**
     * Abstract method for retrieving the attack bonus of a unit.
     * All classes that inherit this class, must implement this method
     *
     * @return the attack bonus of the unit
     */
    public abstract int getAttackBonus();

    /**
     * Abstract method for retrieving the resist bonus of a unit.
     * All subclasses implement this method
     *
     * @return the resist bonus of the unit
     */
    public abstract int getResistBonus();
}
