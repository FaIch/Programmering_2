package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.*;


/**
 * The type Infantry unit.
 */
public class InfantryUnit extends Unit implements TerrainAttackBonus, TerrainDefenceBonus {

    /**
     * Instantiates a new Infantry unit.
     * Constructor 1
     *
     * @param health the health of the unit
     * @param name the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public InfantryUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Infantry unit.
     * Constructor 2
     * Name set by user, health, attack and armor predetermined
     *
     * @param name of the unit
     */
    public InfantryUnit(String name){
        super(100,name,15,10);

    }

    /**
     * bonus based on terrain, if terrain is Forest, increased bonus
     * @return The attack bonus of the unit
     * @param enemyUnit
     * @param terrain
     */
    @Override
    public int getAttackBonus(Unit enemyUnit, Terrain terrain) {
        return 2 + getTerrainAttackBonus(terrain);
    }


    /**
     * Resist bonus based on terrain, if terrain is Forest, increased bonus
     * @return the resist bonus of the unit.
     * @param terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 1 + getTerrainDefenceBonus(terrain);
    }

    @Override
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain == Terrain.Forest) {
            return 2;
        }
        return 0;
    }

    public int getTerrainDefenceBonus(Terrain terrain) {
        if (terrain == Terrain.Forest) {
            return 2;
        }
        return 0;
    }
}
