package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Interface.TerrainBonus;

/**
 * The type Cavalry unit.
 * Counter as a variable decides the attackBonus of the given unit, based on how many times the unit has attacked.
 */
public class CavalryUnit extends Unit implements TerrainBonus {
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
     * Inherits methods
     * Constructor that will see most use, where the user decides name and health of unit
     * and the attack and armor stats are already defined
     *
     * @param name
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
     * Method for attack bonus for CavalryUnits
     * A cavalry unit will have the highest attack bonus on the first strike, afterwards it will be constant.
     * Attack bonus varies with terrain
     * @return the current attack bonus of the unit
     * @param enemyUnit
     * @param terrain
     */
    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        int attackBonus = 0;
        if (counter == 0){
            attackBonus = 6;
        }
        else {
            attackBonus = 2;
        }
        increaseCounter();
        return  attackBonus +getTerrainAttackBonus(terrain);
    }

    /**
     * Resist bonus for this unit varies, in Forest it has no advantage
     * @return the resist bonus.
     * @param terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 1 + getTerrainArmorBonus(terrain);
    }

    @Override
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Plains)){
            return 2;
        }
        return 0;
    }

    @Override
    public int getTerrainArmorBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Forest)){
            return -1;
        }
        return 0;
    }
}
