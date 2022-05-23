package edu.ntnu.idatt2001.wargamesjfx.units;

import edu.ntnu.idatt2001.wargamesjfx.battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.interfaces.SituationalBonus;

/**
 * The type Mage unit.
 * Implements situationalBonus to calculate attack based on what type the unit is attacking
 */
public class MageUnit extends Unit implements SituationalBonus {

    /**
     * Instantiates a new Mage unit.
     *
     * @param health the health of the unit
     * @param name   the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public MageUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Mage unit.
     * Predetermined stats
     *
     * @param name the name of the unit
     */
    public MageUnit(String name){super(70, name, 35, 5);}

    /**
     * Default bonus of 3, calculates bonus based on what type the unit is attacking
     * @param enemyUnit the enemy unit the unit is attacking
     * @param terrain the terrain the attack is happening in
     * @return the attack bonus of the unit
     */
    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {
        if (enemyUnit == null){
            throw new IllegalArgumentException("EnemyUnit cannot be null");
        }
        return 3 + getSituationalAttackBonus(enemyUnit);
    }

    /**
     * Default resist bonus
     * @param terrain the terrain the unit is being attacked in
     * @return the resist bonus of the unit
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        return 1;
    }

    /**
     * Calculates attack based on what enemy the unit is attacking, if the unit is a Dragon, the unit loses all attack
     * since Dragons are immune to magic
     * @param enemyUnit the enemy the unit is attacking
     * @return the situational attack bonus
     */
    public int getSituationalAttackBonus(UnitType enemyUnit) {
        if (enemyUnit == null){
            throw new IllegalArgumentException("EnemyUnit cannot be null");
        }
        if (enemyUnit.equals(UnitType.DragonUnit)){
            return -50;
        }
        return 0;
    }

}
