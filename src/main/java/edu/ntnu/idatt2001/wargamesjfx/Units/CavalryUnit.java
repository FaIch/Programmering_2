package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.*;

/**
 * The type Cavalry unit.
 * Counter as a variable decides the attackBonus of the given unit, based on how many times the unit has attacked.
 * Implements the interfaces TerrainAttackBonus, TerrainDefenceBonus and SituationalBonus to calculate bonuses
 */
public class CavalryUnit extends Unit implements TerrainAttackBonus, TerrainDefenceBonus, SituationalBonus {
    private int counter;

    /**
     * Instantiates a new Cavalry unit.
     * Constructor 1
     * Extends the unit class, and therefore inherits methods i.e "super".
     * Will not be used as much, since the attack and armor of the unit are predetermined
     *
     * @param health the health of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public CavalryUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Cavalry unit.
     * Constructor 2
     * Stats are predetermined, only name is set by user
     *
     * @param name of the unit
     */
    public CavalryUnit(String name){
        super(100, name, 20,12);
    }

    /**
     * Increases counter, used for calculating the attack bonus of the unit. Increases when the getAttackBonus method
     * is called in the Unit superclass, ensuring correct attackBonus based on how many times the unit has attacked.
     */
    public void increaseCounter(){
        counter++;
    }

    /**
     * Method for getting attack bonus for CavalryUnits
     * A cavalry unit will have the highest attack bonus on the first strike, afterwards it will be constant.
     *
     * Attack bonus varies with terrain and what unit is being attacked
     *
     * @return the current attack bonus of the unit
     * @param enemyUnit the unit that is being attacked
     * @param terrain the terrain the attack is happening in
     */
    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {
        int attackBonus;
        if (counter == 0){
            attackBonus = 6;
        }
        else {
            attackBonus = 2;
        }
        increaseCounter();
        return  attackBonus + getTerrainAttackBonus(terrain) + getSituationalAttackBonus(enemyUnit);
    }

    /**
     * Resist bonus for this unit varies, in Forest the unit loses bonus
     * @return the resist bonus.
     * @param terrain the terrain the unit is being attacked in
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 1 + getTerrainDefenceBonus(terrain);
    }

    /**
     * Calculates attack bonus based on terrain, gains 2 points of damage in Plains
     * @param terrain the terrain the unit is attacking in
     * @return the attack bonus from terrain
     */
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Plains)){
            return 2;
        }
        return 0;
    }

    /**
     * Calculates resist bonus based on terrain, loses 1 point of resist in Forest
     * @param terrain the terrain the unit is being attacked in
     * @return the resist bonus from terrain
     */
    public int getTerrainDefenceBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Forest)){
            return -1;
        }
        return 0;
    }

    /**
     * Attack bonus based on what unitType the unit is attacking, gains 2 points of damage if attacking an InfantryUnit
     * @param enemyUnit that is being attacked
     * @return bonus from situation
     */
    public int getSituationalAttackBonus(UnitType enemyUnit) {
        if (enemyUnit.equals(UnitType.InfantryUnit)){
            return 2;
        }
        return 0;
    }
}
