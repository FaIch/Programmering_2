package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.TerrainAttackBonus;

/**
 * The type Dragon unit.
 * Implements TerrainAttackBonus to calculate attack bonus
 */
public class DragonUnit extends Unit implements TerrainAttackBonus {


    /**
     * Instantiates a new Dragon Unit.
     *
     * @param health the health of the unit
     * @param name   the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public DragonUnit(int health, String name, int attack, int armor) {super(health, name, attack, armor);}

    /**
     * Instantiates a new Dragon unit.
     * Predetermined stats
     *
     * @param name the name of the unit
     */
    public DragonUnit(String name){
        super(200, name, 30, 12);

    }

    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {return 3 + getTerrainAttackBonus(terrain);}

    @Override
    public int getResistBonus(Terrain terrain) {
        return 3;
    }

    /**
     * Method for calculating attack bonus based on terrain, if the terrain is Forest, the unit loses 10 points of damage
     * @param terrain the unit is attacking in
     * @return the situational attack bonus
     */
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain.equals(Terrain.Forest)){
            return -10;
        }
        return 0;
    }
}
