package edu.ntnu.idatt2001.wargamesjfx.Units;

import edu.ntnu.idatt2001.wargamesjfx.Battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.*;


/**
 * The type Infantry unit, implements the interfaces TerrainAttackBonus and TerrainDefenceBonus to calculate bonuses
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
     * Name set by user. Health, attack and armor predetermined
     *
     * @param name of the unit
     */
    public InfantryUnit(String name){
        super(100,name,15,10);

    }

    /**
     * Has a default bonus, gets bonus attack bonus at certain terrain
     * @return The attack bonus of the unit
     * @param enemyUnit the unit that is being attacked
     * @param terrain the terrain the attack is happening in
     */
    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {
        return 2 + getTerrainAttackBonus(terrain);
    }


    /**
     * Default resist bonus, additional bonus in certain terrain
     * @return the resist bonus of the unit.
     * @param terrain the terrain the attack is happening in
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 1 + getTerrainDefenceBonus(terrain);
    }

    /**
     * Gets attack bonus based on what terrain the unit is in
     * If the attack is happening in Forest, the unit has an additional attack bonus of 2
     * @param terrain the current terrain
     * @return bonus based on terrain
     */
    public int getTerrainAttackBonus(Terrain terrain) {
        if (terrain == Terrain.Forest) {
            return 2;
        }
        return 0;
    }

    /**
     * Gets resist bonus based on what terrain the unit is in
     * If the attack is happening in Forest, the unit has an additional resist bonus of 2
     * @param terrain the current terrain
     * @return bonus based on terrain
     */
    public int getTerrainDefenceBonus(Terrain terrain) {
        if (terrain == Terrain.Forest) {
            return 2;
        }
        return 0;
    }
}
