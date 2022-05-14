package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;

import java.util.Objects;

/**
 * The type Unit.
 */
public abstract class Unit {
    private int health;
    private final String name;
    private final int attack;
    private final int armor;

    /**
     * Instantiates a new Unit.
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param name the name of the unit
     * @param armor the armor of the unit
     * @throws IllegalArgumentException if any of the int parameters given are below or equal to zero.
     */
    public Unit(int health, String name, int attack, int armor) throws IllegalArgumentException {
        this.health = health;
        this.name = name;
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
     * @param hasBanner
     */
    public void attack(Unit opponent, Terrain terrain, boolean hasBanner){
        int totalDamage = this.attack + this.getAttackBonus(opponent, terrain);
        if (hasBanner){
            totalDamage += Math.abs(totalDamage * 0.2);
        }
        int totalResistance = opponent.getArmor() + opponent.getResistBonus(terrain);

        if (totalDamage > totalResistance) {
            opponent.health = opponent.health - totalDamage + totalResistance;
        }
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
     * Gets name.
     *
     * @return the name of the unit
     */
    public String getName() {
        return name;
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
     * @param enemyUnit
     * @param terrain
     */
    public abstract int getAttackBonus(Unit enemyUnit, Terrain terrain);

    /**
     * Abstract method for retrieving the resist bonus of a unit.
     * All subclasses implement this method
     *
     * @return the resist bonus of the unit
     * @param terrain
     */
    public abstract int getResistBonus(Terrain terrain);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && attack == unit.attack && armor == unit.armor && Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, name, attack, armor);
    }
}
