package edu.ntnu.idatt2001.wargamesjfx.units;

import edu.ntnu.idatt2001.wargamesjfx.battle.Terrain;
import edu.ntnu.idatt2001.wargamesjfx.factory.UnitType;

/**
 * The type Banner unit.
 */
public class BannerUnit extends Unit{

    /**
     * Instantiates a new Banner unit.
     *
     * @param health the health of the unit
     * @param name   the name of the unit
     * @param attack the attack of the unit
     * @param armor  the armor of the unit
     */
    public BannerUnit(int health, String name, int attack, int armor) {
        super(health, name, attack, armor);
    }

    /**
     * Instantiates a new Banner unit.
     * Predetermined stats, this unit solely boosts the army, it has no use other than boosting other units
     *
     * @param name the name of the unit
     */
    public BannerUnit(String name){super(80, name,0, 0);}

    /**
     * Since this unit is only boosting its allies, this unit does not have any bonuses
     * @param enemyUnit the type of the unit that is being attacked
     * @param terrain the terrain the attack is happening in
     * @return returns 0
     */
    @Override
    public int getAttackBonus(UnitType enemyUnit, Terrain terrain) {
        return 0;
    }

    @Override
    public int getResistBonus(Terrain terrain) {
        return 0;
    }
}
