package edu.ntnu.idatt2001.wargamesjfx.interfaces;

import edu.ntnu.idatt2001.wargamesjfx.factory.UnitType;

/**
 * The interface Situational bonus, used to calculate bonus based on what situation the attack is in
 */
public interface SituationalBonus {
    /**
     * Gets situational attack bonus.
     *
     * @param enemyUnit the enemy that is being attacked
     * @return the situational attack bonus the unit has based on enemyUnit
     */
    int getSituationalAttackBonus(UnitType enemyUnit);
}
